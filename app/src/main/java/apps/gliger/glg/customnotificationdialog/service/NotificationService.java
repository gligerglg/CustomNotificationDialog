package apps.gliger.glg.customnotificationdialog.service;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import apps.gliger.glg.customnotificationdialog.model.NotificationContent;
import apps.gliger.glg.customnotificationdialog.repository.NotificationRepository;
import apps.gliger.glg.customnotificationdialog.repository.Status;

@SuppressLint("OverrideAbstract")
public class NotificationService extends NotificationListenerService {
    private NotificationRepository notificationRepository;
    private NotificationContent notificationContent;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn, RankingMap rankingMap) {
        super.onNotificationPosted(sbn, rankingMap);
        notificationContent = new NotificationContent();
        notificationContent.setApp_name(sbn.getPackageName());
        notificationContent.setNotification_title(sbn.getNotification().extras.getString("android.title"));
        notificationContent.setNotification_body(sbn.getNotification().extras.getString("android.text"));
        notificationRepository.setNewNotificationContent(notificationContent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        notificationContent = new NotificationContent();
        notificationContent.setApp_name(sbn.getPackageName());
        notificationContent.setNotification_title(sbn.getNotification().extras.getString("android.title"));
        notificationContent.setNotification_body(sbn.getNotification().extras.getString("android.text"));
        notificationRepository.setNotificationServiceStatus(notificationContent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        notificationRepository = NotificationRepository.getInstance();
        return super.onBind(intent);
    }
}
