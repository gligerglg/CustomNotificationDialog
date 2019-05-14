package apps.gliger.glg.customnotificationdialog.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Space;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import apps.gliger.glg.customnotificationdialog.R;
import apps.gliger.glg.customnotificationdialog.databinding.NotificationDialogBinding;
import apps.gliger.glg.customnotificationdialog.model.NotificationContent;
import apps.gliger.glg.customnotificationdialog.repository.NotificationRepository;
import apps.gliger.glg.customnotificationdialog.repository.Status;


public class MainActivityPresenter {
    private LifecycleOwner lifecycleOwner;
    private MainActivityView mainActivityView;
    private NotificationRepository notificationRepository;
    private NotificationContent notificationContent;
    private Context context;

    public MainActivityPresenter(Context context,MainActivityView mainActivityView,LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.mainActivityView = mainActivityView;
        this.lifecycleOwner = lifecycleOwner;
        notificationContent = new NotificationContent();
        notificationRepository = NotificationRepository.getInstance();
        initApp();
    }

    private void initApp(){
        notificationRepository.getNotificationServiceStatus().observe(lifecycleOwner, new Observer<NotificationContent>() {
            @Override
            public void onChanged(NotificationContent notificationContent) {
                mainActivityView.onNotificationRemoved(notificationContent);
            }
        });


        //Notification Observer
        notificationRepository.getNotificationLiveData().observe(lifecycleOwner, new Observer<NotificationContent>() {
            @Override
            public void onChanged(NotificationContent notificationContent) {
                mainActivityView.onNewNotificationFetched(notificationContent);
            }
        });
    }
}
