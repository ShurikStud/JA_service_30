package com.example.shurik.ja_service_30;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shurik.ja_service_30.model.Entity;
import com.example.shurik.ja_service_30.receiver.MyBroadcastReceiver;
import com.example.shurik.ja_service_30.service.CustomService;
import com.example.shurik.ja_service_30.service.MyExecutorService;
import com.example.shurik.ja_service_30.service.MyIntentService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button buttonStart, buttonStop, buttonExecutorService, buttonIntentService;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.activity_main_button_start);
        buttonStop = (Button) findViewById(R.id.activity_main_button_stop);
        buttonExecutorService = (Button) findViewById(R.id.activity_main_button_executor_service);
        buttonIntentService = (Button) findViewById(R.id.activity_main_button_intent_service);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonExecutorService.setOnClickListener(this);
        buttonIntentService.setOnClickListener(this);

        //getServices();

        ServiceApplication.setHandler(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (MyExecutorService.DESTROY_SERVICE_KEY == msg.what) {
                    //Toast.makeText(MainActivity.this, "service destroyed", Toast.LENGTH_LONG);


                    Entity entity = (Entity) msg.obj;
                    Toast.makeText(MainActivity.this, entity.toString(), Toast.LENGTH_LONG);
                }
            }
        });

        myBroadcastReceiver = new MyBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_MY_INTENT_SERVICE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(myBroadcastReceiver, intentFilter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.activity_main_button_start:

                startService(new Intent(MainActivity.this, CustomService.class));

                break;

            case R.id.activity_main_button_stop:

                stopService(new Intent(MainActivity.this, CustomService.class));

                break;

            case R.id.activity_main_button_executor_service:

                startService(new Intent(MainActivity.this, MyExecutorService.class).putExtra(MyExecutorService.TIME_KEY, 7));
                startService(new Intent(MainActivity.this, MyExecutorService.class).putExtra(MyExecutorService.TIME_KEY, 2));
                startService(new Intent(MainActivity.this, MyExecutorService.class).putExtra(MyExecutorService.TIME_KEY, 4));

                break;

            case R.id.activity_main_button_intent_service:
                Intent intentMyIntentService
                    = new Intent(MainActivity.this, MyIntentService.class);
                startService(intentMyIntentService
                        .putExtra(MyIntentService.TIME_TASK_KEY, 3)
                        .putExtra(MyIntentService.TASK_KEY, "first task")
                        .putExtra(MyIntentService.NOTIFICATION, 1));
                startService(intentMyIntentService
                        .putExtra(MyIntentService.TIME_TASK_KEY, 1)
                        .putExtra(MyIntentService.TASK_KEY, "second task")
                        .putExtra(MyIntentService.NOTIFICATION, 2));
                startService(intentMyIntentService
                        .putExtra(MyIntentService.TIME_TASK_KEY, 4)
                        .putExtra(MyIntentService.TASK_KEY, "third task")
                        .putExtra(MyIntentService.NOTIFICATION, 3));

        }

    }

    private void getServices(){

        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> rs = activityManager.getRunningServices(50);
        Log.v(TAG, "RunningService size = " + rs.size());

        for (int i = 0; i < rs.size(); i ++){

            ActivityManager.RunningServiceInfo runningServiceInfo = rs.get(i);

            Log.v(TAG, "Service process " + runningServiceInfo.process + " with component " + runningServiceInfo.service.getClassName());

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }
}
