package com.liuyuntian.liu_easy_shop;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.liuyuntian.liu_easy_shop.android.utils.ActivityUtils;
import com.liuyuntian.liu_easy_shop.fragment.MeFragment;
import com.liuyuntian.liu_easy_shop.fragment.Shop_Fragment;
import com.liuyuntian.liu_easy_shop.fragment_adapter.MyFragmentAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_toobar)
    Toolbar toolbar;
    @BindView(R.id.main_title)
    TextView tv_title;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    private ActivityUtils activityUtils;

    private boolean isExit = false;
    ArrayList<Fragment> fragmentArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        init();
    }

    private void init() {
        bottomBar.selectTabAtPosition(0,true);
        viewPager.setOffscreenPageLimit(4);
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new Shop_Fragment());
        fragmentArrayList.add(new Shop_Fragment());
        fragmentArrayList.add(new Shop_Fragment());
        fragmentArrayList.add(new MeFragment());

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragmentArrayList);
        viewPager.setAdapter(myFragmentAdapter);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_shopping:
                        viewPager.setCurrentItem(0);
                        toolbar.setBackgroundColor(getResources().getColor(R.color.first_color));
                        break;
                    case R.id.tab_message:
                        viewPager.setCurrentItem(1);
                        toolbar.setBackgroundColor(getResources().getColor(R.color.second_color));
                        break;
                    case R.id.tab_list:
                        viewPager.setCurrentItem(2);
                        toolbar.setBackgroundColor(getResources().getColor(R.color.third_color));
                        break;
                    case R.id.tab_me:
                        viewPager.setCurrentItem(3);
                        toolbar.setBackgroundColor(getResources().getColor(R.color.forth_color));
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("MainActivity", "position:" + position);
                bottomBar.selectTabAtPosition(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


//    private FragmentStatePagerAdapter unLoginAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
//        @Override
//        public Fragment getItem(int position) {
//            switch (position) {
//                //甯傚満
//                case 0:
//                    return new ShopFragment();
//                //娑堟伅
//                case 1:
//                    return new UnLoginFragment();
//                //閫氳褰?
//                case 2:
//                    return new UnLoginFragment();
//                //鎴戠殑
//                case 3:
//                    return new MeFragment();
//            }
//            return null;
//      }
//
//        @Override
//        public int getCount() {
//
//            return 4;
//        }
//    };
//
//


    @Override
    public void onBackPressed() {

        if (!isExit) {
            isExit = true;
            activityUtils.showToast("再按退出");

            viewPager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }
}

