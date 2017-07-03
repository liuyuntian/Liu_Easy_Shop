package com.liuyuntian.liu_easy_shop.presenter;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.liuyuntian.liu_easy_shop.mode.GoodsDetail;
import com.liuyuntian.liu_easy_shop.mode.GoodsDetailResult;
import com.liuyuntian.liu_easy_shop.network.EasyShopClient;
import com.liuyuntian.liu_easy_shop.network.UICallBack;
import com.liuyuntian.liu_easy_shop.view_interface.GoodsDetailView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by liuyu on 2017/7/3.
 */

public class GoodsDetailsPresenter extends MvpNullObjectBasePresenter<GoodsDetailView>{
    private Call getGoodsCall;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(getGoodsCall!=null)getGoodsCall.cancel();
    }
    //获取商品详细的数据
    public void getData(String uuid){
        getView().showProgress();
        getGoodsCall = EasyShopClient.getInstance().getGoodsData(uuid);
        getGoodsCall.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hideProgress();
                getView().showMessage(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String json) {
                getView().hideProgress();
                GoodsDetailResult goodsDetailResult = new Gson().fromJson(json,GoodsDetailResult.class);
               if(goodsDetailResult.getCode()==1) {
                   GoodsDetail detail = goodsDetailResult.getDatas();
                   ArrayList<String> list = new ArrayList<String>();
                   //商品图片
                   for (int i = 0; i < detail.getPages().size(); i++) {
                       String page = detail.getPages().get(i).getUri();
                       list.add(page);
                   }
                   getView().setImageSource(list);
                   //info
                   getView().setData(detail, goodsDetailResult.getUser());
               }
               else{
                   getView().showError();
               }
            }
        });
    }
}
