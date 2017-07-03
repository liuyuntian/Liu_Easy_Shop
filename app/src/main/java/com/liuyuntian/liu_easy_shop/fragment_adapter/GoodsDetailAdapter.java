package com.liuyuntian.liu_easy_shop.fragment_adapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class GoodsDetailAdapter extends PagerAdapter{

    private ArrayList<ImageView> list;

    public GoodsDetailAdapter(ArrayList<ImageView> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //实例化Item
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = list.get(position);
        //实现图片点击跳转到图片展示页
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到图片详情页
                if (listener != null){
                    listener.onItemClick();
                }
            }
        });
        container.addView(imageView);
        return imageView;
    }

    //销毁Item
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


//    ############################  Viewpager的Item点击事件（接口回调）  #################
    public interface OnItemClickListener{

        void onItemClick();
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
