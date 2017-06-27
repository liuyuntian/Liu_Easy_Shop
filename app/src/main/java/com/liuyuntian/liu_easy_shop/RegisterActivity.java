package com.liuyuntian.liu_easy_shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class RegisterActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        etUsername.addTextChangedListener(textWatcher);
        etPwd.addTextChangedListener(textWatcher);
        etPwdAgain.addTextChangedListener(textWatcher);

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
            boolean canLogin = (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)&&password.equals(etPwdAgain.getText().toString()));
            btnRegister.setEnabled(canLogin);
        }
    };

}
