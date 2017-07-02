package com.liuyuntian.liu_easy_shop.presenter;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.liuyuntian.liu_easy_shop.cache.peference.CachePerference;
import com.liuyuntian.liu_easy_shop.mode.UserResult;
import com.liuyuntian.liu_easy_shop.network.EasyShopClient;
import com.liuyuntian.liu_easy_shop.network.UICallBack;
import com.liuyuntian.liu_easy_shop.view_interface.PersonView;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

/**
 * 作者：王小超
 * 邮箱：wxcican@qq.com
 */

public class PersonPresenter extends MvpNullObjectBasePresenter<PersonView>{


    private Call call;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call !=null )call.cancel();
    }

    //上传头像
    public void updataAvatar(File file){
        getView().showPrb();

        call = EasyShopClient.getInstance().uploadAvatar(file);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hidePrb();
                getView().showMsg(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String json) {
                getView().hidePrb();
                UserResult result = new Gson().fromJson(json,UserResult.class);

                if (result == null){
                    getView().showMsg("未知错误！");
                }else if (result.getCode() != 1){
                    getView().showMsg(result.getMsg());
                    return;
                }

                UserResult.DataBean user = result.getData();
                CachePerference.getInstance().save(user);

                //上传成功，触发UI操作（更新头像）
                getView().updataAvatar(result.getData().getOther());

                // TODO: 2017/6/29 0029 环信更新头像

            }
        });
    }
}
