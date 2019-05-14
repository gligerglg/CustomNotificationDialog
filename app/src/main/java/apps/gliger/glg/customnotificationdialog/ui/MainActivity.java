package apps.gliger.glg.customnotificationdialog.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import apps.gliger.glg.customnotificationdialog.R;
import apps.gliger.glg.customnotificationdialog.databinding.ActivityMainBinding;
import apps.gliger.glg.customnotificationdialog.model.NotificationContent;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    private NotificationContent notificationBean;
    private MainActivityPresenter mainActivityPresenter;
    private ActivityMainBinding activityMainBinding;
    private static final int  ACTION_NOTIFICATION_LISTENER_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationBean = new NotificationContent();
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setNotification(notificationBean);
        mainActivityPresenter = new MainActivityPresenter(getApplicationContext(),this,this);
    }

    @Override
    public void onNewNotificationFetched(NotificationContent notificationContent) {
        notificationBean.setNotification_title(notificationContent.getNotification_title());
        notificationBean.setNotification_body(notificationContent.getNotification_body());
    }

    @Override
    public void onNotificationRemoved(NotificationContent notificationContent) {
        notificationBean.setNotification_body("REMOVED");
    }

    public void onNotificationRegistration(View view) {
        startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"),ACTION_NOTIFICATION_LISTENER_CODE);
//        this.notificationBean.setNotification_body("BODY");
    }

    public void showSuccessNotification(View view) {
        String channelId = "001122";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("title")
                .setChannelId(channelId)
                .setContentTitle("Transaction Alert")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("messageBody"))
                .setAutoCancel(true);

        mBuilder.setVibrate(new long[]{0, 100, 1000, 300, 200, 100, 500, 200, 100});
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            mNotificationManager.createNotificationChannel(mChannel);
        }
        mNotificationManager.notify(0, mBuilder.build());
    }



}
