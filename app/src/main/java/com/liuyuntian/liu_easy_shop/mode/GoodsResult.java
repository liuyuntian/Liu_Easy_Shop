package com.liuyuntian.liu_easy_shop.mode;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王小超
 * 邮箱：wxcican@qq.com
 */

//获取商品时的实体类

//{
//        "code": 1,
//        "msg": " success",
//        "datas": [
//        {

public class GoodsResult implements Serializable{

    private int code;
    @SerializedName("msg")
    private String message;
    private List<GoodsInfo> datas;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<GoodsInfo> getDatas() {
        return datas;
    }
}
