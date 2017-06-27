package com.liuyuntian.liu_easy_shop.fragment_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentArrayList;
    FragmentManager fm;
    public MyFragmentAdapter(FragmentManager fm,ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fm = fm;
        this.fragmentArrayList = fragmentArrayList;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }
    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
