package com.cobosideas.lista.global.remainders_manager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.cobosideas.lista.R;

public class Notifications2 {
    Context gContext;
    public Notifications2(Context context){
        gContext = context;

    }
    public void showNotification(){
        final int NOTIFICATION_START_ID      = 0;
        RemoteViews remoteViews = new RemoteViews(gContext.getPackageName(),
                R.layout.notifications);
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //builder = new NotificationCompat.Builder(contextStatic, Chanel Id)
            builder = new NotificationCompat.Builder(gContext)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContent(remoteViews)
                    .setPriority(2)
                    .setAutoCancel(true);
        }else{
            builder = new NotificationCompat.Builder(gContext)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContent(remoteViews)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setAutoCancel(true);
        }

        /*Intent startNotificationIntent = new Intent(
                gContext,
                playerService.BroadcastNotifications.class);
        startNotificationIntent.putExtra(CODE_ACTION, CODE_NOTIFICATION_START);
        startNotificationIntent.putExtra(CURRENT_NOTIFICATION, NOTIFICATION_START_ID);
        PendingIntent startIntent = PendingIntent.getBroadcast(
                contextStatic,
                NOTIFICATION_START_ID,
                startNotificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent exitNotificationIntent = new Intent(
                contextStatic,
                playerService.BroadcastNotifications.class);
        exitNotificationIntent.putExtra(CODE_ACTION, CODE_NOTIFICATION_EXIT);
        exitNotificationIntent.putExtra(CURRENT_NOTIFICATION, NOTIFICATION_EXIT_ID);
        PendingIntent exitIntent = PendingIntent.getBroadcast(
                contextStatic,
                NOTIFICATION_EXIT_ID,
                exitNotificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);*/

        //remoteViews.setTextViewText(R.id.textView_totalFilesToUpload, "change6");
        //remoteViews.setTextViewText(R.id.textView_currentFileCopying, "change7");
        /*remoteViews.setProgressBar(R.id.progressBar_totalFilesToUpload,
                totalFilesToCopy, fileCurrentlyCopying, false);
        remoteViews.setProgressBar(R.id.progressBar_fileCurrentUploading,
                currentFileSize, currentFileProgress, false);*/

        //remoteViews.setOnClickPendingIntent(R.id.button_notification_exit, exitIntent);

        NotificationManager notificationManager =
                (NotificationManager) gContext.getSystemService(Context.NOTIFICATION_SERVICE);

        builder.setContentIntent(startIntent);
        notificationManager.notify(NOTIFICATION_START_ID, builder.build());
    }
}
