package com.thecrackertechnology.dragonterminal;

import android.media.AudioRecord;
import android.media.AudioTrack;
import android.util.Log;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;
import java.util.concurrent.Semaphore;

/* compiled from: Audio */
class AudioThread {
    private AudioTrack mAudio;
    private byte[] mAudioBuffer;
    private NeoXorgViewClient mClient;
    private RecordingThread mRecordThread = null;
    /* access modifiers changed from: private */
    public AudioRecord mRecorder = null;
    private int mRecorderBufferSize = 0;
    private int mVirtualBufSize;

    private native int nativeAudioInitJavaCallbacks();

    /* access modifiers changed from: private */
    public native void nativeAudioRecordCallback();

    public AudioThread(NeoXorgViewClient neoXorgViewClient) {
        this.mClient = neoXorgViewClient;
        this.mAudio = null;
        this.mAudioBuffer = null;
        nativeAudioInitJavaCallbacks();
    }

    public int fillBuffer() {
        if (this.mClient.isPaused()) {
            try {
                Thread.sleep(500);
                return 1;
            } catch (InterruptedException unused) {
                return 1;
            }
        } else {
            this.mAudio.write(this.mAudioBuffer, 0, this.mVirtualBufSize);
            return 1;
        }
    }

    public int initAudio(int i, int i2, int i3, int i4) {
        int i5;
        if (this.mAudio == null) {
            int i6 = i2 == 1 ? 2 : 3;
            int i7 = i3 == 1 ? 2 : 3;
            this.mVirtualBufSize = i4;
            if (AudioTrack.getMinBufferSize(i, i6, i7) > i4) {
                i4 = AudioTrack.getMinBufferSize(i, i6, i7);
            }
            if (Globals.AudioBufferConfig != 0) {
                int i8 = (int) (((float) i4) * ((((float) (Globals.AudioBufferConfig - 1)) * 2.5f) + 1.0f));
                this.mVirtualBufSize = i8;
                i5 = i8;
            } else {
                i5 = i4;
            }
            this.mAudioBuffer = new byte[i5];
            this.mAudio = new AudioTrack(3, i, i6, i7, i5, 1);
            this.mAudio.play();
        }
        return this.mVirtualBufSize;
    }

    public byte[] getBuffer() {
        return this.mAudioBuffer;
    }

    public int deinitAudio() {
        AudioTrack audioTrack = this.mAudio;
        if (audioTrack != null) {
            audioTrack.stop();
            this.mAudio.release();
            this.mAudio = null;
        }
        this.mAudioBuffer = null;
        return 1;
    }

    public int initAudioThread() {
        Thread.currentThread().setPriority(10);
        return 1;
    }

    public int pauseAudioPlayback() {
        AudioTrack audioTrack = this.mAudio;
        if (audioTrack != null) {
            audioTrack.pause();
        }
        RecordingThread recordingThread = this.mRecordThread;
        if (recordingThread == null) {
            return 1;
        }
        recordingThread.pauseRecording();
        return 1;
    }

    public int resumeAudioPlayback() {
        AudioTrack audioTrack = this.mAudio;
        if (audioTrack != null) {
            audioTrack.play();
        }
        RecordingThread recordingThread = this.mRecordThread;
        if (recordingThread == null) {
            return 1;
        }
        recordingThread.resumeRecording();
        return 1;
    }

    private byte[] startRecording(int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (this.mRecordThread == null) {
            this.mRecordThread = new RecordingThread();
            this.mRecordThread.start();
        }
        if (!this.mRecordThread.isStopped()) {
            Log.i("SDL", "SDL: error: application already opened audio recording device");
            return null;
        }
        this.mRecordThread.init(i8);
        int i9 = i6 == 1 ? 16 : 12;
        int i10 = i7 == 1 ? 2 : 3;
        int minBufferSize = AudioRecord.getMinBufferSize(i, i9, i10);
        int max = Math.max(i8 * 8, minBufferSize + (i8 - (minBufferSize % i8)));
        Log.i("SDL", "SDL: app opened recording device, rate " + i + " channels " + i6 + " sample size " + (i7 + 1) + " bufsize " + i8 + " internal bufsize " + max);
        AudioRecord audioRecord = this.mRecorder;
        if (audioRecord != null && audioRecord.getSampleRate() == i5 && this.mRecorder.getChannelCount() == i6 && this.mRecorder.getAudioFormat() == i10 && this.mRecorderBufferSize == max) {
            Log.i("SDL", "SDL: reusing old recording device");
        } else {
            AudioRecord audioRecord2 = this.mRecorder;
            if (audioRecord2 != null) {
                audioRecord2.release();
            }
            this.mRecorder = null;
            try {
                this.mRecorder = new AudioRecord(1, i, i9, i10, max);
                this.mRecorderBufferSize = max;
            } catch (IllegalArgumentException unused) {
                Log.i("SDL", "SDL: error: failed to open MIC recording device!");
                try {
                    this.mRecorder = new AudioRecord(6, i, i9, i10, max);
                    this.mRecorderBufferSize = max;
                } catch (IllegalArgumentException unused2) {
                    Log.i("SDL", "SDL: error: failed to open VOICE_RECOGNITION recording device!");
                    try {
                        this.mRecorder = new AudioRecord(0, i, i9, i10, max);
                        this.mRecorderBufferSize = max;
                    } catch (IllegalArgumentException unused3) {
                        Log.i("SDL", "SDL: error: failed to open DEFAULT recording device!");
                        return null;
                    }
                }
            }
        }
        this.mRecordThread.startRecording();
        return this.mRecordThread.mRecordBuffer;
    }

    private void stopRecording() {
        RecordingThread recordingThread = this.mRecordThread;
        if (recordingThread == null || recordingThread.isStopped()) {
            Log.i("SDL", "SDL: error: application already closed audio recording device");
            return;
        }
        this.mRecordThread.stopRecording();
        Log.i("SDL", "SDL: app closed recording device");
    }

    /* compiled from: Audio */
    private class RecordingThread extends Thread {
        byte[] mRecordBuffer;
        private boolean sleep = false;
        private boolean stopped = true;
        private Semaphore waitStarted = new Semaphore(0);

        RecordingThread() {
        }

        /* access modifiers changed from: package-private */
        public void init(int i) {
            byte[] bArr = this.mRecordBuffer;
            if (bArr == null || bArr.length != i) {
                this.mRecordBuffer = new byte[i];
            }
        }

        public void run() {
            while (true) {
                this.waitStarted.acquireUninterruptibly();
                this.waitStarted.drainPermits();
                this.stopped = false;
                this.sleep = false;
                while (!this.sleep) {
                    AudioRecord access$000 = AudioThread.this.mRecorder;
                    byte[] bArr = this.mRecordBuffer;
                    if (access$000.read(bArr, 0, bArr.length) != this.mRecordBuffer.length) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException unused) {
                        }
                    } else {
                        AudioThread.this.nativeAudioRecordCallback();
                    }
                }
                this.stopped = true;
                AudioThread.this.mRecorder.stop();
            }
        }

        public void startRecording() {
            AudioThread.this.mRecorder.startRecording();
            this.waitStarted.release();
        }

        public void stopRecording() {
            this.sleep = true;
            while (!this.stopped) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException unused) {
                }
            }
        }

        public void pauseRecording() {
            if (!this.stopped) {
                AudioThread.this.mRecorder.stop();
            }
        }

        public void resumeRecording() {
            if (!this.stopped) {
                AudioThread.this.mRecorder.startRecording();
            }
        }

        public boolean isStopped() {
            return this.stopped;
        }
    }
}
