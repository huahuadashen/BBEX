package com.bbex.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bbex.R;
import com.bbex.R2;
import com.bbex.base.BaseFragment;
import com.just.library.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bbex.main.MainActivity.sTab;

/**
 * author : zhangxuebing
 * date   : 9/9/17
 */

public class WebFragment extends BaseFragment{
    protected AgentWeb mAgentWeb;
    @BindView(R2.id.container)
    LinearLayout mLinearLayout;


    public static WebFragment newInstance() {
        Bundle args = new Bundle();
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View  mRootView = inflater.inflate(R.layout.fragment_web, container, false);
        ButterKnife.bind(this,mRootView);

        mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()
                .ready()
                .go("https://bbex.io/");

        WebView webView=mAgentWeb.getWebCreator().get();

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (sTab == 0 && mAgentWeb.handleKeyEvent(i, keyEvent)) {
                    return true;
                }
                return false;
            }
        });

        return mRootView;
    }


    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }
}
