package com.bbex.webview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bbex.R;
import com.bbex.config.RouterConstant;

/**
 * author : zhangxuebing
 * date   : 2017/9/18
 */

@Route(path = RouterConstant.CommonModule.WEBVIEW)
public class WebActivity extends Activity {
    public static final String URL = "_url";
    WebView mWebView;
    @Autowired(name = URL)
    public String WebUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_webview);

        Toolbar toolBar = (Toolbar) findViewById(R.id.app_framework_toolbar_bar);
        toolBar.findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(WebUrl);

    }
}
