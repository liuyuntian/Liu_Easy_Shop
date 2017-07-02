package com.liuyuntian.liu_easy_shop.view_interface;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.liuyuntian.liu_easy_shop.mode.GoodsInfo;

import java.util.List;

/**
 * Created by liuyu on 2017/6/30.
 */

public interface ShopInfo extends MvpView {
    //数据刷新
    public void showreFresh();
    public void showRefreshError(String msg);
    public void showRefreshEnd();
    public void hideRefresh();
    public void addRefreshData(List<GoodsInfo> data);
    public void showLoadMore();
    public void showLoadMoreError(String msg);
    public void showLoadMoreEnd();
    public void hidLoadMore();
    public void addMoreData(List<GoodsInfo> data);
    public void showMessage(String msg);
}
