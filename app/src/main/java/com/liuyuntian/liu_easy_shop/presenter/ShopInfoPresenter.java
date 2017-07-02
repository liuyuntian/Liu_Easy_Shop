package com.liuyuntian.liu_easy_shop.presenter;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.liuyuntian.liu_easy_shop.mode.GoodsInfo;
import com.liuyuntian.liu_easy_shop.mode.GoodsResult;
import com.liuyuntian.liu_easy_shop.network.EasyShopClient;
import com.liuyuntian.liu_easy_shop.network.UICallBack;
import com.liuyuntian.liu_easy_shop.view_interface.ShopInfo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by liuyuntian on 2017/6/30.
 */

public class ShopInfoPresenter extends MvpNullObjectBasePresenter<ShopInfo> {
    private static EasyShopClient easyShopClient;
    private OkHttpClient okHttpClient;
    private Gson gson;
    private Call call;
    private int pageNo = 1;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(call!=null) call.cancel();
    }
    //refresh data
    public void refresh(String type){
        //display UI
        getView().showreFresh();
        call = EasyShopClient.getInstance().getGoods(1,type);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().showRefreshError(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String json) {
                gson = new Gson();
                GoodsResult goodsResult = gson.fromJson(json,GoodsResult.class);
                switch (goodsResult.getCode()){
                    //success
                    case 1:
                        //no products
                        if(goodsResult.getDatas().size()==0){
                            getView().showRefreshEnd();
                        }
                        else{
                            getView().addRefreshData(goodsResult.getDatas());
                            getView().showRefreshEnd();
                        }
                        // pagen0=2,update more data
                        pageNo = 2;
                        break;
                    default:
                        getView().showRefreshError(goodsResult.getMessage());
                }
            }
        });
    }
    //load more
    public void loadMoreData(String type){
        getView().showLoadMore();
        call = EasyShopClient.getInstance().getGoods(pageNo,type);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().showLoadMoreError(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String json) {
                GoodsResult goodsResult = new Gson().fromJson(json,GoodsResult.class);
                switch (goodsResult.getCode()){
                    case 1:
                        if(goodsResult.getDatas().size()==0){
                            getView().showLoadMoreEnd();
                        }
                        else{
                            getView().addMoreData(goodsResult.getDatas());
                            getView().showLoadMoreEnd();
                        }
                        //load new page
                        pageNo++;
                        break;
                    default:
                        getView().showLoadMoreError(goodsResult.getMessage());
                }
            }
        });
    }
}

