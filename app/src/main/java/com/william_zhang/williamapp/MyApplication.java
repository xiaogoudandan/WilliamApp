package com.william_zhang.williamapp;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by william_zhang on 2018/2/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
