package com.liuyuntian.liu_easy_shop.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.liuyuntian.liu_easy_shop.cache.peference.CachePerference;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)//开启硬盘缓存
                .cacheInMemory(true)//开启内存缓存
                .resetViewBeforeLoading(true)//加载前重置ImageView
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(1024 * 1024 * 4)//设置内存缓存的大小，4M
                .defaultDisplayImageOptions(displayImageOptions)//设置默认加载选项
                .build();

        ImageLoader.getInstance().init(configuration);
    }
}
