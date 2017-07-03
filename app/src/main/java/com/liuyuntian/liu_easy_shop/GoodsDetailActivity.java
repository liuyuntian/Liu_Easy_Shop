package com.liuyuntian.liu_easy_shop;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.liuyuntian.liu_easy_shop.mode.GoodsDetail;
import com.liuyuntian.liu_easy_shop.mode.UserResult;
import com.liuyuntian.liu_easy_shop.presenter.GoodsDetailsPresenter;
import com.liuyuntian.liu_easy_shop.view_interface.GoodsDetailView;

import java.util.ArrayList;

/**
 * Created by liuyu on 2017/7/3.
 */

public class GoodsDetailActivity extends MvpActivity<GoodsDetailView,GoodsDetailsPresenter> implements GoodsDetailView{
    @NonNull
    @Override
    public GoodsDetailsPresenter createPresenter() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setImageSource(ArrayList<String> arrayList) {

    }

    @Override
    public void setData(GoodsDetail data, UserResult.DataBean goods_user) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void deleteEnd() {

    }
}
