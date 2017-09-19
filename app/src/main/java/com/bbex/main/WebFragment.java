package com.bbex.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bbex.R;
import com.bbex.base.BaseFragment;

import static com.bbex.main.MainActivity.sTab;

/**
 * author : zhangxuebing
 * date   : 9/9/17
 */

public class WebFragment extends BaseFragment{
    private View mRootView;
    private WebView mWebView;


    public static WebFragment newInstance() {
        Bundle args = new Bundle();
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

         mRootView = inflater.inflate(R.layout.fragment_web, container, false);

        mWebView = (WebView) mRootView.findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://bbex.io/");

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK
                        && mWebView.canGoBack()
                        && sTab == 0) {
                    mWebView.goBack();
                    return true;
                }
                return false;
            }
        });

        return mRootView;
    }

}
