package com.liuyuntian.liu_easy_shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.liuyuntian.liu_easy_shop.android.utils.ActivityUtils;
import com.liuyuntian.liu_easy_shop.cache.peference.CachePerference;
import com.liuyuntian.liu_easy_shop.presenter.LoginPresenter;
import com.liuyuntian.liu_easy_shop.view_interface.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.et_username)
    EditText et_userName;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.activity_login)
    RelativeLayout activityLogin;

    private ActivityUtils activityUtils;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        init();
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    private void init() {
        //给左上角加上一个返回图标
        setSupportActionBar(toolbar);
        //toolbar显示返回按钮,但是点击效果需要“实现菜单点击事件”
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //给EditText添加监听
        et_userName.addTextChangedListener(textWatcher);
        et_pwd.addTextChangedListener(textWatcher);

    }

    //toolbar显示返回按钮,但是点击效果需要“实现菜单点击事件”
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //如果点击的是返回，则finish
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    //EditText监听
    private TextWatcher textWatcher = new TextWatcher() {
        //这里的s表示改变之前的内容，通常start和count组合，可以在s中读取本次改变字段中被改变的内容。
        //而after表示改变后新的内容的数量
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        //这里的s表示改变之后的内容，通常start和count组合，可以在s中读取本次改变字段中新的内容。
        //而before表示被改变的内容的数量。
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        //表示最终内容
        @Override
        public void afterTextChanged(Editable s) {
            username = et_userName.getText().toString();
            password = et_pwd.getText().toString();
            //判断用户民和密码是否为空
            boolean canLogin = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password));
            btn_login.setEnabled(canLogin);
        }
    };

    //登录按钮点击事件
    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                // TODO: 2017/6/23 0023 执行登录的网络请求
                presenter.login(username,password);
                break;
            case R.id.tv_register:
                // TODO: 2017/6/23 0023 跳转注册页面
                activityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }

    @Override
    public void showPro() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePro() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void registerSuccess() {
        activityUtils.startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void registerFailed() {
        et_userName.setText("");
        et_pwd.setText("");
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}
