package com.liuyuntian.liu_easy_shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.liuyuntian.liu_easy_shop.android.utils.ActivityUtils;
import com.liuyuntian.liu_easy_shop.android.utils.RegexUtils;
import com.liuyuntian.liu_easy_shop.presenter.RegisterPresenter;
import com.liuyuntian.liu_easy_shop.view_interface.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class RegisterActivity extends MvpActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwdAgain)
    EditText etPwdAgain;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.activity_register)
    RelativeLayout activityRegister;
    String username;
    String password;
    ActivityUtils activityUtils;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        etUsername.addTextChangedListener(textWatcher);
        etPwd.addTextChangedListener(textWatcher);
        etPwdAgain.addTextChangedListener(textWatcher);
        activityUtils = new ActivityUtils(this);
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return  new RegisterPresenter();
    }


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
            username = etUsername.getText().toString();
            password = etPwd.getText().toString();
            //判断用户民和密码是否为空
            boolean canLogin = (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && password.equals(etPwdAgain.getText().toString()));
            btnRegister.setEnabled(canLogin);
        }
    };

    //点击注册操作
    @OnClick(R.id.btn_register)
    public void onClickReg(View view) {
        //业务操作
        //使用正则表达式限制用户名及密码
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(R.string.username_rules);
            return;
        } else if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(R.string.password_rules);
            return;
            //判断两次密码相同
        } else if (!TextUtils.equals(password, etPwdAgain.getText().toString())) {
            activityUtils.showToast(R.string.username_equal_pwd);
            return;
        }
        presenter.register(username,password);
    }


    @Override
    public void showPro() {
        activityUtils.hideSoftKeyboard();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePro() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess() {
        finish();
    }

    @Override
    public void registerFailed() {
        etUsername.setText("");
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
