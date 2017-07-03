package com.liuyuntian.liu_easy_shop.cache.peference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.liuyuntian.liu_easy_shop.mode.UserResult;

/**
 * Created by liuyu on 2017/6/28.
 */

public class CachePerference {



    private static CachePerference cachePerference;
    SharedPreferences sp;
    private CachePerference(){

    }
    public static CachePerference getInstance(){
        if (cachePerference == null){
            cachePerference = new CachePerference();
        }
        return cachePerference;
    }
    SharedPreferences.Editor editor;
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_HEAD_IMAGE = "headImg";
    private static final String KEY_USER_NICKNAME = "nickname";
    private static final String KEY_USER_HX_ID ="hx_id" ;
    private final String KEY_USER_PWD = "password";
    private static final String KEY_USER_TABLE_ID = "uuid";

    public void init(Context context){
        sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    public void save(UserResult.DataBean user){
        editor.putString(KEY_USER_NAME, user.getUsername());
        editor.putString(KEY_USER_PWD, user.getPassword());
        editor.putString(KEY_USER_HX_ID, user.getName());
        editor.putString(KEY_USER_TABLE_ID, user.getUuid());
        editor.putString(KEY_USER_HEAD_IMAGE, user.getOther());
        editor.putString(KEY_USER_NICKNAME, user.getNickname());
        editor.apply();
    }
    public void clear(){
        editor.clear();
        editor.apply();
    }
    public UserResult.DataBean getUser(){
        UserResult.DataBean user = new UserResult.DataBean();
        user.setUsername(sp.getString(KEY_USER_NAME,null));
        user.setPassword(sp.getString(KEY_USER_PWD,null));
        user.setUuid(sp.getString(KEY_USER_HX_ID,null));
        user.setName(sp.getString(KEY_USER_HX_ID,null));
        user.setOther(sp.getString(KEY_USER_HEAD_IMAGE,null));
        user.setNickname(sp.getString(KEY_USER_NICKNAME,null));
        Log.d("CachePerference", user.getUsername());
        return user;
    }


}
