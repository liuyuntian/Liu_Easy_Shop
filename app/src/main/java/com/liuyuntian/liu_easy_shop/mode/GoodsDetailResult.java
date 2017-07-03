package com.liuyuntian.liu_easy_shop.mode;

import com.google.gson.annotations.SerializedName;

//"code": 1,
//        "msg": " success",
//        "datas": {

public class GoodsDetailResult {

    private int code;
    @SerializedName("msg")
    private String message;
    private GoodsDetail datas;

    //发布者信息
    private UserResult.DataBean user;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public GoodsDetail getDatas() {
        return datas;
    }

    public UserResult.DataBean getUser() {
        return user;
    }
}
