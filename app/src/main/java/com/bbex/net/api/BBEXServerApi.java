package com.bbex.net.api;

import com.bbex.net.model.UserModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * author : zhangxuebing
 * date   : 9/13/17
 */

public interface BBEXServerApi {

    /**
     * 获取用户信息
     *
     * @return
     */
    @GET("api/user/100000")
    Observable<UserModel> getUserInfo();

}
