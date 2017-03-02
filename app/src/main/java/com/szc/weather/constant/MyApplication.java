package com.szc.weather.constant;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by szc on 17-2-25.
 */

public class MyApplication extends Application {
    private static Context context;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        context = this;
        handler = new Handler();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }
}
