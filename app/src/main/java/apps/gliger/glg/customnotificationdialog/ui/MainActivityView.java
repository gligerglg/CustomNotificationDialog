package apps.gliger.glg.customnotificationdialog.ui;

import apps.gliger.glg.customnotificationdialog.model.NotificationContent;

public interface MainActivityView {
    void onNewNotificationFetched(NotificationContent notificationContent);
    void onNotificationRemoved(NotificationContent notificationContent);
}
