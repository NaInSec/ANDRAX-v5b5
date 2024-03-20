package com.thecrackertechnology.dragonterminal.backend;

import android.os.Handler;
import android.os.Message;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.FileDescriptor;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TerminalSession extends TerminalOutput {
    private static final int MSG_NEW_INPUT = 1;
    private static final int MSG_PROCESS_EXITED = 4;
    private final String[] mArgs;
    /* access modifiers changed from: private */
    public final SessionChangedCallback mChangeCallback;
    private final String mCwd;
    /* access modifiers changed from: private */
    public TerminalEmulator mEmulator;
    private final String[] mEnv;
    public final String mHandle = UUID.randomUUID().toString();
    /* access modifiers changed from: private */
    public final Handler mMainThreadHandler = new Handler() {
        final byte[] mReceiveBuffer = new byte[4096];

        public void handleMessage(Message message) {
            if (message.what == 1 && TerminalSession.this.isRunning()) {
                int read = TerminalSession.this.mProcessToTerminalIOQueue.read(this.mReceiveBuffer, false);
                if (read > 0) {
                    TerminalSession.this.mEmulator.append(this.mReceiveBuffer, read);
                    TerminalSession.this.notifyScreenUpdate();
                }
            } else if (message.what == 4) {
                int intValue = ((Integer) message.obj).intValue();
                TerminalSession.this.cleanupResources(intValue);
                TerminalSession.this.mChangeCallback.onSessionFinished(TerminalSession.this);
                byte[] bytes = TerminalSession.this.getExitDescription(intValue).getBytes(StandardCharsets.UTF_8);
                TerminalSession.this.mEmulator.append(bytes, bytes.length);
                TerminalSession.this.notifyScreenUpdate();
            }
        }
    };
    /* access modifiers changed from: private */
    public final ByteQueue mProcessToTerminalIOQueue = new ByteQueue(4096);
    public String mSessionName;
    private int mShellExitStatus;
    private final String mShellPath;
    /* access modifiers changed from: private */
    public int mShellPid;
    private int mTerminalFileDescriptor;
    /* access modifiers changed from: private */
    public final ByteQueue mTerminalToProcessIOQueue = new ByteQueue(4096);
    private final byte[] mUtf8InputBuffer = new byte[5];

    public interface SessionChangedCallback {
        void onBell(TerminalSession terminalSession);

        void onClipboardText(TerminalSession terminalSession, String str);

        void onColorsChanged(TerminalSession terminalSession);

        void onSessionFinished(TerminalSession terminalSession);

        void onTextChanged(TerminalSession terminalSession);

        void onTitleChanged(TerminalSession terminalSession);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r2 = java.io.FileDescriptor.class.getDeclaredField("fd");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.FileDescriptor wrapFileDescriptor(int r4) {
        /*
            java.io.FileDescriptor r0 = new java.io.FileDescriptor
            r0.<init>()
            r1 = 1
            java.lang.Class<java.io.FileDescriptor> r2 = java.io.FileDescriptor.class
            java.lang.String r3 = "descriptor"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0013, IllegalAccessException -> 0x0011, IllegalArgumentException -> 0x000f }
            goto L_0x001b
        L_0x000f:
            r4 = move-exception
            goto L_0x0027
        L_0x0011:
            r4 = move-exception
            goto L_0x0027
        L_0x0013:
            java.lang.Class<java.io.FileDescriptor> r2 = java.io.FileDescriptor.class
            java.lang.String r3 = "fd"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0026, IllegalAccessException -> 0x0011, IllegalArgumentException -> 0x000f }
        L_0x001b:
            r2.setAccessible(r1)     // Catch:{ NoSuchFieldException -> 0x0026, IllegalAccessException -> 0x0011, IllegalArgumentException -> 0x000f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ NoSuchFieldException -> 0x0026, IllegalAccessException -> 0x0011, IllegalArgumentException -> 0x000f }
            r2.set(r0, r4)     // Catch:{ NoSuchFieldException -> 0x0026, IllegalAccessException -> 0x0011, IllegalArgumentException -> 0x000f }
            goto L_0x0031
        L_0x0026:
            r4 = move-exception
        L_0x0027:
            java.lang.String r2 = "NeoTerm-Emulator"
            java.lang.String r3 = "Error accessing FileDescriptor#descriptor private field"
            android.util.Log.wtf(r2, r3, r4)
            java.lang.System.exit(r1)
        L_0x0031:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalSession.wrapFileDescriptor(int):java.io.FileDescriptor");
    }

    public SessionChangedCallback getSessionChangedCallback() {
        return this.mChangeCallback;
    }

    public TerminalSession(String str, String str2, String[] strArr, String[] strArr2, SessionChangedCallback sessionChangedCallback) {
        this.mChangeCallback = sessionChangedCallback;
        this.mShellPath = str;
        this.mCwd = str2;
        this.mArgs = strArr;
        this.mEnv = strArr2;
    }

    public void updateSize(int i, int i2) {
        if (this.mEmulator == null) {
            initializeEmulator(i, i2);
            return;
        }
        JNI.setPtyWindowSize(this.mTerminalFileDescriptor, i2, i);
        this.mEmulator.resize(i, i2);
    }

    public String getTitle() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return null;
        }
        return terminalEmulator.getTitle();
    }

    public void initializeEmulator(int i, int i2) {
        this.mEmulator = new TerminalEmulator(this, i, i2, 2000);
        int[] iArr = new int[1];
        this.mTerminalFileDescriptor = JNI.createSubprocess(this.mShellPath, this.mCwd, this.mArgs, this.mEnv, iArr, i2, i);
        this.mShellPid = iArr[0];
        final FileDescriptor wrapFileDescriptor = wrapFileDescriptor(this.mTerminalFileDescriptor);
        new Thread("TermSessionInputReader[pid=" + this.mShellPid + "]") {
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
                r0.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x003d, code lost:
                throw r2;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x003e }
                    java.io.FileDescriptor r1 = r9     // Catch:{ Exception -> 0x003e }
                    r0.<init>(r1)     // Catch:{ Exception -> 0x003e }
                    r1 = 4096(0x1000, float:5.74E-42)
                    byte[] r1 = new byte[r1]     // Catch:{ all -> 0x0032 }
                L_0x000b:
                    int r2 = r0.read(r1)     // Catch:{ all -> 0x0032 }
                    r3 = -1
                    if (r2 != r3) goto L_0x0016
                    r0.close()     // Catch:{ Exception -> 0x003e }
                    return
                L_0x0016:
                    com.thecrackertechnology.dragonterminal.backend.TerminalSession r3 = com.thecrackertechnology.dragonterminal.backend.TerminalSession.this     // Catch:{ all -> 0x0032 }
                    com.thecrackertechnology.dragonterminal.backend.ByteQueue r3 = r3.mProcessToTerminalIOQueue     // Catch:{ all -> 0x0032 }
                    r4 = 0
                    boolean r2 = r3.write(r1, r4, r2)     // Catch:{ all -> 0x0032 }
                    if (r2 != 0) goto L_0x0027
                    r0.close()     // Catch:{ Exception -> 0x003e }
                    return
                L_0x0027:
                    com.thecrackertechnology.dragonterminal.backend.TerminalSession r2 = com.thecrackertechnology.dragonterminal.backend.TerminalSession.this     // Catch:{ all -> 0x0032 }
                    android.os.Handler r2 = r2.mMainThreadHandler     // Catch:{ all -> 0x0032 }
                    r3 = 1
                    r2.sendEmptyMessage(r3)     // Catch:{ all -> 0x0032 }
                    goto L_0x000b
                L_0x0032:
                    r1 = move-exception
                    throw r1     // Catch:{ all -> 0x0034 }
                L_0x0034:
                    r2 = move-exception
                    r0.close()     // Catch:{ all -> 0x0039 }
                    goto L_0x003d
                L_0x0039:
                    r0 = move-exception
                    r1.addSuppressed(r0)     // Catch:{ Exception -> 0x003e }
                L_0x003d:
                    throw r2     // Catch:{ Exception -> 0x003e }
                L_0x003e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalSession.AnonymousClass2.run():void");
            }
        }.start();
        new Thread("TermSessionOutputWriter[pid=" + this.mShellPid + "]") {
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0024, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
                r1.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x002d, code lost:
                throw r2;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r4 = this;
                    r0 = 4096(0x1000, float:5.74E-42)
                    byte[] r0 = new byte[r0]
                    java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002e }
                    java.io.FileDescriptor r2 = r9     // Catch:{ IOException -> 0x002e }
                    r1.<init>(r2)     // Catch:{ IOException -> 0x002e }
                L_0x000b:
                    com.thecrackertechnology.dragonterminal.backend.TerminalSession r2 = com.thecrackertechnology.dragonterminal.backend.TerminalSession.this     // Catch:{ all -> 0x0022 }
                    com.thecrackertechnology.dragonterminal.backend.ByteQueue r2 = r2.mTerminalToProcessIOQueue     // Catch:{ all -> 0x0022 }
                    r3 = 1
                    int r2 = r2.read(r0, r3)     // Catch:{ all -> 0x0022 }
                    r3 = -1
                    if (r2 != r3) goto L_0x001d
                    r1.close()     // Catch:{ IOException -> 0x002e }
                    return
                L_0x001d:
                    r3 = 0
                    r1.write(r0, r3, r2)     // Catch:{ all -> 0x0022 }
                    goto L_0x000b
                L_0x0022:
                    r0 = move-exception
                    throw r0     // Catch:{ all -> 0x0024 }
                L_0x0024:
                    r2 = move-exception
                    r1.close()     // Catch:{ all -> 0x0029 }
                    goto L_0x002d
                L_0x0029:
                    r1 = move-exception
                    r0.addSuppressed(r1)     // Catch:{ IOException -> 0x002e }
                L_0x002d:
                    throw r2     // Catch:{ IOException -> 0x002e }
                L_0x002e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalSession.AnonymousClass3.run():void");
            }
        }.start();
        new Thread("TermSessionWaiter[pid=" + this.mShellPid + "]") {
            public void run() {
                TerminalSession.this.mMainThreadHandler.sendMessage(TerminalSession.this.mMainThreadHandler.obtainMessage(4, Integer.valueOf(JNI.waitFor(TerminalSession.this.mShellPid))));
            }
        }.start();
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.mShellPid > 0) {
            this.mTerminalToProcessIOQueue.write(bArr, i, i2);
        }
    }

    public void writeCodePoint(boolean z, int i) {
        int i2;
        int i3;
        int i4;
        if (i > 1114111 || (i >= 55296 && i <= 57343)) {
            throw new IllegalArgumentException("Invalid code point: " + i);
        }
        if (z) {
            this.mUtf8InputBuffer[0] = 27;
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i <= 127) {
            i4 = i2 + 1;
            this.mUtf8InputBuffer[i2] = (byte) i;
        } else {
            if (i <= 2047) {
                byte[] bArr = this.mUtf8InputBuffer;
                int i5 = i2 + 1;
                bArr[i2] = (byte) ((i >> 6) | 192);
                i3 = i5 + 1;
                bArr[i5] = (byte) ((i & 63) | 128);
            } else if (i <= 65535) {
                byte[] bArr2 = this.mUtf8InputBuffer;
                int i6 = i2 + 1;
                bArr2[i2] = (byte) ((i >> 12) | 224);
                int i7 = i6 + 1;
                bArr2[i6] = (byte) (((i >> 6) & 63) | 128);
                i4 = i7 + 1;
                bArr2[i7] = (byte) ((i & 63) | 128);
            } else {
                byte[] bArr3 = this.mUtf8InputBuffer;
                int i8 = i2 + 1;
                bArr3[i2] = (byte) ((i >> 18) | SDL_1_2_Keycodes.SDLK_WORLD_80);
                int i9 = i8 + 1;
                bArr3[i8] = (byte) (((i >> 12) & 63) | 128);
                int i10 = i9 + 1;
                bArr3[i9] = (byte) (((i >> 6) & 63) | 128);
                i3 = i10 + 1;
                bArr3[i10] = (byte) ((i & 63) | 128);
            }
            write(this.mUtf8InputBuffer, 0, i3);
        }
        i3 = i4;
        write(this.mUtf8InputBuffer, 0, i3);
    }

    public TerminalEmulator getEmulator() {
        return this.mEmulator;
    }

    /* access modifiers changed from: private */
    public void notifyScreenUpdate() {
        this.mChangeCallback.onTextChanged(this);
    }

    public void reset() {
        this.mEmulator.reset();
        notifyScreenUpdate();
    }

    public void finishIfRunning() {
        if (isRunning()) {
            try {
                Os.kill(this.mShellPid, OsConstants.SIGKILL);
            } catch (ErrnoException e) {
                Log.w("neoterm-shell-session", "Failed sending SIGKILL: " + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getExitDescription(int i) {
        String str;
        if (i > 0) {
            str = "\r\n[ Process completed" + " (code " + i + ")";
        } else if (i < 0) {
            str = "\r\n[ Process completed" + " (signal " + (-i) + ")";
        } else {
            str = "\r\n[ Process completed" + "[ SECCOMP BLOCKED US ]";
        }
        return str + " - press Enter ]";
    }

    /* access modifiers changed from: private */
    public void cleanupResources(int i) {
        synchronized (this) {
            this.mShellPid = -1;
            this.mShellExitStatus = i;
        }
        this.mTerminalToProcessIOQueue.close();
        this.mProcessToTerminalIOQueue.close();
        JNI.close(this.mTerminalFileDescriptor);
    }

    public void titleChanged(String str, String str2) {
        this.mChangeCallback.onTitleChanged(this);
    }

    public synchronized boolean isRunning() {
        return this.mShellPid != -1;
    }

    public synchronized int getExitStatus() {
        return this.mShellExitStatus;
    }

    public void clipboardText(String str) {
        this.mChangeCallback.onClipboardText(this, str);
    }

    public void onBell() {
        this.mChangeCallback.onBell(this);
    }

    public void onColorsChanged() {
        this.mChangeCallback.onColorsChanged(this);
    }

    public int getPid() {
        return this.mShellPid;
    }
}
