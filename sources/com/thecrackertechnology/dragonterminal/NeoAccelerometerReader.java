package com.thecrackertechnology.dragonterminal;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class NeoAccelerometerReader extends AccelerometerReader {
    public /* bridge */ /* synthetic */ void onAccuracyChanged(Sensor sensor, int i) {
        super.onAccuracyChanged(sensor, i);
    }

    public /* bridge */ /* synthetic */ void onSensorChanged(SensorEvent sensorEvent) {
        super.onSensorChanged(sensorEvent);
    }

    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    public NeoAccelerometerReader(Context context) {
        super(context);
    }

    public static void setGyroInvertedOrientation(boolean z) {
        gyro.invertedOrientation = z;
    }
}
