package com.example.shurik.ja_service_30.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by shurik on 05.02.2018.
 */

public class CustomService extends Service {

    private static final String TAG = CustomService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(TAG, "OnCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "OnStartCommand");
        //someTask();
        someTaskThread();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.v(TAG, "onDestroy()");
    }

    void someTask(){

        for (int i = 1; i <= 5; i++){

            Log.v(TAG, "i = " + i);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
    void someTaskThread(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++){

                    Log.v(TAG, "i = " + i);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();




    }
}
