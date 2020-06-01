package com.cobosideas.lista.global.remainders_manager;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.cobosideas.lista.R;

import java.util.List;

public class Notifications {
    Context gContext = null;

    public Notifications(Context context){
        this.gContext = context;
    }
    public NotificationChannel createNotificationChannel(String channelId,
                                                         CharSequence channelName,
                                                         int chooseImportance,
                                                         boolean isLight,
                                                         boolean isVibration,
                                                         int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = getPriority(chooseImportance);
            NotificationChannel notificationChannel =
                    new NotificationChannel(channelId, channelName, importance);
            notificationChannel.enableLights(isLight);
            notificationChannel.setLightColor(color);
            notificationChannel.enableVibration(isVibration);
            notificationChannel.setVibrationPattern(new
                    long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(gContext);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationChannel.notify();
            return notificationChannel;
        }else {

        }
        return null;
    }
    public NotificationManager createNotificationChannel(String channelId,
                                     CharSequence channelName,
                                     int chooseImportance,
                                     String description) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = getPriority(chooseImportance);
            NotificationChannel notificationChannel = new NotificationChannel(
                    channelId,
                    channelName,
                    importance);
            notificationChannel.setDescription(description);
            // Register the channel with the system; we can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =
                    gContext.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
                return notificationManager;
            }
        }else{

        }
        return null;
    }
    public NotificationManager createNotificationChannelGroup(String groupChannelId,
                                                               CharSequence groupChannelName){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannelGroup notificationChannelGroups
                    = new NotificationChannelGroup(groupChannelId, groupChannelName);

            // Register the channel with the system; we can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =
                    gContext.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannelGroup(notificationChannelGroups);
                return notificationManager;
            }
        }
        return null;
    }

    /*private NotificationManager createNotificationChannelGroups(List<String> groupChannelId,
                                                         List<CharSequence> groupChannelName){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            List<NotificationChannelGroup> notificationChannelGroups = new ArrayList();
            int totalGroundId = groupChannelId.size();
            for (int cont = 0; cont < totalGroundId; cont++) {
                notificationChannelGroups.add(new NotificationChannelGroup(groupChannelId.get(cont),
                        groupChannelName.get(cont)));
            }
            // Register the channel with the system; we can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =
                    gContext.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannelGroup(notificationChannelGroups);
                return notificationManager;
            }
        }
        return null;
    }*/

    public void createNotification(
            int  notificationId,
            String  channelId,
            int choosePriority,
            String title,
            String message){
        int priority = getPriority(choosePriority);
        NotificationCompat.Builder notification =
            new NotificationCompat.Builder(gContext, channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.app_developer)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setPriority(priority);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(gContext);
        notificationManager.notify(notificationId, notification.build());
    }

    public List<NotificationChannel> getNotificationsManager(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(gContext);
            if (notificationManager != null) {
                return notificationManager.getNotificationChannels();
            }
        }
        return null;
    }
    private void deleteNotification(@NonNull NotificationManager notificationManager, String channelId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(channelId);
        }
    }
    private void accessSettings(NotificationChannel notificationChannel){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, gContext.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, notificationChannel.getId());
            gContext.startActivity(intent);
        }
    }
    private int getPriority(int chooseImportance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            switch (chooseImportance) {
                case 0:
                    return NotificationManager.IMPORTANCE_NONE;
                case 1:
                    return NotificationManager.IMPORTANCE_MIN;
                case 2:
                    return NotificationManager.IMPORTANCE_LOW;
                case 4:
                    return NotificationManager.IMPORTANCE_HIGH;
                case 5:
                    return NotificationManager.IMPORTANCE_MAX;
                default:
                    return NotificationManager.IMPORTANCE_DEFAULT;
            }
        }else {
            return chooseImportance;
        }
    }
}
