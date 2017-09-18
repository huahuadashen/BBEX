package com.bbex.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bbex.R;
import com.bbex.R2;
import com.bbex.base.BaseFragment;
import com.bbex.net.api.BBEXServerApi;
import com.bbex.net.model.UserModel;
import com.bbex.net.retrofit.RetrofitUtils;
import com.jfz.imageloader.ImageLoaderOptions;
import com.jfz.imageloader.JfzImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

        if(false){
            mUserName.setText("花花大神");
            mUserUid.setText("UID:" + 10001);
            mUserUid.setVisibility(View.VISIBLE);
            mUserLoginState.setText(R.string.login_state);
        }else{
            mUserName.setText(R.string.login_then_have_nickname);
            mUserUid.setVisibility(View.GONE);
            mUserLoginState.setText(R.string.login);
        }

        BBEXServerApi weixinApi;
        weixinApi = new Retrofit.Builder()
                .baseUrl("https://bbex.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(BBEXServerApi.class);



        RetrofitUtils.getCommonServer()
                .getUserInfo()
//                .compose(new DefaultTransformer<UserModel>())
                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
                .subscribe(new ResourceObserver<UserModel>() {
                    @Override
                    public void onNext(@NonNull UserModel userModel) {
                        Log.i("huahua","userModel =" + userModel.data.name);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("huahua","e =" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return mRootView;
    }
}
