package com.example.shurik.ja_service_30.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.shurik.ja_service_30.service.CustomService;

/**
 * Created by shurik on 12.02.2018.
 */

public class BootBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, CustomService.class));
    }
}
