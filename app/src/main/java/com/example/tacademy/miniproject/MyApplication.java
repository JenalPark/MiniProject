package com.example.tacademy.miniproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by Tacademy on 2016-08-10.
 */
//네트워크 하기에 앞서 MyApplication 객체 만들어 준다.
public class MyApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
