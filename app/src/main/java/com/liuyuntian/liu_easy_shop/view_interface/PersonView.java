package com.liuyuntian.liu_easy_shop.view_interface;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by liuyu on 2017/6/30.
 */

public interface PersonView extends MvpView {

    void showPrb();

    void hidePrb();

    void showMsg(String msg);
    //用来更新头像
    void updataAvatar(String url);
}
