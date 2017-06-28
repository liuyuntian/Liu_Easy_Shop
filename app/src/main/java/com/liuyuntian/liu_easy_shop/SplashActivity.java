package com.liuyuntian.liu_easy_shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.liuyuntian.liu_easy_shop.cache.peference.CachePerference;
import com.liuyuntian.liu_easy_shop.mode.UserResult;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuyu on 2017/6/28.
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.activity_splash)
    RelativeLayout activitySplash;
    boolean hasUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        UserResult.DataBean user = CachePerference.getInstance().getUser();
        if(user.getUsername()!=null&&user.getPassword()!=null){
            hasUser=true;
        }
        else{
            hasUser=false;
        }

        RotateAnimation animRotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animRotate.setDuration(1000);
        animRotate.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animRotate);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        activitySplash.setAnimation(set);
        Log.d("SplashActivity", "excuted");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (hasUser) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }).start();

    }
}
