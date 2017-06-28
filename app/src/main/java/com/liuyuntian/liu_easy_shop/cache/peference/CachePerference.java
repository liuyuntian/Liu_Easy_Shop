package com.liuyuntian.liu_easy_shop.cache.peference;

import android.content.Context;
import android.content.SharedPreferences;

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
    private final String USER_NAME = "name";
    private final String USER_PASSWORD = "password";
    private final String HX_ID = "hx_id";
    private final String TABLE_ID = "table_id";

    public void init(Context context){
        sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    public void save(UserResult.DataBean user){
        editor.putString(USER_NAME,user.getUsername());
        editor.putString(USER_PASSWORD,user.getPassword());
        editor.putString(HX_ID,user.getUuid());
        editor.putString(TABLE_ID,user.getName());
        editor.apply();
    }
    public void clear(){
        editor.clear();
        editor.apply();
    }
    public UserResult.DataBean getUser(){
        UserResult.DataBean user = new UserResult.DataBean();
        user.setUsername(sp.getString(USER_NAME,null));
        user.setPassword(sp.getString(USER_PASSWORD,null));
        user.setUuid(sp.getString(HX_ID,null));
        user.setName(sp.getString(TABLE_ID,null));
        return user;
    }


}
