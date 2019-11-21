package com.example.tower;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RequestForNotification extends Service {
    public RequestForNotification() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.channel_id))
                .setContentTitle(intent.getStringExtra("TITLE"))
                .setSmallIcon(R.drawable.water)
                .setContentText(intent.getStringExtra("CONTENT_TEXT"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setAutoCancel(true);  //Required, for support for 7.1 and lower

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(80, builder.build());

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //Required, for support for 8.0 and higher
    private void createNotificationChannel() throws NullPointerException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channel_id = getString(R.string.channel_id);
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_des);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channel_id, name, importance);
            channel.setDescription(description);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

}
