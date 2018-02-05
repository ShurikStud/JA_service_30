package com.example.shurik.ja_service_30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shurik.ja_service_30.service.CustomService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart, buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.activity_main_button_start);
        buttonStop = (Button) findViewById(R.id.activity_main_button_stop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

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
}
