package com.thecrackertechnology.dragonterminal;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.widget.Toast;
import java.lang.reflect.Method;

/* compiled from: Video */
abstract class DifferentTouchInput {
    public static int ExternalMouseDetected;
    /* access modifiers changed from: private */
    public static int[] gamepadIds = new int[4];
    public static DifferentTouchInput touchInput = getInstance();

    public abstract void process(MotionEvent motionEvent);

    public abstract void processGenericEvent(MotionEvent motionEvent);

    DifferentTouchInput() {
    }

    public static DifferentTouchInput getInstance() {
        boolean z = false;
        boolean z2 = false;
        for (Method method : MotionEvent.class.getDeclaredMethods()) {
            if (method.getName().equals("getPointerCount")) {
                z = true;
            }
            if (method.getName().equals("getPointerId")) {
                z2 = true;
            }
        }
        try {
            Log.i("SDL", "Device: " + Build.DEVICE);
            Log.i("SDL", "Device name: " + Build.DISPLAY);
            Log.i("SDL", "Device model: " + Build.MODEL);
            Log.i("SDL", "Device board: " + Build.BOARD);
            if (Build.VERSION.SDK_INT >= 14) {
                return AutoDetectTouchInput.Holder.sInstance;
            }
            if (Build.VERSION.SDK_INT >= 9) {
                return GingerbreadTouchInput.Holder.sInstance;
            }
            if (!z || !z2) {
                return SingleTouchInput.Holder.sInstance;
            }
            return MultiTouchInput.Holder.sInstance;
        } catch (Exception unused) {
            if (!z || !z2) {
                return SingleTouchInput.Holder.sInstance;
            }
            try {
                return MultiTouchInput.Holder.sInstance;
            } catch (Exception unused2) {
                return SingleTouchInput.Holder.sInstance;
            }
        }
    }

    /* compiled from: Video */
    private static class SingleTouchInput extends DifferentTouchInput {
        private SingleTouchInput() {
        }

        /* compiled from: Video */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final SingleTouchInput sInstance = new SingleTouchInput();

            private Holder() {
            }
        }

        public void processGenericEvent(MotionEvent motionEvent) {
            process(motionEvent);
        }

