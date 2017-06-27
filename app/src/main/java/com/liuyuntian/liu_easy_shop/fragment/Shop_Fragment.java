package com.liuyuntian.liu_easy_shop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuyuntian.liu_easy_shop.LoginActivity;
import com.liuyuntian.liu_easy_shop.R;
import com.liuyuntian.liu_easy_shop.RegisterActivity;
import com.liuyuntian.liu_easy_shop.android.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuyu on 2017/6/27.
 */

public class Shop_Fragment extends Fragment {
    View view;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_person_info)
    TextView tvPersonInfo;
    @BindView(R.id.tv_person_goods)
    TextView tvPersonGoods;
    @BindView(R.id.tv_goods_upload)
    TextView tvGoodsUpload;
    Unbinder unbinder;
    ActivityUtils activityUtils = new ActivityUtils(this);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getActivity()).inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @OnClick(R.id.tv_login)
    public void onClick(View view){
        activityUtils.startActivity(LoginActivity.class);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
