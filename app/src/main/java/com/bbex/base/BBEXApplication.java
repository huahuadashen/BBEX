package com.bbex.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jfz.context.BaseApp;

/**
 * author : zhangxuebing
 * date   : 2017/9/17
 */

public class BBEXApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        BaseApp.instance = this;
        ARouter.init(this);
    }
}
