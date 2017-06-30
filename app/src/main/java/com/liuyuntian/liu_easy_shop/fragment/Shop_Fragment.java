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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getActivity()).inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
