package com.cobosideas.lista.global.remainders_manager;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Notifications {
    Context gContext = null;
    boolean gIsGroup = false;
    List<CharSequence> gGroupName = new ArrayList<>();
    List<String> gGroupId = new ArrayList<>();
    List<String> gGroupDescription = new ArrayList<>();

    Notifications(Context context,
                  List<String> groupId, List<CharSequence> groupName, List<String> GroupDescription,
                  boolean isGroup){
        this.gContext = context;
        this.gGroupId = groupId;
        this.gGroupName = groupName;
        this.gGroupDescription = GroupDescription;
        this.gIsGroup = isGroup;
    }
    private NotificationManager createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(gGroupId.get(0),
                    gGroupName.get(0), importance);
            channel.setDescription(gGroupDescription.get(0));
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = gContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            return notificationManager;
        }else{

        }

    }
    private void notificationOlder(NotificationManager notificationManager){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (gIsGroup) {
                List<NotificationChannelGroup> notificationChannelGroups = new ArrayList();
                int totalGroundId = gGroupId.size();
                for (int cont = 0; cont < totalGroundId; cont++) {
                    notificationChannelGroups.add(new NotificationChannelGroup(gGroupId.get(cont),
                            gGroupName.get(cont)));
                }
                notificationManager.createNotificationChannelGroup(notificationChannelGroups);
            } else {
                notificationManager.createNotificationChannelGroup(
                        new NotificationChannelGroup(gGroupId.get(0), gGroupName.get(0)));
            }
        }
    }
    public void createNotification(){
        NotificationManager notificationManager;
        if (){
            notificationManager =
                    (NotificationManager) gContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }
    }
    private void deleteNotification(@NonNull NotificationManager notificationManager, String chanelId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(chanelId);
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
}
