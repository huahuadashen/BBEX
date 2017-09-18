package com.bbex.net.model;

import com.google.gson.annotations.SerializedName;

/**
 * author : zhangxuebing
 * date   : 2017/9/17
 */

public class UserModel {
    /**
     * code : 0
     * msg : ok
     * data : {"id":100000,"name":"中本聪","email":"zbc@bbex.io","country_code":"86","phone":"17620358888","created_at":"2017-09-17 09:30:50","updated_at":"2017-09-17 09:30:50"}
     */

    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public DataBean data;

    public static class DataBean {
        /**
         * id : 100000
         * name : 中本聪
         * email : zbc@bbex.io
         * country_code : 86
         * phone : 17620358888
         * created_at : 2017-09-17 09:30:50
         * updated_at : 2017-09-17 09:30:50
         */

        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("email")
        public String email;
        @SerializedName("country_code")
        public String countryCode;
        @SerializedName("phone")
        public String phone;
        @SerializedName("created_at")
        public String createdAt;
        @SerializedName("updated_at")
        public String updatedAt;
    }
}
