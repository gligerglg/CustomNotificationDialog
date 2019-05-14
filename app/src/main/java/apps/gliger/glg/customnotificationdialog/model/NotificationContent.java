package apps.gliger.glg.customnotificationdialog.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import apps.gliger.glg.customnotificationdialog.BR;

public class NotificationContent extends BaseObservable {
    private String notification_title;
    private String notification_body;
    private String app_name;

    public NotificationContent() {
    }

    @Bindable
    public String getNotification_title() {
        return notification_title;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
        notifyPropertyChanged(BR.notification_title);
    }

    @Bindable
    public String getNotification_body() {
        return notification_body;
    }

    public void setNotification_body(String notification_body) {
        this.notification_body = notification_body;
        notifyPropertyChanged(BR.notification_body);

    }

    @Bindable
    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
        notifyPropertyChanged(BR.app_name);
    }
}
