package com.liuyuntian.liu_easy_shop.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.liuyuntian.liu_easy_shop.cache.peference.CachePerference;

/**
 * Created by liuyu on 2017/6/28.
 */

public class Myapp extends Application {
    Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Myapp", "execute");
        context = getApplicationContext();
        CachePerference.getInstance().init(this);
    }
}
