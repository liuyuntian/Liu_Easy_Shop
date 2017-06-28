package com.liuyuntian.liu_easy_shop.view_interface;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by liuyu on 2017/6/28.
 */

public interface RegisterView extends MvpView{
    public void showPro();
    public void hidePro();
    public void showData();
    //注册成功
    void registerSuccess();
    //注册失败
    void registerFailed();
    //用户名密码不对时提示用户
    void showErrorMsg(String msg);


}