        public void process(MotionEvent motionEvent) {
            int i = motionEvent.getAction() == 0 ? 0 : -1;
            if (motionEvent.getAction() == 1) {
                i = 1;
            }
            int i2 = motionEvent.getAction() == 2 ? 2 : i;
            if (i2 >= 0) {
                DemoGLSurfaceView.nativeMotionEvent((int) motionEvent.getX(), (int) motionEvent.getY(), i2, 0, (int) (motionEvent.getPressure() * 1024.0f), (int) (motionEvent.getSize() * 1024.0f));
            }
        }
    }

    /* compiled from: Video */
    private static class MultiTouchInput extends DifferentTouchInput {
        public static final int TOUCH_EVENTS_MAX = 16;
        protected touchEvent[] touchEvents = new touchEvent[16];

        /* compiled from: Video */
        private class touchEvent {
            public boolean down;
            public int pressure;
            public int size;
            public int x;
            public int y;

            private touchEvent() {
                this.down = false;
                this.x = 0;
                this.y = 0;
                this.pressure = 0;
                this.size = 0;
            }
        }

        MultiTouchInput() {
            for (int i = 0; i < 16; i++) {
                this.touchEvents[i] = new touchEvent();
            }
        }

        /* compiled from: Video */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final MultiTouchInput sInstance = new MultiTouchInput();

            private Holder() {
            }
        }

        public void processGenericEvent(MotionEvent motionEvent) {
            process(motionEvent);
        }

        public void process(MotionEvent motionEvent) {
            int i;
            if ((motionEvent.getAction() & 255) == 1 || (motionEvent.getAction() & 255) == 3) {
                for (int i2 = 0; i2 < 16; i2++) {
                    if (this.touchEvents[i2].down) {
                        touchEvent[] toucheventArr = this.touchEvents;
                        toucheventArr[i2].down = false;
                        DemoGLSurfaceView.nativeMotionEvent(toucheventArr[i2].x, this.touchEvents[i2].y, 1, i2, this.touchEvents[i2].pressure, this.touchEvents[i2].size);
                    }
                }
            }
            if ((motionEvent.getAction() & 255) == 0) {
                for (int i3 = 0; i3 < motionEvent.getPointerCount(); i3++) {
                    int pointerId = motionEvent.getPointerId(i3);
                    int i4 = pointerId >= 16 ? 15 : pointerId;
                    touchEvent[] toucheventArr2 = this.touchEvents;
                    toucheventArr2[i4].down = true;
                    toucheventArr2[i4].x = (int) motionEvent.getX(i3);
                    this.touchEvents[i4].y = (int) motionEvent.getY(i3);
                    this.touchEvents[i4].pressure = (int) (motionEvent.getPressure(i3) * 1024.0f);
                    this.touchEvents[i4].size = (int) (motionEvent.getSize(i3) * 1024.0f);
                    DemoGLSurfaceView.nativeMotionEvent(this.touchEvents[i4].x, this.touchEvents[i4].y, 0, i4, this.touchEvents[i4].pressure, this.touchEvents[i4].size);
                }
            }
            if ((motionEvent.getAction() & 255) == 2 || (motionEvent.getAction() & 255) == 5 || (motionEvent.getAction() & 255) == 6) {
                int i5 = -1;
                if ((motionEvent.getAction() & 255) == 6) {
                    i5 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                }
                int i6 = 0;
                while (i6 < 16) {
                    int i7 = 0;
                    while (i7 < motionEvent.getPointerCount() && i6 != motionEvent.getPointerId(i7)) {
                        i7++;
                    }
                    if (i7 < motionEvent.getPointerCount()) {
                        if (i5 == i6 && this.touchEvents[i5].down) {
                            this.touchEvents[i6].down = false;
                            i = 1;
                        } else if (this.touchEvents[i6].down) {
                            i = 2;
                        } else {
                            this.touchEvents[i6].down = true;
                            i = 0;
                        }
                        this.touchEvents[i6].x = (int) motionEvent.getX(i7);
                        this.touchEvents[i6].y = (int) motionEvent.getY(i7);
                        this.touchEvents[i6].pressure = (int) (motionEvent.getPressure(i7) * 1024.0f);
                        this.touchEvents[i6].size = (int) (motionEvent.getSize(i7) * 1024.0f);
                        DemoGLSurfaceView.nativeMotionEvent(this.touchEvents[i6].x, this.touchEvents[i6].y, i, i6, this.touchEvents[i6].pressure, this.touchEvents[i6].size);
                    } else if (this.touchEvents[i6].down) {
                        touchEvent[] toucheventArr3 = this.touchEvents;
                        toucheventArr3[i6].down = false;
                        DemoGLSurfaceView.nativeMotionEvent(toucheventArr3[i6].x, this.touchEvents[i6].y, 1, i6, this.touchEvents[i6].pressure, this.touchEvents[i6].size);
                    }
                    i6++;
                }
            }
        }
    }

    /* compiled from: Video */
    private static class GingerbreadTouchInput extends MultiTouchInput {

        /* compiled from: Video */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final GingerbreadTouchInput sInstance = new GingerbreadTouchInput();

            private Holder() {
            }
        }

        GingerbreadTouchInput() {
        }

        public void process(MotionEvent motionEvent) {
            int i = ((motionEvent.getSource() & 8194) == 8194 || Globals.ForceHardwareMouse) ? 2 : (motionEvent.getSource() & InputDeviceCompat.SOURCE_STYLUS) == 16386 ? 1 : 0;
            if (ExternalMouseDetected != i) {
                ExternalMouseDetected = i;
                DemoGLSurfaceView.nativeHardwareMouseDetected(i);
            }
            super.process(motionEvent);
            if (Globals.FingerHover || ExternalMouseDetected != 0) {
                if ((motionEvent.getAction() & 255) == 7) {
                    int i2 = this.touchEvents[0].down ? 1 : 3;
                    this.touchEvents[0].down = false;
                    this.touchEvents[0].x = (int) motionEvent.getX();
                    this.touchEvents[0].y = (int) motionEvent.getY();
                    this.touchEvents[0].pressure = 1024;
                    this.touchEvents[0].size = 0;
                    InputDevice device = InputDevice.getDevice(motionEvent.getDeviceId());
                    if (!(device == null || device.getMotionRange(24) == null || device.getMotionRange(24).getRange() <= 0.0f)) {
                        this.touchEvents[0].pressure = (int) (((motionEvent.getAxisValue(24) - device.getMotionRange(24).getMin()) * 1024.0f) / device.getMotionRange(24).getRange());
                    }
                    DemoGLSurfaceView.nativeMotionEvent(this.touchEvents[0].x, this.touchEvents[0].y, i2, 0, this.touchEvents[0].pressure, this.touchEvents[0].size);
                }
                if ((motionEvent.getAction() & 255) == 10) {
                    this.touchEvents[0].pressure = Mouse.HOVER_REDRAW_SCREEN;
                    this.touchEvents[0].size = 0;
                    DemoGLSurfaceView.nativeMotionEvent(this.touchEvents[0].x, this.touchEvents[0].y, 3, 0, this.touchEvents[0].pressure, this.touchEvents[0].size);
                }
            }
        }

        public void processGenericEvent(MotionEvent motionEvent) {
            process(motionEvent);
        }
    }

    /* compiled from: Video */
    private static class IcsTouchInput extends GingerbreadTouchInput {
        private int buttonState;

        private IcsTouchInput() {
            this.buttonState = 0;
        }

        /* compiled from: Video */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final IcsTouchInput sInstance = new IcsTouchInput();

            private Holder() {
            }
        }

        public void process(MotionEvent motionEvent) {
            int buttonState2 = motionEvent.getButtonState();
            if (buttonState2 != this.buttonState) {
                int i = 1;
                int i2 = 1;
                while (true) {
                    int i3 = 0;
                    if (i2 > 16) {
                        break;
                    }
                    int i4 = buttonState2 & i2;
                    if (i4 != (this.buttonState & i2)) {
                        if (i4 != 0) {
                            i3 = 1;
                        }
                        DemoGLSurfaceView.nativeMouseButtonsPressed(i2, i3);
                    }
                    i2 *= 2;
                }
                int i5 = buttonState2 & 32;
                if (i5 != (this.buttonState & 32)) {
                    DemoGLSurfaceView.nativeMouseButtonsPressed(2, i5 == 0 ? 0 : 1);
                }
                int i6 = buttonState2 & 64;
                if (i6 != (this.buttonState & 64)) {
                    if (i6 == 0) {
                        i = 0;
                    }
                    DemoGLSurfaceView.nativeMouseButtonsPressed(4, i);
                }
                this.buttonState = buttonState2;
            }
            super.process(motionEvent);
        }

        public void processGenericEvent(MotionEvent motionEvent) {
            if ((motionEvent.getSource() & 16) == 16) {
                DemoGLSurfaceView.nativeGamepadAnalogJoystickInput(motionEvent.getAxisValue(0), motionEvent.getAxisValue(1), motionEvent.getAxisValue(11), motionEvent.getAxisValue(14), motionEvent.getAxisValue(17), motionEvent.getAxisValue(18), motionEvent.getAxisValue(15), motionEvent.getAxisValue(16), processGamepadDeviceId(motionEvent.getDevice()));
            } else if (motionEvent.getAction() == 8) {
                DemoGLSurfaceView.nativeMouseWheel(Math.round(motionEvent.getAxisValue(10)), Math.round(motionEvent.getAxisValue(9)));
            } else {
                super.processGenericEvent(motionEvent);
            }
        }
    }

    /* compiled from: Video */
    private static class IcsTouchInputWithHistory extends IcsTouchInput {
        private IcsTouchInputWithHistory() {
            super();
        }

        /* compiled from: Video */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final IcsTouchInputWithHistory sInstance = new IcsTouchInputWithHistory();

            private Holder() {
            }
        }

        public void process(MotionEvent motionEvent) {
            int i = 0;
            while (i < 16 && !this.touchEvents[i].down) {
                i++;
            }
            if (i >= 16) {
                i = 0;
            }
            for (int i2 = 0; i2 < motionEvent.getHistorySize(); i2++) {
                DemoGLSurfaceView.nativeMotionEvent((int) motionEvent.getHistoricalX(i2), (int) motionEvent.getHistoricalY(i2), 2, i, (int) (motionEvent.getHistoricalPressure(i2) * 1024.0f), (int) (motionEvent.getHistoricalSize(i2) * 1024.0f));
            }
            super.process(motionEvent);
        }
    }

    /* compiled from: Video */
    private static class CrappyMtkTabletWithBrokenTouchDrivers extends IcsTouchInput {
        private CrappyMtkTabletWithBrokenTouchDrivers() {
            super();
        }

        /* compiled from: Video */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final CrappyMtkTabletWithBrokenTouchDrivers sInstance = new CrappyMtkTabletWithBrokenTouchDrivers();

            private Holder() {
            }
        }

        public void process(MotionEvent motionEvent) {
            if ((motionEvent.getAction() & 255) != 7 && (motionEvent.getAction() & 255) != 10) {
                super.process(motionEvent);
            }
        }

        public void processGenericEvent(MotionEvent motionEvent) {
            if ((motionEvent.getAction() & 255) != 7 && (motionEvent.getAction() & 255) != 10) {
                super.processGenericEvent(motionEvent);
            }
        }
    }

    /* compiled from: Video */
    private static class AutoDetectTouchInput extends IcsTouchInput {
        boolean fingerHover;
        boolean hover;
        long hoverTime;
        float hoverTouchDistance;
        float hoverX;
        float hoverY;
        boolean tap;
        int tapCount;
        long tapTime;
        float tapX;
        float tapY;

        private AutoDetectTouchInput() {
            super();
            this.tapCount = 0;
            this.hover = false;
            this.fingerHover = false;
            this.tap = false;
            this.hoverX = 0.0f;
            this.hoverY = 0.0f;
            this.hoverTime = 0;
            this.tapX = 0.0f;
            this.tapY = 0.0f;
            this.tapTime = 0;
            this.hoverTouchDistance = 0.0f;
        }

        /* compiled from: Video */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final AutoDetectTouchInput sInstance = new AutoDetectTouchInput();

            private Holder() {
            }
        }

        public void process(MotionEvent motionEvent) {
            if ((motionEvent.getAction() & 255) == 1 || (motionEvent.getAction() & 255) == 0) {
                this.tapCount++;
                if ((motionEvent.getAction() & 255) == 1) {
                    this.tap = true;
                    this.tapX = motionEvent.getX();
                    this.tapY = motionEvent.getY();
                    this.tapTime = System.currentTimeMillis();
                    if (this.hover) {
                        Log.i("SDL", "Tap tapX " + motionEvent.getX() + " tapY " + motionEvent.getX());
                    }
                } else if (this.hover && System.currentTimeMillis() < this.hoverTime + 1000) {
                    this.hoverTouchDistance += Math.abs(this.hoverX - motionEvent.getX()) + Math.abs(this.hoverY - motionEvent.getY());
                    Log.i("SDL", "Finger down event.getX() " + motionEvent.getX() + " hoverX " + this.hoverX + " event.getY() " + motionEvent.getY() + " hoverY " + this.hoverY + " hoverTouchDistance " + this.hoverTouchDistance);
                }
            }
            if (this.tapCount >= 4) {
                int i = 800;
                try {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    MainActivity.instance.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    i = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
                } catch (Exception unused) {
                }
                StringBuilder sb = new StringBuilder();
                sb.append("AutoDetectTouchInput: hoverTouchDistance ");
                sb.append(this.hoverTouchDistance);
                sb.append(" threshold ");
                int i2 = i / 2;
                sb.append(i2);
                sb.append(" hover ");
                sb.append(this.hover);
                sb.append(" fingerHover ");
                sb.append(this.fingerHover);
                Log.i("SDL", sb.toString());
                if (this.hoverTouchDistance > ((float) i2)) {
                    if (Globals.AppUsesMouse) {
                        Toast.makeText(MainActivity.instance, "Detected buggy touch panel, enabling workarounds", 0).show();
                    }
                    touchInput = CrappyMtkTabletWithBrokenTouchDrivers.Holder.sInstance;
                } else {
                    if (this.fingerHover) {
                        if (Globals.AppUsesMouse) {
                            Toast.makeText(MainActivity.instance, "Finger hover capability detected", 0).show();
                        }
                        if (Globals.FingerHover && (Globals.RelativeMouseMovement || Globals.LeftClickMethod != 0)) {
                            if (Globals.RelativeMouseMovement) {
                                Globals.ShowScreenUnderFinger = 1;
                            }
                            Globals.RelativeMouseMovement = false;
                            Globals.LeftClickMethod = 0;
                        }
                        Settings.applyMouseEmulationOptions();
                    }
                    if (Globals.GenerateSubframeTouchEvents) {
                        touchInput = IcsTouchInputWithHistory.Holder.sInstance;
                    } else {
                        touchInput = IcsTouchInput.Holder.sInstance;
                    }
                }
            }
            super.process(motionEvent);
        }

        public void processGenericEvent(MotionEvent motionEvent) {
            super.processGenericEvent(motionEvent);
            if ((motionEvent.getAction() & 255) == 7 || (motionEvent.getAction() & 255) == 9 || (motionEvent.getAction() & 255) == 10) {
                this.hover = true;
                this.hoverX = motionEvent.getX();
                this.hoverY = motionEvent.getY();
                this.hoverTime = System.currentTimeMillis();
                if (ExternalMouseDetected == 0 && (motionEvent.getAction() & 255) == 7) {
                    this.fingerHover = true;
                }
                if (this.tap && System.currentTimeMillis() < this.tapTime + 1000) {
                    this.tap = false;
                    this.hoverTouchDistance += Math.abs(this.tapX - this.hoverX) + Math.abs(this.tapY - this.hoverY);
                    Log.i("SDL", "Hover hoverX " + this.hoverX + " tapX " + this.tapX + " hoverY " + this.hoverX + " tapY " + this.tapY + " hoverTouchDistance " + this.hoverTouchDistance);
                }
            }
        }
    }

    public static int processGamepadDeviceId(InputDevice inputDevice) {
        if (inputDevice == null) {
            return 0;
        }
        int sources = inputDevice.getSources();
        if ((sources & 16) != 16 && (sources & InputDeviceCompat.SOURCE_GAMEPAD) != 1025) {
            return 0;
        }
        int id = inputDevice.getId();
        int i = 0;
        while (true) {
            int[] iArr = gamepadIds;
            if (i >= iArr.length) {
                int i2 = 0;
                while (true) {
                    int[] iArr2 = gamepadIds;
                    if (i2 >= iArr2.length) {
                        return 0;
                    }
                    if (iArr2[i2] == 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("libSDL: gamepad added: deviceId ");
                        sb.append(id);
                        sb.append(" gamepadId ");
                        int i3 = i2 + 1;
                        sb.append(i3);
                        Log.i("SDL", sb.toString());
                        gamepadIds[i2] = id;
                        return i3;
                    }
                    i2++;
                }
            } else if (iArr[i] == id) {
                return i + 1;
            } else {
                i++;
            }
        }
    }

    public static void registerInputManagerCallbacks(Context context) {
        if (Build.VERSION.SDK_INT >= 16) {
            JellyBeanInputManager.Holder.sInstance.register(context);
        }
    }

    /* compiled from: Video */
    private static class JellyBeanInputManager {
        private JellyBeanInputManager() {
        }

        /* compiled from: Video */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final JellyBeanInputManager sInstance = new JellyBeanInputManager();

            private Holder() {
            }
        }

        /* compiled from: Video */
        private static class Listener implements InputManager.InputDeviceListener {
            public void onInputDeviceAdded(int i) {
            }

            private Listener() {
            }

            public void onInputDeviceChanged(int i) {
                onInputDeviceRemoved(i);
            }

            public void onInputDeviceRemoved(int i) {
                for (int i2 = 0; i2 < DifferentTouchInput.gamepadIds.length; i2++) {
                    if (DifferentTouchInput.gamepadIds[i2] == i) {
                        Log.i("SDL", "libSDL: gamepad removed: deviceId " + i + " gamepadId " + (i2 + 1));
                        DifferentTouchInput.gamepadIds[i2] = 0;
                    }
                }
            }
        }

        public void register(Context context) {
            ((InputManager) context.getSystemService("input")).registerInputDeviceListener(new Listener(), (Handler) null);
        }
    }
}
