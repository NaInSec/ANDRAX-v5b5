package com.thecrackertechnology.dragonterminal;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/* compiled from: Accelerometer */
class AccelerometerReader implements SensorEventListener {
    public static final GyroscopeListener gyro = new GyroscopeListener();
    public static final OrientationListener orientation = new OrientationListener();
    private SensorManager _manager = null;
    public boolean openedBySDL = false;

    private static native void nativeAccelerometer(float f, float f2, float f3);

    /* access modifiers changed from: private */
    public static native void nativeGyroscope(float f, float f2, float f3);

    /* access modifiers changed from: private */
    public static native void nativeOrientation(float f, float f2, float f3);

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public AccelerometerReader(Context context) {
        this._manager = (SensorManager) context.getSystemService("sensor");
    }

    public synchronized void stop() {
        if (this._manager != null) {
            Log.i("SDL", "libSDL: stopping accelerometer/gyroscope/orientation");
            this._manager.unregisterListener(this);
            this._manager.unregisterListener(gyro);
            this._manager.unregisterListener(orientation);
        }
    }

    public synchronized void start() {
        if (!((!Globals.UseAccelerometerAsArrowKeys && !Globals.AppUsesAccelerometer) || this._manager == null || this._manager.getDefaultSensor(1) == null)) {
            Log.i("SDL", "libSDL: starting accelerometer");
            this._manager.registerListener(this, this._manager.getDefaultSensor(1), 1);
        }
        if (!((!Globals.AppUsesGyroscope && !Globals.MoveMouseWithGyroscope) || this._manager == null || this._manager.getDefaultSensor(4) == null)) {
            Log.i("SDL", "libSDL: starting gyroscope");
            this._manager.registerListener(gyro, this._manager.getDefaultSensor(4), 1);
        }
        if (!(!Globals.AppUsesOrientationSensor || this._manager == null || this._manager.getDefaultSensor(15) == null)) {
            Log.i("SDL", "libSDL: starting orientation sensor");
            this._manager.registerListener(orientation, this._manager.getDefaultSensor(15), 1);
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (!Globals.HorizontalOrientation) {
            nativeAccelerometer(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
        } else if (gyro.invertedOrientation) {
            nativeAccelerometer(-sensorEvent.values[1], sensorEvent.values[0], sensorEvent.values[2]);
        } else {
            nativeAccelerometer(sensorEvent.values[1], -sensorEvent.values[0], sensorEvent.values[2]);
        }
    }

    /* compiled from: Accelerometer */
    static class GyroscopeListener implements SensorEventListener {
        final float[] filterMax = {0.05f, 0.05f, 0.05f};
        final float[] filterMin = {-0.05f, -0.05f, -0.05f};
        public boolean invertedOrientation = false;
        float[] measuredNoiseRange = null;
        int measurementIteration = 0;
        int movementBackoff = 0;
        float[][] noiseData = ((float[][]) Array.newInstance(float.class, new int[]{200, this.noiseMin.length}));
        int noiseDataIdx = 0;
        float[] noiseMax = {1.0f, 1.0f, 1.0f};
        float[] noiseMin = {-1.0f, -1.0f, -1.0f};

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        /* access modifiers changed from: package-private */
        public void collectNoiseData(float[] fArr) {
            int i = 0;
            while (true) {
                float[] fArr2 = this.noiseMin;
                if (i >= fArr2.length) {
                    this.movementBackoff--;
                    if (this.movementBackoff < 0) {
                        this.noiseDataIdx++;
                        if (this.noiseDataIdx >= this.noiseData.length) {
                            this.measurementIteration++;
                            Log.d("SDL", "GYRO_NOISE: Measuring in progress... " + this.measurementIteration);
                            if (this.measurementIteration > 5) {
                                float[] fArr3 = this.noiseMin;
                                float[] fArr4 = this.filterMin;
                                System.arraycopy(fArr3, 0, fArr4, 0, fArr4.length);
                                float[] fArr5 = this.noiseMax;
                                float[] fArr6 = this.filterMax;
                                System.arraycopy(fArr5, 0, fArr6, 0, fArr6.length);
                            }
                            if (this.measurementIteration > 15) {
                                Log.d("SDL", "GYRO_NOISE: Measuring done! Maximum number of iterations reached: " + this.measurementIteration);
                                this.noiseData = null;
                                this.measuredNoiseRange = null;
                                return;
                            }
                            this.noiseDataIdx = 0;
                            boolean z = false;
                            for (int i2 = 0; i2 < this.noiseMin.length; i2++) {
                                int i3 = 0;
                                float f = 1.0f;
                                float f2 = -1.0f;
                                while (true) {
                                    float[][] fArr7 = this.noiseData;
                                    if (i3 >= fArr7.length) {
                                        break;
                                    }
                                    if (f > fArr7[i3][i2]) {
                                        f = fArr7[i3][i2];
                                    }
                                    float[][] fArr8 = this.noiseData;
                                    if (f2 < fArr8[i3][i2]) {
                                        f2 = fArr8[i3][i2];
                                    }
                                    i3++;
                                }
                                float f3 = (f + f2) / 2.0f;
                                float f4 = f + ((f - f3) * 0.2f);
                                float f5 = f2 + ((f2 - f3) * 0.2f);
                                float[] fArr9 = this.noiseMax;
                                float f6 = fArr9[i2];
                                float[] fArr10 = this.noiseMin;
                                if (f5 - f4 < f6 - fArr10[i2] && f4 >= fArr10[i2] && f5 <= fArr9[i2]) {
                                    fArr10[i2] = (fArr10[i2] + (f4 * 4.0f)) / 5.0f;
                                    fArr9[i2] = (fArr9[i2] + (f5 * 4.0f)) / 5.0f;
                                    z = true;
                                }
                            }
                            Log.d("SDL", "GYRO_NOISE: MIN MAX: " + Arrays.toString(this.noiseMin) + StringUtils.SPACE + Arrays.toString(this.noiseMax));
                            if (z) {
                                float[] fArr11 = new float[this.noiseMin.length];
                                int i4 = 0;
                                while (true) {
                                    float[] fArr12 = this.noiseMin;
                                    if (i4 >= fArr12.length) {
                                        break;
                                    }
                                    fArr11[i4] = this.noiseMax[i4] - fArr12[i4];
                                    i4++;
                                }
                                Log.d("SDL", "GYRO_NOISE: RANGE:   " + Arrays.toString(fArr11) + StringUtils.SPACE + Arrays.toString(this.measuredNoiseRange));
                                if (this.measuredNoiseRange == null) {
                                    this.measuredNoiseRange = fArr11;
                                    return;
                                }
                                for (int i5 = 0; i5 < fArr11.length; i5++) {
                                    if (this.measuredNoiseRange[i5] / fArr11[i5] > 1.2f) {
                                        this.measuredNoiseRange = fArr11;
                                        return;
                                    }
                                }
                                float[] fArr13 = this.noiseMin;
                                float[] fArr14 = this.filterMin;
                                System.arraycopy(fArr13, 0, fArr14, 0, fArr14.length);
                                float[] fArr15 = this.noiseMax;
                                float[] fArr16 = this.filterMax;
                                System.arraycopy(fArr15, 0, fArr16, 0, fArr16.length);
                                this.noiseData = null;
                                this.measuredNoiseRange = null;
                                Log.d("SDL", "GYRO_NOISE: Measuring done! Range converged on iteration " + this.measurementIteration);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                } else if (fArr[i] < fArr2[i] || fArr[i] > this.noiseMax[i]) {
                    int i6 = this.movementBackoff;
                } else {
                    this.noiseData[this.noiseDataIdx][i] = fArr[i];
                    i++;
                }
            }
            int i62 = this.movementBackoff;
            if (i62 < 0) {
                this.noiseDataIdx -= (-i62) < 10 ? -i62 : 10;
                if (this.noiseDataIdx < 0) {
                    this.noiseDataIdx = 0;
                }
            }
            this.movementBackoff = 10;
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] fArr = sensorEvent.values;
            if (this.noiseData != null) {
                collectNoiseData(fArr);
            }
            boolean z = true;
            for (int i = 0; i < 3; i++) {
                float f = fArr[i];
                float[] fArr2 = this.filterMin;
                if (f < fArr2[i]) {
                    fArr[i] = fArr[i] - fArr2[i];
                } else {
                    float f2 = fArr[i];
                    float[] fArr3 = this.filterMax;
                    if (f2 > fArr3[i]) {
                        fArr[i] = fArr[i] - fArr3[i];
                    }
                }
                z = false;
            }
            if (!z) {
                if (Globals.HorizontalOrientation) {
                    if (this.invertedOrientation) {
                        AccelerometerReader.nativeGyroscope(-fArr[0], -fArr[1], fArr[2]);
                    } else {
                        AccelerometerReader.nativeGyroscope(fArr[0], fArr[1], fArr[2]);
                    }
                } else if (this.invertedOrientation) {
                    AccelerometerReader.nativeGyroscope(-fArr[1], fArr[0], fArr[2]);
                } else {
                    AccelerometerReader.nativeGyroscope(fArr[1], -fArr[0], fArr[2]);
                }
            }
        }

        public boolean available(Activity activity) {
            SensorManager sensorManager = (SensorManager) activity.getSystemService("sensor");
            return (sensorManager == null || sensorManager.getDefaultSensor(4) == null) ? false : true;
        }

        public void registerListener(Activity activity, SensorEventListener sensorEventListener) {
            SensorManager sensorManager = (SensorManager) activity.getSystemService("sensor");
            int i = 4;
            if (sensorManager != null || sensorManager.getDefaultSensor(4) != null) {
                GyroscopeListener gyroscopeListener = AccelerometerReader.gyro;
                if (Globals.AppUsesOrientationSensor) {
                    i = 15;
                }
                sensorManager.registerListener(gyroscopeListener, sensorManager.getDefaultSensor(i), 1);
            }
        }

        public void unregisterListener(Activity activity, SensorEventListener sensorEventListener) {
            SensorManager sensorManager = (SensorManager) activity.getSystemService("sensor");
            if (sensorManager != null) {
                sensorManager.unregisterListener(sensorEventListener);
            }
        }
    }

    /* compiled from: Accelerometer */
    static class OrientationListener implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            AccelerometerReader.nativeOrientation(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
        }
    }
}
