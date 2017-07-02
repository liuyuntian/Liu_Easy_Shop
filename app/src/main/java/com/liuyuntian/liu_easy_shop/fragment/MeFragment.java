package com.liuyuntian.liu_easy_shop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuyuntian.liu_easy_shop.LoginActivity;
import com.liuyuntian.liu_easy_shop.PersonActivity;
import com.liuyuntian.liu_easy_shop.R;
import com.liuyuntian.liu_easy_shop.android.utils.ActivityUtils;
import com.liuyuntian.liu_easy_shop.cache.peference.CachePerference;

import butterknife.ButterKnife;
import butterknife.OnClick;



public class MeFragment extends Fragment{

    private ActivityUtils activityUtils;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_me,container,false);
            activityUtils = new ActivityUtils(this);
            ButterKnife.bind(this,view);
        }
        return view;
    }

    @OnClick({R.id.tv_person_info, R.id.tv_login, R.id.tv_person_goods, R.id.tv_goods_upload})
    public void onClick(View v){
        //需要判断用户是否登录，从而觉得跳转的位置
        if(CachePerference.getInstance().getUser().getName() == null){
            activityUtils.startActivity(LoginActivity.class);
            return;
        }

        switch (v.getId()){
            //个人信息
            case R.id.tv_person_info:
            //登录
            case R.id.tv_login:
                //跳转到个人信息页面
                activityUtils.startActivity(PersonActivity.class);
                break;
            //我的商品
            case R.id.tv_person_goods:
                //todo 跳转到我的商品页面
                activityUtils.showToast("跳转到我的商品页面,待实现");
                break;
            //商品上传
            case R.id.tv_goods_upload:
                //todo 跳转到商品上传页面
                activityUtils.showToast("跳转到商品上传页面,待实现");
                break;
        }
    }

}
