package com.thecrackertechnology.dragonterminal;

import android.app.KeyguardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceView_SDL extends SurfaceView implements SurfaceHolder.Callback {
    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;
    /* access modifiers changed from: private */
    public static final Semaphore sEglSemaphore = new Semaphore(1);
    private int mDebugFlags;
    /* access modifiers changed from: private */
    public EGLConfigChooser mEGLConfigChooser;
    private GLThread mGLThread;
    /* access modifiers changed from: private */
    public GLWrapper mGLWrapper;
    /* access modifiers changed from: private */
    public KeyguardManager mKeyguardManager;
    /* access modifiers changed from: private */
    public boolean mSizeChanged = true;

    public interface EGLConfigChooser {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);

        boolean isGles2Required();

        boolean isGles3Required();
    }

    public interface GLWrapper {
        GL wrap(GL gl);
    }

    public interface SwapBuffersCallback {
        void ResetVideoSurface();

        boolean SwapBuffers();

        void onWindowResize(int i, int i2);
    }

    public GLSurfaceView_SDL(Context context) {
        super(context);
        init();
    }

    public GLSurfaceView_SDL(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setType(2);
        this.mKeyguardManager = (KeyguardManager) getContext().getSystemService("keyguard");
    }

    public void setGLWrapper(GLWrapper gLWrapper) {
        this.mGLWrapper = gLWrapper;
    }

    public void setDebugFlags(int i) {
        this.mDebugFlags = i;
    }

    public int getDebugFlags() {
        return this.mDebugFlags;
    }

    public void setRenderer(Renderer renderer) {
        if (this.mGLThread == null) {
            if (this.mEGLConfigChooser == null) {
                this.mEGLConfigChooser = getEglConfigChooser(16, false, false, false, false);
            }
            this.mGLThread = new GLThread(renderer);
            this.mGLThread.start();
            return;
        }
        throw new IllegalStateException("setRenderer has already been called for this instance.");
    }

    public void setEGLConfigChooser(EGLConfigChooser eGLConfigChooser) {
        if (this.mGLThread == null) {
            this.mEGLConfigChooser = eGLConfigChooser;
            return;
        }
        throw new IllegalStateException("setRenderer has already been called for this instance.");
    }

    public void setEGLConfigChooser(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        setEGLConfigChooser(getEglConfigChooser(i, z, z2, z3, z4));
    }

    public void setEGLConfigChooser(int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2) {
        setEGLConfigChooser(new ComponentSizeChooser(i, i2, i3, i4, i5, i6, z, z2));
    }

    public void setRenderMode(int i) {
        this.mGLThread.setRenderMode(i);
    }

    public int getRenderMode() {
        return this.mGLThread.getRenderMode();
    }

    public void requestRender() {
        this.mGLThread.requestRender();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mGLThread.surfaceCreated();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mGLThread.surfaceDestroyed();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mGLThread.onWindowResize(i2, i3);
    }

    public void onPause() {
        this.mGLThread.onPause();
    }

    public void onResume() {
        this.mGLThread.onResume();
    }

    public void queueEvent(Runnable runnable) {
        this.mGLThread.queueEvent(runnable);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mGLThread.requestExitAndWait();
    }

    public static abstract class Renderer {
        private SwapBuffersCallback mSwapBuffersCallback = null;

        public abstract void onDrawFrame(GL10 gl10);

        public abstract void onSurfaceChanged(GL10 gl10, int i, int i2);

        public abstract void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig);

        public abstract void onSurfaceDestroyed();

        public void onWindowResize(int i, int i2) {
            SwapBuffersCallback swapBuffersCallback = this.mSwapBuffersCallback;
            if (swapBuffersCallback != null) {
                swapBuffersCallback.onWindowResize(i, i2);
            }
        }

        public boolean SwapBuffers() {
            SwapBuffersCallback swapBuffersCallback = this.mSwapBuffersCallback;
            if (swapBuffersCallback != null) {
                return swapBuffersCallback.SwapBuffers();
            }
            return false;
        }

        public void ResetVideoSurface() {
            SwapBuffersCallback swapBuffersCallback = this.mSwapBuffersCallback;
            if (swapBuffersCallback != null) {
                swapBuffersCallback.ResetVideoSurface();
            }
        }

        public void setSwapBuffersCallback(SwapBuffersCallback swapBuffersCallback) {
            this.mSwapBuffersCallback = swapBuffersCallback;
        }
    }

    private static abstract class BaseConfigChooser implements EGLConfigChooser {
        protected int[] mConfigSpec;

        /* access modifiers changed from: package-private */
        public abstract EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public BaseConfigChooser(int[] iArr) {
            this.mConfigSpec = iArr;
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            egl10.eglChooseConfig(eGLDisplay, this.mConfigSpec, (EGLConfig[]) null, 0, iArr);
            int i = iArr[0];
            if (i > 0) {
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                egl10.eglChooseConfig(eGLDisplay, this.mConfigSpec, eGLConfigArr, i, iArr);
                EGLConfig chooseConfig = chooseConfig(egl10, eGLDisplay, eGLConfigArr);
                if (chooseConfig != null) {
                    return chooseConfig;
                }
                throw new IllegalArgumentException("No config chosen");
            }
            throw new IllegalArgumentException("No configs match configSpec");
        }
    }

    private static class ComponentSizeChooser extends BaseConfigChooser {
        public static final int EGL_OPENGL_BIT = 8;
        public static final int EGL_OPENGL_ES2_BIT = 4;
        public static final int EGL_OPENGL_ES3_BIT = 16;
        public static final int EGL_OPENGL_ES_BIT = 1;
        public static final int EGL_OPENVG_BIT = 2;
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected boolean mIsGles2 = false;
        protected boolean mIsGles3 = false;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue = new int[1];

        public ComponentSizeChooser(int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2) {
            super(new int[]{12344});
            this.mRedSize = i;
            this.mGreenSize = i2;
            this.mBlueSize = i3;
            this.mAlphaSize = i4;
            this.mDepthSize = i5;
            this.mStencilSize = i6;
            this.mIsGles2 = z;
            this.mIsGles3 = z2;
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            int i;
            int i2;
            String str;
            String str2;
            String str3;
            String str4;
            int i3;
            int i4;
            int i5;
            int i6;
            ComponentSizeChooser componentSizeChooser = this;
            EGLConfig[] eGLConfigArr2 = eGLConfigArr;
            StringBuilder sb = new StringBuilder();
            sb.append("Desired GL config: R");
            sb.append(componentSizeChooser.mRedSize);
            sb.append("G");
            sb.append(componentSizeChooser.mGreenSize);
            sb.append("B");
            sb.append(componentSizeChooser.mBlueSize);
            String str5 = "A";
            sb.append(str5);
            sb.append(componentSizeChooser.mAlphaSize);
            String str6 = " depth ";
            sb.append(str6);
            sb.append(componentSizeChooser.mDepthSize);
            String str7 = " stencil ";
            sb.append(str7);
            sb.append(componentSizeChooser.mStencilSize);
            String str8 = " type ";
            sb.append(str8);
            String str9 = "GLES";
            sb.append(componentSizeChooser.mIsGles3 ? "GLES3" : componentSizeChooser.mIsGles2 ? "GLES2" : str9);
            Log.v("SDL", sb.toString());
            int length = eGLConfigArr2.length;
            int i7 = 1000;
            String str10 = "";
            int i8 = -1;
            EGLConfig eGLConfig = null;
            String str11 = "SDL";
            int i9 = 0;
            int i10 = 0;
            while (i10 < length) {
                EGLConfig eGLConfig2 = eGLConfigArr2[i10];
                if (eGLConfig2 == null) {
                    i = i10;
                    i2 = length;
                    str4 = str5;
                    str2 = str6;
                    str = str8;
                    str3 = str11;
                } else {
                    int i11 = i9;
                    int i12 = i7;
                    EGL10 egl102 = egl10;
                    String str12 = str10;
                    EGLDisplay eGLDisplay2 = eGLDisplay;
                    int i13 = i8;
                    EGLConfig eGLConfig3 = eGLConfig2;
                    i = i10;
                    i2 = length;
                    int findConfigAttrib = findConfigAttrib(egl102, eGLDisplay2, eGLConfig3, 12324, 0);
                    String str13 = ": ";
                    int findConfigAttrib2 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig3, 12323, 0);
                    String str14 = str9;
                    int findConfigAttrib3 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig3, 12322, 0);
                    String str15 = str8;
                    int findConfigAttrib4 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig3, 12321, 0);
                    String str16 = str7;
                    int findConfigAttrib5 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig3, 12325, 0);
                    String str17 = str6;
                    int findConfigAttrib6 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig3, 12326, 0);
                    int findConfigAttrib7 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig3, 12352, 0);
                    int i14 = componentSizeChooser.mIsGles3 ? 16 : componentSizeChooser.mIsGles2 ? 4 : 1;
                    EGL10 egl103 = egl10;
                    EGLDisplay eGLDisplay3 = eGLDisplay;
                    EGLConfig eGLConfig4 = eGLConfig2;
                    String str18 = str5;
                    int i15 = findConfigAttrib7;
                    int findConfigAttrib8 = findConfigAttrib(egl103, eGLDisplay3, eGLConfig4, 12333, 0);
                    int findConfigAttrib9 = findConfigAttrib(egl103, eGLDisplay3, eGLConfig4, 12327, 12344);
                    int abs = Math.abs(findConfigAttrib - componentSizeChooser.mRedSize) + Math.abs(findConfigAttrib2 - componentSizeChooser.mGreenSize) + Math.abs(findConfigAttrib3 - componentSizeChooser.mBlueSize);
                    int i16 = componentSizeChooser.mAlphaSize;
                    int i17 = i16 - findConfigAttrib4 > 0 ? (i16 - findConfigAttrib4) + abs : i16 - findConfigAttrib4 < 0 ? abs + 1 : abs;
                    if ((findConfigAttrib5 > 0) != (componentSizeChooser.mDepthSize > 0)) {
                        i3 = (componentSizeChooser.mDepthSize > 0 ? 5 : 1) + i17;
                    } else {
                        i3 = i17;
                    }
                    if ((findConfigAttrib6 > 0) != (componentSizeChooser.mStencilSize > 0)) {
                        i4 = i3 + (componentSizeChooser.mStencilSize > 0 ? 5 : 1);
                    } else {
                        i4 = i3;
                    }
                    int i18 = (i15 & i14) == 0 ? i4 + 5 : i4;
                    if (findConfigAttrib9 == 12368) {
                        i6 = i18 + 4;
                        i5 = i18;
                    } else {
                        i6 = i18;
                        i5 = i6;
                    }
                    if (findConfigAttrib9 == 12369) {
                        i6++;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    int i19 = i4;
                    sb2.append("R");
                    sb2.append(findConfigAttrib);
                    sb2.append("G");
                    sb2.append(findConfigAttrib2);
                    sb2.append("B");
                    sb2.append(findConfigAttrib3);
                    str4 = str18;
                    sb2.append(str4);
                    sb2.append(findConfigAttrib4);
                    str2 = str17;
                    sb2.append(str2);
                    sb2.append(findConfigAttrib5);
                    str7 = str16;
                    sb2.append(str7);
                    sb2.append(findConfigAttrib6);
                    str = str15;
                    sb2.append(str);
                    sb2.append(i15);
                    sb2.append(" (");
                    String sb3 = sb2.toString();
                    if ((i15 & 1) != 0) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(sb3);
                        str9 = str14;
                        sb4.append(str9);
                        sb3 = sb4.toString();
                    } else {
                        str9 = str14;
                    }
                    if ((i15 & 4) != 0) {
                        sb3 = sb3 + " GLES2";
                    }
                    if ((i15 & 16) != 0) {
                        sb3 = sb3 + " GLES3";
                    }
                    if ((i15 & 8) != 0) {
                        sb3 = sb3 + " OPENGL";
                    }
                    if ((i15 & 2) != 0) {
                        sb3 = sb3 + " OPENVG";
                    }
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(sb3 + ")");
                    sb5.append(" caveat ");
                    sb5.append(findConfigAttrib9 == 12344 ? "none" : findConfigAttrib9 == 12368 ? "SLOW" : findConfigAttrib9 == 12369 ? "non-conformant" : String.valueOf(findConfigAttrib9));
                    String str19 = (sb5.toString() + " nr " + findConfigAttrib8) + " pos " + i6 + " (" + abs + "," + i17 + "," + i3 + "," + i19 + "," + i5 + ")";
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("GL config ");
                    int i20 = i11;
                    sb6.append(i20);
                    sb6.append(str13);
                    sb6.append(str19);
                    str3 = str11;
                    Log.v(str3, sb6.toString());
                    i7 = i12;
                    if (i6 < i7) {
                        str12 = new String(str19);
                        i13 = i20;
                        i7 = i6;
                        eGLConfig = eGLConfig2;
                    }
                    i9 = i20 + 1;
                    str10 = str12;
                    i8 = i13;
                }
                str5 = str4;
                str11 = str3;
                i10 = i + 1;
                str8 = str;
                length = i2;
                componentSizeChooser = this;
                str6 = str2;
                eGLConfigArr2 = eGLConfigArr;
            }
            String str20 = str10;
            Log.v(str11, "GLSurfaceView_SDL::EGLConfigChooser::chooseConfig(): selected " + i8 + ": " + str10);
            return eGLConfig;
        }

        private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            int[] iArr = this.mValue;
            iArr[0] = -1;
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, iArr)) {
                return this.mValue[0];
            }
            Log.w("SDL", "GLSurfaceView_SDL::EGLConfigChooser::findConfigAttrib(): attribute doesn't exist: " + i);
            return i2;
        }

        public boolean isGles2Required() {
            return this.mIsGles2;
        }

        public boolean isGles3Required() {
            return this.mIsGles3;
        }
    }

    private static class SimpleEGLConfigChooser16 extends ComponentSizeChooser {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SimpleEGLConfigChooser16(boolean z, boolean z2, boolean z3, boolean z4) {
            super(4, 4, 4, 0, z ? 16 : 0, z2 ? 8 : 0, z3, z4);
            this.mRedSize = 5;
            this.mGreenSize = 6;
            this.mBlueSize = 5;
        }
    }

    private static class SimpleEGLConfigChooser24 extends ComponentSizeChooser {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SimpleEGLConfigChooser24(boolean z, boolean z2, boolean z3, boolean z4) {
            super(8, 8, 8, 0, z ? 16 : 0, z2 ? 8 : 0, z3, z4);
            this.mRedSize = 8;
            this.mGreenSize = 8;
            this.mBlueSize = 8;
        }
    }

    private static class SimpleEGLConfigChooser32 extends ComponentSizeChooser {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SimpleEGLConfigChooser32(boolean z, boolean z2, boolean z3, boolean z4) {
            super(8, 8, 8, 8, z ? 16 : 0, z2 ? 8 : 0, z3, z4);
            this.mRedSize = 8;
            this.mGreenSize = 8;
            this.mBlueSize = 8;
            this.mAlphaSize = 8;
        }
    }

    private static ComponentSizeChooser getEglConfigChooser(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        if (i == 16) {
            return new SimpleEGLConfigChooser16(z, z2, z3, z4);
        }
        if (i == 24) {
            return new SimpleEGLConfigChooser24(z, z2, z3, z4);
        }
        if (i == 32) {
            return new SimpleEGLConfigChooser32(z, z2, z3, z4);
        }
        return null;
    }

    private class EglHelper {
        EGL10 mEgl;
        EGLConfig mEglConfig;
        EGLContext mEglContext;
        EGLDisplay mEglDisplay;
        EGLSurface mEglSurface;

        public EglHelper() {
        }

        public void start() {
            Log.v("SDL", "GLSurfaceView_SDL::EglHelper::start(): creating GL context");
            this.mEgl = (EGL10) EGLContext.getEGL();
            this.mEglDisplay = this.mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.mEgl.eglInitialize(this.mEglDisplay, new int[2]);
            this.mEglConfig = GLSurfaceView_SDL.this.mEGLConfigChooser.chooseConfig(this.mEgl, this.mEglDisplay);
            if (this.mEglConfig == null) {
                Log.e("SDL", "GLSurfaceView_SDL::EglHelper::start(): mEglConfig is NULL");
            }
            int[] iArr = {12440, 2, 12344};
            int[] iArr2 = {12440, 3, 12344};
            EGL10 egl10 = this.mEgl;
            EGLDisplay eGLDisplay = this.mEglDisplay;
            EGLConfig eGLConfig = this.mEglConfig;
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (!GLSurfaceView_SDL.this.mEGLConfigChooser.isGles3Required()) {
                iArr2 = GLSurfaceView_SDL.this.mEGLConfigChooser.isGles2Required() ? iArr : null;
            }
            this.mEglContext = egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr2);
            EGLContext eGLContext2 = this.mEglContext;
            if (eGLContext2 == null || eGLContext2 == EGL10.EGL_NO_CONTEXT) {
                Log.e("SDL", "GLSurfaceView_SDL::EglHelper::start(): mEglContext is EGL_NO_CONTEXT, error: " + this.mEgl.eglGetError());
            }
            this.mEglSurface = null;
        }

        public GL createSurface(SurfaceHolder surfaceHolder) {
            Log.v("SDL", "GLSurfaceView_SDL::EglHelper::createSurface(): creating GL context");
            if (this.mEglSurface != null) {
                this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.mEgl.eglDestroySurface(this.mEglDisplay, this.mEglSurface);
            }
            this.mEglSurface = this.mEgl.eglCreateWindowSurface(this.mEglDisplay, this.mEglConfig, surfaceHolder, (int[]) null);
            EGL10 egl10 = this.mEgl;
            EGLDisplay eGLDisplay = this.mEglDisplay;
            EGLSurface eGLSurface = this.mEglSurface;
            egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.mEglContext);
            GL gl = this.mEglContext.getGL();
            return GLSurfaceView_SDL.this.mGLWrapper != null ? GLSurfaceView_SDL.this.mGLWrapper.wrap(gl) : gl;
        }

        public boolean swap() {
            this.mEgl.eglSwapBuffers(this.mEglDisplay, this.mEglSurface);
            return this.mEgl.eglGetError() != 12302;
        }

        public void finish() {
            Log.v("SDL", "GLSurfaceView_SDL::EglHelper::finish(): destroying GL context");
            if (this.mEglSurface != null) {
                this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.mEgl.eglDestroySurface(this.mEglDisplay, this.mEglSurface);
                this.mEglSurface = null;
            }
            EGLContext eGLContext = this.mEglContext;
            if (eGLContext != null) {
                this.mEgl.eglDestroyContext(this.mEglDisplay, eGLContext);
                this.mEglContext = null;
            }
            EGLDisplay eGLDisplay = this.mEglDisplay;
            if (eGLDisplay != null) {
                this.mEgl.eglTerminate(eGLDisplay);
                this.mEglDisplay = null;
            }
        }
    }

    class GLThread extends Thread implements SwapBuffersCallback {
        private boolean mDone = false;
        private EglHelper mEglHelper;
        private ArrayList<Runnable> mEventQueue = new ArrayList<>();
        private GL10 mGL = null;
        private boolean mHasSurface;
        private int mHeight = 0;
        private boolean mNeedStart = false;
        private boolean mPaused;
        private int mRenderMode = 1;
        private Renderer mRenderer;
        private boolean mRequestRender = true;
        private boolean mResetVideoSurface = false;
        private int mWidth = 0;

        GLThread(Renderer renderer) {
            this.mRenderer = renderer;
            this.mRenderer.setSwapBuffersCallback(this);
            setName("GLThread");
        }

        public void run() {
            try {
                GLSurfaceView_SDL.sEglSemaphore.acquire();
                this.mEglHelper = new EglHelper();
                this.mNeedStart = true;
                boolean unused = GLSurfaceView_SDL.this.mSizeChanged = true;
                SwapBuffers();
                this.mRenderer.onDrawFrame(this.mGL);
                this.mEglHelper.finish();
                GLSurfaceView_SDL.sEglSemaphore.release();
            } catch (InterruptedException unused2) {
            }
        }

        public void ResetVideoSurface() {
            this.mResetVideoSurface = true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
            if (needToWait() == false) goto L_0x0039;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
            monitor-enter(r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            wait(500);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            android.util.Log.v("SDL", "GLSurfaceView_SDL::GLThread::SwapBuffers(): Who dared to interrupt my slumber?");
            java.lang.Thread.interrupted();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0039, code lost:
            monitor-enter(r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x003c, code lost:
            if (r8.mDone == false) goto L_0x0040;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x003e, code lost:
            monitor-exit(r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x003f, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0040, code lost:
            r3 = r8.mWidth;
            r5 = r8.mHeight;
            com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL.access$302(r8.this$0, false);
            r8.mRequestRender = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x004b, code lost:
            monitor-exit(r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x004e, code lost:
            if (r8.mNeedStart == false) goto L_0x005a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0050, code lost:
            r8.mEglHelper.start();
            r8.mNeedStart = false;
            r1 = true;
            r6 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x005a, code lost:
            r6 = r1;
            r1 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x005c, code lost:
            if (r1 == false) goto L_0x006f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x005e, code lost:
            r8.mGL = (javax.microedition.khronos.opengles.GL10) r8.mEglHelper.createSurface(r8.this$0.getHolder());
            r2 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x006f, code lost:
            if (r6 == false) goto L_0x007e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0071, code lost:
            r8.mRenderer.onSurfaceCreated(r8.mGL, r8.mEglHelper.mEglConfig);
            r1 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x007e, code lost:
            r1 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x007f, code lost:
            if (r2 == false) goto L_0x0089;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0081, code lost:
            r8.mRenderer.onSurfaceChanged(r8.mGL, r3, r5);
            r2 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x008b, code lost:
            if (r8.mResetVideoSurface != false) goto L_0x0096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0093, code lost:
            if (r8.mEglHelper.swap() == false) goto L_0x0096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0095, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0096, code lost:
            r8.mResetVideoSurface = false;
            r8.mRenderer.onSurfaceDestroyed();
            r8.mEglHelper.finish();
            r8.mNeedStart = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean SwapBuffers() {
            /*
                r8 = this;
                r0 = 0
                r1 = 0
                r2 = 0
            L_0x0003:
                monitor-enter(r8)
                boolean r3 = r8.mPaused     // Catch:{ all -> 0x00ac }
                r4 = 1
                if (r3 == 0) goto L_0x001b
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$Renderer r3 = r8.mRenderer     // Catch:{ all -> 0x00ac }
                r3.onSurfaceDestroyed()     // Catch:{ all -> 0x00ac }
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$EglHelper r3 = r8.mEglHelper     // Catch:{ all -> 0x00ac }
                r3.finish()     // Catch:{ all -> 0x00ac }
                r8.mNeedStart = r4     // Catch:{ all -> 0x00ac }
                boolean r3 = com.thecrackertechnology.dragonterminal.Globals.NonBlockingSwapBuffers     // Catch:{ all -> 0x00ac }
                if (r3 == 0) goto L_0x001b
                monitor-exit(r8)     // Catch:{ all -> 0x00ac }
                return r0
            L_0x001b:
                monitor-exit(r8)     // Catch:{ all -> 0x00ac }
            L_0x001c:
                boolean r3 = r8.needToWait()
                if (r3 == 0) goto L_0x0039
                monitor-enter(r8)
                r5 = 500(0x1f4, double:2.47E-321)
                r8.wait(r5)     // Catch:{ InterruptedException -> 0x002b }
                goto L_0x0035
            L_0x0029:
                r0 = move-exception
                goto L_0x0037
            L_0x002b:
                java.lang.String r3 = "SDL"
                java.lang.String r5 = "GLSurfaceView_SDL::GLThread::SwapBuffers(): Who dared to interrupt my slumber?"
                android.util.Log.v(r3, r5)     // Catch:{ all -> 0x0029 }
                java.lang.Thread.interrupted()     // Catch:{ all -> 0x0029 }
            L_0x0035:
                monitor-exit(r8)     // Catch:{ all -> 0x0029 }
                goto L_0x001c
            L_0x0037:
                monitor-exit(r8)     // Catch:{ all -> 0x0029 }
                throw r0
            L_0x0039:
                monitor-enter(r8)
                boolean r3 = r8.mDone     // Catch:{ all -> 0x00a9 }
                if (r3 == 0) goto L_0x0040
                monitor-exit(r8)     // Catch:{ all -> 0x00a9 }
                return r0
            L_0x0040:
                int r3 = r8.mWidth     // Catch:{ all -> 0x00a9 }
                int r5 = r8.mHeight     // Catch:{ all -> 0x00a9 }
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL r6 = com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL.this     // Catch:{ all -> 0x00a9 }
                boolean unused = r6.mSizeChanged = r0     // Catch:{ all -> 0x00a9 }
                r8.mRequestRender = r0     // Catch:{ all -> 0x00a9 }
                monitor-exit(r8)     // Catch:{ all -> 0x00a9 }
                boolean r6 = r8.mNeedStart
                if (r6 == 0) goto L_0x005a
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$EglHelper r1 = r8.mEglHelper
                r1.start()
                r8.mNeedStart = r0
                r1 = 1
                r6 = 1
                goto L_0x005c
            L_0x005a:
                r6 = r1
                r1 = 0
            L_0x005c:
                if (r1 == 0) goto L_0x006f
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$EglHelper r1 = r8.mEglHelper
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL r2 = com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL.this
                android.view.SurfaceHolder r2 = r2.getHolder()
                javax.microedition.khronos.opengles.GL r1 = r1.createSurface(r2)
                javax.microedition.khronos.opengles.GL10 r1 = (javax.microedition.khronos.opengles.GL10) r1
                r8.mGL = r1
                r2 = 1
            L_0x006f:
                if (r6 == 0) goto L_0x007e
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$Renderer r1 = r8.mRenderer
                javax.microedition.khronos.opengles.GL10 r6 = r8.mGL
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$EglHelper r7 = r8.mEglHelper
                javax.microedition.khronos.egl.EGLConfig r7 = r7.mEglConfig
                r1.onSurfaceCreated(r6, r7)
                r1 = 0
                goto L_0x007f
            L_0x007e:
                r1 = r6
            L_0x007f:
                if (r2 == 0) goto L_0x0089
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$Renderer r2 = r8.mRenderer
                javax.microedition.khronos.opengles.GL10 r6 = r8.mGL
                r2.onSurfaceChanged(r6, r3, r5)
                r2 = 0
            L_0x0089:
                boolean r3 = r8.mResetVideoSurface
                if (r3 != 0) goto L_0x0096
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$EglHelper r3 = r8.mEglHelper
                boolean r3 = r3.swap()
                if (r3 == 0) goto L_0x0096
                return r4
            L_0x0096:
                r8.mResetVideoSurface = r0
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$Renderer r3 = r8.mRenderer
                r3.onSurfaceDestroyed()
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL$EglHelper r3 = r8.mEglHelper
                r3.finish()
                r8.mNeedStart = r4
                boolean r3 = com.thecrackertechnology.dragonterminal.Globals.NonBlockingSwapBuffers
                if (r3 == 0) goto L_0x0003
                return r0
            L_0x00a9:
                r0 = move-exception
                monitor-exit(r8)     // Catch:{ all -> 0x00a9 }
                throw r0
            L_0x00ac:
                r0 = move-exception
                monitor-exit(r8)     // Catch:{ all -> 0x00ac }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL.GLThread.SwapBuffers():boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:30:0x003f, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0041, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0043, code lost:
            return true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean needToWait() {
            /*
                r5 = this;
                com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL r0 = com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL.this
                android.app.KeyguardManager r0 = r0.mKeyguardManager
                boolean r0 = r0.inKeyguardRestrictedInputMode()
                r1 = 1
                if (r0 == 0) goto L_0x000e
                return r1
            L_0x000e:
                monitor-enter(r5)
                boolean r0 = r5.mDone     // Catch:{ all -> 0x0044 }
                r2 = 0
                if (r0 == 0) goto L_0x0016
                monitor-exit(r5)     // Catch:{ all -> 0x0044 }
                return r2
            L_0x0016:
                boolean r0 = com.thecrackertechnology.dragonterminal.Globals.HorizontalOrientation     // Catch:{ all -> 0x0044 }
                int r3 = r5.mWidth     // Catch:{ all -> 0x0044 }
                int r4 = r5.mHeight     // Catch:{ all -> 0x0044 }
                if (r3 <= r4) goto L_0x0020
                r3 = 1
                goto L_0x0021
            L_0x0020:
                r3 = 0
            L_0x0021:
                if (r0 == r3) goto L_0x0025
                monitor-exit(r5)     // Catch:{ all -> 0x0044 }
                return r1
            L_0x0025:
                boolean r0 = r5.mPaused     // Catch:{ all -> 0x0044 }
                if (r0 != 0) goto L_0x0042
                boolean r0 = r5.mHasSurface     // Catch:{ all -> 0x0044 }
                if (r0 != 0) goto L_0x002e
                goto L_0x0042
            L_0x002e:
                int r0 = r5.mWidth     // Catch:{ all -> 0x0044 }
                if (r0 <= 0) goto L_0x0040
                int r0 = r5.mHeight     // Catch:{ all -> 0x0044 }
                if (r0 <= 0) goto L_0x0040
                boolean r0 = r5.mRequestRender     // Catch:{ all -> 0x0044 }
                if (r0 != 0) goto L_0x003e
                int r0 = r5.mRenderMode     // Catch:{ all -> 0x0044 }
                if (r0 != r1) goto L_0x0040
            L_0x003e:
                monitor-exit(r5)     // Catch:{ all -> 0x0044 }
                return r2
            L_0x0040:
                monitor-exit(r5)     // Catch:{ all -> 0x0044 }
                return r1
            L_0x0042:
                monitor-exit(r5)     // Catch:{ all -> 0x0044 }
                return r1
            L_0x0044:
                r0 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0044 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL.GLThread.needToWait():boolean");
        }

        public void setRenderMode(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (this) {
                this.mRenderMode = i;
                if (i == 1) {
                    notify();
                }
            }
        }

        public int getRenderMode() {
            int i;
            synchronized (this) {
                i = this.mRenderMode;
            }
            return i;
        }

        public void requestRender() {
            synchronized (this) {
                this.mRequestRender = true;
                notify();
            }
        }

        public void surfaceCreated() {
            synchronized (this) {
                this.mHasSurface = true;
                notify();
            }
        }

        public void surfaceDestroyed() {
            synchronized (this) {
                this.mHasSurface = false;
                notify();
            }
        }

        public void onPause() {
            Log.v("SDL", "GLSurfaceView_SDL::onPause()");
            synchronized (this) {
                this.mPaused = true;
            }
        }

        public void onResume() {
            Log.v("SDL", "GLSurfaceView_SDL::onResume()");
            synchronized (this) {
                this.mPaused = false;
                notify();
            }
        }

        public void onWindowResize(int i, int i2) {
            Log.v("SDL", "GLSurfaceView_SDL::onWindowResize(): " + i + "x" + i2);
            synchronized (this) {
                this.mWidth = i;
                this.mHeight = i2;
                boolean unused = GLSurfaceView_SDL.this.mSizeChanged = true;
                this.mRenderer.onWindowResize(i, i2);
                notify();
            }
        }

        public void requestExitAndWait() {
            Log.v("SDL", "GLSurfaceView_SDL::requestExitAndWait()");
            synchronized (this) {
                this.mDone = true;
                notify();
            }
            try {
                join();
            } catch (InterruptedException unused) {
            }
        }

        public void queueEvent(Runnable runnable) {
            synchronized (this) {
                this.mEventQueue.add(runnable);
            }
        }

        private Runnable getEvent() {
            synchronized (this) {
                if (this.mEventQueue.size() <= 0) {
                    return null;
                }
                Runnable remove = this.mEventQueue.remove(0);
                return remove;
            }
        }
    }

    static class LogWriter extends Writer {
        private StringBuilder mBuilder = new StringBuilder();

        LogWriter() {
        }

        public void close() {
            flushBuilder();
        }

        public void flush() {
            flushBuilder();
        }

        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == 10) {
                    flushBuilder();
                } else {
                    this.mBuilder.append(c);
                }
            }
        }

        private void flushBuilder() {
            if (this.mBuilder.length() > 0) {
                Log.v("GLSurfaceView", this.mBuilder.toString());
                StringBuilder sb = this.mBuilder;
                sb.delete(0, sb.length());
            }
        }
    }
}
