package com.szc.weather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.szc.weather.constant.MyApplication;
import com.szc.weather.R;

/**
 * Created by szc on 17-2-25.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MyApplication.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }



}
