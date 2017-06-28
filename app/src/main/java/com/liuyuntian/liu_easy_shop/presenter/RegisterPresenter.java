package com.liuyuntian.liu_easy_shop.presenter;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.liuyuntian.liu_easy_shop.mode.UserResult;
import com.liuyuntian.liu_easy_shop.network.EasyShopClient;
import com.liuyuntian.liu_easy_shop.network.UICallBack;
import com.liuyuntian.liu_easy_shop.view_interface.RegisterView;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by liuyu on 2017/6/28.
 */

public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView>{
    private RegisterView registerView;
    private Call call;

    @Override
    public void attachView(RegisterView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(call!=null) call.cancel();
    }

    public void register(String username, String password) {
        getView().showPro();
        call = EasyShopClient.getInstance().register(username, password);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hidePro();
                getView().showErrorMsg(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String json) {
                getView().hidePro();
                UserResult result = new Gson().fromJson(json,UserResult.class);
                if (result.getCode() == 1){
                    //成功提示
                    getView().showErrorMsg("注册成功");
                    // TODO: 2017/6/28 0028 用户信息保存到本地配置当中，自动登录
                    //执行注册成功的方法
                    getView().registerSuccess();
                } else if (result.getCode() == 2) {
                    //提示失败信息
                    getView().showErrorMsg(result.getMsg());
                    //执行注册失败的方法
                    getView().registerFailed();
                }else{
                    getView().showErrorMsg("未知错误！");
                }
            }
        });

    }
}
