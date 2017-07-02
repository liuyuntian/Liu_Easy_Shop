package com.liuyuntian.liu_easy_shop.fragment_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuyuntian.liu_easy_shop.R;
import com.liuyuntian.liu_easy_shop.components.AvatarLoadOptions;
import com.liuyuntian.liu_easy_shop.mode.GoodsInfo;
import com.liuyuntian.liu_easy_shop.network.EasyShopApi;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/2 0002.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private List<GoodsInfo> list = new ArrayList<>();
    private Context context;

    //add data
    public void addData(List<GoodsInfo> dataList) {
        list.addAll(dataList);
        notifyDataSetChanged();
    }

    //clear data
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    //
    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, viewGroup, false);
        ShopViewHolder viewHolder = new ShopViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShopViewHolder shopViewHolder, final int i) {
        shopViewHolder.tvItemName.setText(list.get(i).getName());
        String price = context.getString(R.string.goods_money,list.get(i).getPrice());
        shopViewHolder.tvItemPrice.setText(price);
        //image
        ImageLoader.getInstance().displayImage(EasyShopApi.IMAGE_URL+list.get(i).getPage(),shopViewHolder.ivItemRecycler,
        AvatarLoadOptions.build_item());

        // click image and jump to product activity
        shopViewHolder.ivItemRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClicked(list.get(i));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_recycler)
        ImageView ivItemRecycler;
        @BindView(R.id.tv_item_name)
        TextView tvItemName;
        @BindView(R.id.tv_item_price)
        TextView tvItemPrice;

        public ShopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface onItemClickListener{
        void onItemClicked(GoodsInfo goodsInfo);
    }
    private onItemClickListener listener;
    public void setListener(onItemClickListener listener){
        this.listener = listener;
    }

}
