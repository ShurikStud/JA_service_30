package com.example.shurik.ja_service_30.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.shurik.ja_service_30.ServiceApplication;
import com.example.shurik.ja_service_30.model.Entity;
import com.example.shurik.ja_service_30.service.thread.MyRunThread;

import java.util.Enumeration;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shurik on 05.02.2018.
 */

public class MyExecutorService extends Service {

    private static final String TAG = MyExecutorService.class.getSimpleName();

    public static final String TIME_KEY = "time";
    public static final int DESTROY_SERVICE_KEY = 99;

    private ExecutorService executorService;
    private Entity entity;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(TAG, "onCreate");

        executorService = Executors.newFixedThreadPool(2);
        entity  = new Entity(DESTROY_SERVICE_KEY, TAG, "===========");

    }

    @Override
    public void onDestroy() {

        Log.v(TAG, "onDestroy()");

        super.onDestroy();

        //ServiceApplication.getHandler().sendEmptyMessage(DESTROY_SERVICE_KEY);

        Message message = ServiceApplication.getHandler().obtainMessage(DESTROY_SERVICE_KEY, entity);
        ServiceApplication.getHandler().sendMessage(message);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.v(TAG, "onStartCommand");

        int time = intent.getIntExtra(TIME_KEY, 1);

        MyRunThread myRunThread = new MyRunThread(time, startId, this, entity);
        executorService.execute(myRunThread);

        return super.onStartCommand(intent, flags, startId);
    }
}
