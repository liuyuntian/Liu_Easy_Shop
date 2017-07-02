package com.liuyuntian.liu_easy_shop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.liuyuntian.liu_easy_shop.R;
import com.liuyuntian.liu_easy_shop.android.utils.ActivityUtils;
import com.liuyuntian.liu_easy_shop.fragment_adapter.ShopAdapter;
import com.liuyuntian.liu_easy_shop.mode.GoodsInfo;
import com.liuyuntian.liu_easy_shop.presenter.ShopInfoPresenter;
import com.liuyuntian.liu_easy_shop.view_interface.ShopInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by liuyu on 2017/6/27.
 */

public class Shop_Fragment extends MvpFragment<ShopInfo, ShopInfoPresenter> implements ShopInfo {
    View view;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    PtrClassicFrameLayout refreshLayout;
    @BindView(R.id.tv_load_error)
    TextView tvLoadError;
    private ActivityUtils activityUtils;
    // product type
    private String pageType="";
    private ShopAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        adapter = new ShopAdapter();
        //Adapter
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getActivity()).inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }


    private void initView() {
        //init recyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        //item onclick listener
        adapter.setListener(new ShopAdapter.onItemClickListener() {
            @Override
            public void onItemClicked(GoodsInfo goodsInfo) {
                activityUtils.showToast("to product activity");
            }
        });
        recyclerView.setAdapter(adapter);
        //init refresh layout
        //record the time when refresh. if user refresh 2 times in a moment,cancel the 2nd refresh
        refreshLayout.setLastUpdateTimeFooterRelateObject(this);
        //set background color when refreshing
        refreshLayout.setBackgroundResource(R.color.recycler_bg);
        //close header duration
        refreshLayout.setDurationToCloseHeader(1500);
        //refresh and load callback
        refreshLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                // load more
                presenter.loadMoreData(pageType);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //refresh
                presenter.refresh(pageType);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //refresh when open this page again
        if(adapter.getItemCount()==0){
            refreshLayout.autoRefresh();
        }else{

        }
    }
    @OnClick(R.id.tv_load_error)
    public void onclick(){
        refreshLayout.autoRefresh();
    }

    @Override
    public ShopInfoPresenter createPresenter() {
        return new ShopInfoPresenter();
    }

    @Override
    public void showreFresh() {
        tvLoadError.setVisibility(View.GONE);
    }

    @Override
    public void showRefreshError(String msg) {
        //stop refresh
        refreshLayout.refreshComplete();
        //if has data
        if(adapter.getItemCount()>0){
            activityUtils.showToast(msg);
            return;
        }
        //
        tvLoadError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRefreshEnd() {
        activityUtils.showToast(R.string.refresh_more_end);
        refreshLayout.refreshComplete();
    }

    @Override
    public void hideRefresh() {
        refreshLayout.refreshComplete();
    }

    @Override
    public void addRefreshData(List<GoodsInfo> data) {
        //clear all data before set data
        if(data!=null){
            adapter.addData(data);
        }
    }

    @Override
    public void showLoadMore() {
        tvLoadError.setVisibility(View.GONE);
    }

    @Override
    public void showLoadMoreError(String msg) {
        refreshLayout.refreshComplete();
        if(adapter.getItemCount()>0){
            activityUtils.showToast(msg);
            return;
        }
        else{
            tvLoadError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoadMoreEnd() {
        refreshLayout.refreshComplete();
    }

    @Override
    public void hidLoadMore() {
        refreshLayout.refreshComplete();
    }

    @Override
    public void addMoreData(List<GoodsInfo> data) {
        adapter.addData(data);
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
