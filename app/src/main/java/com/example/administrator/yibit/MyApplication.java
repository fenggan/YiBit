package com.example.administrator.yibit;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "55ddee85e5", true);
    }
}
