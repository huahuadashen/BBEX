package com.bbex.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bbex.R;
import com.bbex.R2;
import com.bbex.base.BaseFragment;
import com.bbex.config.RouterConstant;
import com.bbex.net.model.UserModel;
import com.bbex.net.retrofit.RetrofitUtils;
import com.bbex.storage.SpUtil;
import com.bbex.webview.WebActivity;
import com.jfz.imageloader.ImageLoaderOptions;
import com.jfz.imageloader.JfzImageView;
import com.jfz.net.retrofit.rx.SimpleObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * author : zhangxuebing
 * date   : 9/9/17
 */

public class MainMeFragment extends BaseFragment{
    private View mRootView;

    @BindView(R2.id.user_header_image)
    JfzImageView mIv;
    @BindView(R2.id.user_name)
    TextView mUserName;
    @BindView(R2.id.user_uid)
    TextView mUserUid;
    @BindView(R2.id.user_login_state)
    TextView mUserLoginState;

    public static MainMeFragment newInstance() {
        Bundle args = new Bundle();
        MainMeFragment fragment = new MainMeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main_me, container, false);

        ButterKnife.bind(this,mRootView);

        mIv.showImage(new ImageLoaderOptions.Builder()
//                .setLoadObj("http://jfz-gxq-public2.oss-cn-hangzhou.aliyuncs.com/ad/20170717/596c30e088afa.jpg")
                .setLoadObj(R.drawable.user_portrait)
                .setImageType(ImageLoaderOptions.IMAGE_CIRCLE)
                .build());

        UserModel userModel = SpUtil.get(getActivity(), UserModel.SP_USER, UserModel.class);
        if(userModel !=null){
            mUserName.setText(userModel.data.name);
            mUserUid.setText("UID:" + userModel.data.uid);
            mUserUid.setVisibility(View.VISIBLE);
            mUserLoginState.setText(R.string.login_state);
        }else{
            mUserName.setText(R.string.login_then_have_nickname);
            mUserUid.setVisibility(View.GONE);
            mUserLoginState.setText(R.string.login);
            getUserInfo();
        }

        return mRootView;
    }

    private void getUserInfo(){
        RetrofitUtils.getCommonServer()
                .getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<UserModel>() {
                    @Override
                    protected void onSuccess(@NonNull UserModel userModel) {
                        Log.i("huahua","userModel =" + userModel.data.name);

                        mUserName.setText(userModel.data.name);
                        mUserUid.setText("UID:" + userModel.data.uid);
                        mUserUid.setVisibility(View.VISIBLE);
                        mUserLoginState.setText(R.string.login_state);

                        SpUtil.put(getActivity(),UserModel.SP_USER, userModel);
                    }

                    @Override
                    protected void onFail(@NonNull Throwable e) {
                        Log.i("huahua","e =" + e);
                    }
                });
    }

    @OnClick(R.id.exchange_order_item) void exchangeOrder(){
        ARouter.getInstance().build(RouterConstant.CommonModule.WEBVIEW)
                .withString(WebActivity.URL,"https://bbex.io/")
                .navigation();

    }
}
