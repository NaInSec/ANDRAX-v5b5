package com.thecrackertechnology.codehackide;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import com.onesignal.OneSignalDbContract;

public class MyNot extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        Intent intent = new Intent(getApplicationContext(), MainActivityCodeHackIDE.class);
        intent.addFlags(268435456);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("channel-02", "CodeHACKIDE", 3));
        }
        Notification build = new NotificationCompat.Builder(this).setCategory(NotificationCompat.CATEGORY_PROMO).setContentTitle("CodeHACK-IDE Running").setContentText("Running in background").setSubText("Close your project to stop").setSmallIcon(R.drawable.codehack).setChannelId("channel-02").setPriority(3).setAutoCancel(false).setOngoing(true).setContentIntent(activity).build();
        notificationManager.notify(2, build);
        startForeground(2, build);
    }
}
