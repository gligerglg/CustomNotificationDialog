package apps.gliger.glg.customnotificationdialog.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import apps.gliger.glg.customnotificationdialog.model.NotificationContent;

public class NotificationRepository {
    private static NotificationRepository repository;
    private MutableLiveData<NotificationContent> notificationLiveData = new MutableLiveData<>();
    private MutableLiveData<NotificationContent> notificationServiceStatus = new MutableLiveData<>();

    public static NotificationRepository getInstance(){
        if(repository==null)
            repository = new NotificationRepository();
        return repository;
    }

    public LiveData<NotificationContent> getNotificationLiveData() {
        return notificationLiveData;
    }

    public LiveData<NotificationContent> getNotificationServiceStatus(){return notificationServiceStatus;}

    public void setNotificationServiceStatus(NotificationContent notificationContent){
        notificationServiceStatus.setValue(notificationContent);
    }

    public void setNewNotificationContent(NotificationContent newNotificationContent){
        notificationLiveData.postValue(newNotificationContent);
    }
}

