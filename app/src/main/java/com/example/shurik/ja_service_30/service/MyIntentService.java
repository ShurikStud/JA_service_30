package com.example.shurik.ja_service_30.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.shurik.ja_service_30.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by shurik on 12.02.2018.
 */

public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    public static final String TIME_TASK_KEY = "time_task_key";
    public static final String TASK_KEY = "task_key";

    public static final String ACTION_MY_INTENT_SERVICE = MyIntentService.class.getName() + ".RESPONSE";

    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";
    String extraOUT = "TASK DONE!";

    public static final String NOTIFICATION = "NOTIFICATION_ID";
    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;

    public MyIntentService(){
        super(TAG);
        Log.v(TAG, "Constructor MyIntentService()");
    }

//    public MyIntentService(String name) {
//        super(name);
//        Log.v(TAG, "Constructor MyIntentService(" + name + ")");
//
//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Integer tm = intent.getIntExtra(TIME_TASK_KEY, 0);
        String label = intent.getStringExtra(TASK_KEY);
        Integer notification_id = intent.getIntExtra(NOTIFICATION, 1);
        Log.v(TAG, "onHandleIntent start:: " + label);

        try {
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        Log.v(TAG, "onHandleIntent finish:: " + label);

        responseIntent(label);
//        notificationMessage(label);
        notificationMessage(label, notification_id);
    }

    private void responseIntent(String label){
        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_MY_INTENT_SERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putExtra(EXTRA_KEY_OUT, label + " " + extraOUT);
        sendBroadcast(responseIntent);
    }

    private void notificationMessage(String label, int notification_id){

        Notification notification   = new Notification.Builder(getApplicationContext())
                .setContentTitle("Progress")
                .setContentText(extraOUT + " " + label)
                .setTicker("notification")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
//        notificationManager.notify(NOTIFICATION_ID, notification);
        notificationManager.notify(notification_id, notification);

    }
}
