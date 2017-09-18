package com.bbex.webview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bbex.R;

/**
 * author : zhangxuebing
 * date   : 2017/9/18
 */

@Route(path = "/test/activity")
public class WebActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
    }
}
