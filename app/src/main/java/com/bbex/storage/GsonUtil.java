package com.bbex.storage;

import com.google.gson.Gson;

/**
 * author : zhangxuebing
 * date   : 2017/9/19
 */

public class GsonUtil {

    private final static String TAG = "GsonUtil";

    private Gson mGson;

    private GsonUtil(){
        mGson = new Gson();
    }

    private static class SingletonHolder {
        private static final GsonUtil INSTANCE = new GsonUtil();
    }

    public static GsonUtil g() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T json2Obj(String json, Class<T> cls){
        T t;
        try {
            t = mGson.fromJson(json, cls);
        } catch (Exception e) {
            return null;
        }

        return t;
    }

    public String obj2Json(Object obj)
    {
        String json = mGson.toJson(obj);
        return json;
    }
}
