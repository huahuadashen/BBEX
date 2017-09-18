package com.bbex.net.retrofit;

import com.bbex.net.api.BBEXServerApi;
import com.jfz.net.retrofit.SubServer;

import java.util.HashMap;

/**
 * @author: kutear.guo
 * @create: 2017/5/18 20:19
 */
@SuppressWarnings("unchecked")
public class RetrofitUtils {
    private static HashMap<Class, SubServer> SERVERS = new HashMap<>();

    static {
        //网络部分
        SERVERS.put(BBEXServerApi.class,
                new SubServer<BBEXServerApi>("https://bbex.io") {
        });
    }


    public static BBEXServerApi getCommonServer() {
        return (BBEXServerApi) SERVERS.get(BBEXServerApi.class).getApi(false);
    }


}
