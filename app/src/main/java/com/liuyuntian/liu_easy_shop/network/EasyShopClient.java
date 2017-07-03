package com.liuyuntian.liu_easy_shop.network;

import com.google.gson.Gson;
import com.liuyuntian.liu_easy_shop.cache.peference.CachePerference;
import com.liuyuntian.liu_easy_shop.mode.UserResult;

import java.io.File;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class EasyShopClient {

    private Gson gson;
    private static EasyShopClient easyShopClient;

    private OkHttpClient okHttpClient;

    private EasyShopClient(){
        //初始化日志拦截器
        okHttpClient = new OkHttpClient.Builder()
                //添加日志拦截器
                .build();
        gson = new Gson();
    }

    public static EasyShopClient getInstance(){
        if (easyShopClient == null){
            easyShopClient = new EasyShopClient();

        }
        return easyShopClient;
    }

    //登录
    public Call login(String username, String password){
        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.LOGIN)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }

    //注册
    public Call register(String username, String password){
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.REGISTER)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }
    //修改头像
    public Call uploadAvatar(File file){
//        需要传一个用户类JSON字符串格式，并且上传头像文件
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //传一个用户类JSON字符串格式
                .addFormDataPart("user",gson.toJson(CachePerference.getInstance().getUser()))
                //上传头像文件
                .addFormDataPart("image",file.getName(),
                        RequestBody.create(MediaType.parse("image/png"),file))
                .build();


        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.UPDATA)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }

    //获取所有商品
    public Call getGoods(int pageNo,String type){
        RequestBody requestBody = new FormBody.Builder()
                .add("pageNo",String.valueOf(pageNo))
                .add("type",type)
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL+EasyShopApi.GETGOODS)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }
    // nick name update
    public Call uploadUser(UserResult.DataBean user){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("user",gson.toJson(user))
                .build();
        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL+EasyShopApi.UPDATA)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);

    }
    //获取商品详情
    public Call getGoodsData(String goodsUuid){

        RequestBody requestBody = new FormBody.Builder()
                .add("uuid",goodsUuid)
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.DETAIL)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }
}
