package com.liuyuntian.liu_easy_shop.view_interface;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.liuyuntian.liu_easy_shop.mode.GoodsDetail;
import com.liuyuntian.liu_easy_shop.mode.UserResult;

import java.util.ArrayList;

/**
 * Created by liuyu on 2017/7/3.
 */

public interface GoodsDetailView extends MvpView {

    public void showProgress();
    public void hideProgress();
    //设置图片路径
    public void setImageSource(ArrayList<String> arrayList);
    //设置商品信息
    public void setData(GoodsDetail data, UserResult.DataBean goods_user);
    //商品没有
    public void showError();

    public void showMessage(String msg);
    //删除商品
    public void deleteEnd();
}
