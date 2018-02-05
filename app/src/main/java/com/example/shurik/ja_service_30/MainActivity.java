package com.example.shurik.ja_service_30;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shurik.ja_service_30.service.CustomService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button buttonStart, buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.activity_main_button_start);
        buttonStop = (Button) findViewById(R.id.activity_main_button_stop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        getServices();

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

}
