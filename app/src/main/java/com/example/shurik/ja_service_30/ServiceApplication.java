package com.example.shurik.ja_service_30;

import android.app.Application;
import android.os.Handler;

/**
 * Created by shurik on 05.02.2018.
 */

public class ServiceApplication extends Application {

    private static Handler handler;

    public static Handler getHandler(){
        return handler;
    }

    public static void setHandler(Handler h){
        handler = h;
    }

    public static void removeHandler(){
        handler = null;
    }


}
