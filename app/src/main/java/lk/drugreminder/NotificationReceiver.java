package lk.drugreminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Build notification based on Intent
//        Notification notification = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.ic_menu_slideshow)
//                .setContentTitle(intent.getStringExtra("title"))
//                .setContentText(intent.getStringExtra("text"))
//                .build();
//        // Show notification
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(42, notification);
        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "abcd")
                        .setSmallIcon(R.drawable.ic_menu_slideshow) //set icon for notification
                        .setContentTitle(intent.getStringExtra("title")) //set title of notification
                        .setContentText(intent.getStringExtra("text"))//this is notification message
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(200, builder.build());
    }
}