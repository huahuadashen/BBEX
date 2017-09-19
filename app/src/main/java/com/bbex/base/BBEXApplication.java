package com.bbex.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jfz.context.BaseApp;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * author : zhangxuebing
 * date   : 2017/9/17
 */

public class BBEXApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        BaseApp.instance = this;
        CrashReport.initCrashReport(getApplicationContext(), "94ba3d1ffd", false);
        ARouter.init(this);
    }
}
