package com.example.a05test.app;

import android.app.Application;
import android.os.SystemClock;

public class AppConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(3000);
    }
}
