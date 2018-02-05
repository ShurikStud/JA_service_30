package com.example.shurik.ja_service_30.service.thread;

import android.app.Service;
import android.util.Log;

import com.example.shurik.ja_service_30.model.Entity;

import java.util.concurrent.TimeUnit;

/**
 * Created by shurik on 05.02.2018.
 */

public class MyRunThread implements Runnable {

    private static final String TAG = MyRunThread.class.getSimpleName();

    private int time;
    private int startId;
    private Service service;
    private Entity entity;

    public MyRunThread(int time, int startId, Service service, Entity entity) {
        this.time = time;
        this.startId = startId;
        this.service = service;
        this.entity = entity;

        Log.v(TAG, "MyRunThread ---> " + startId + " create");

    }

    @Override
    public void run() {

        Log.v(TAG, "MyRunThread ---> " + startId + " start time = " + time);

        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }

        try {
            Log.v(TAG, "MyRunThread ---> " + startId + "entity = " + entity.getClass());
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            Log.v(TAG, "MyRunThread ---> " + startId + " error, null pointer");
        }

        stop();

    }

    void stop(){
        Log.v(TAG, "MyRunThread ---> " + startId + " end, stopSelf(" + startId + ")");
        service.stopSelf();
    }

}
