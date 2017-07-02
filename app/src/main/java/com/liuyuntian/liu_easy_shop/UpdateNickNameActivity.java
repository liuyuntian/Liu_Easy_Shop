package com.liuyuntian.liu_easy_shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liuyuntian.liu_easy_shop.android.utils.ActivityUtils;
import com.liuyuntian.liu_easy_shop.android.utils.RegexUtils;
import com.liuyuntian.liu_easy_shop.cache.peference.CachePerference;
import com.liuyuntian.liu_easy_shop.mode.UserResult;
import com.liuyuntian.liu_easy_shop.network.EasyShopClient;
import com.liuyuntian.liu_easy_shop.network.UICallBack;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by liuyuntian on 2017/7/2 0002.
 */

public class UpdateNickNameActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.tv_nickname_error)
    TextView tvNicknameError;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.activity_nick_name)
    LinearLayout activityNickName;
    private ActivityUtils activityUtils;
    private String nick_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);
        ButterKnife.bind(this);

        activityUtils = new ActivityUtils(this);
        //toolbar back button
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() ==android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.btn_save)
    public void onClick(){
        nick_name = etNickname.getText().toString();
        //use utils to check nick name
        if(RegexUtils.verifyNickname(nick_name)!= RegexUtils.VERIFY_SUCCESS){
            activityUtils.showToast(R.string.nickname_rules);
            return;
        }
        initUpload();
    }
    //upload nick name
    private void initUpload() {
        //get bean
        UserResult.DataBean user = CachePerference.getInstance().getUser();
        //set nikc name
        user.setNickname(nick_name);
        //update nick name
        Call call = EasyShopClient.getInstance().uploadUser(user);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                activityUtils.showToast(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String json) {
                UserResult result = new Gson().fromJson(json,UserResult.class);
                if(result.getCode()!=1){
                    activityUtils.showToast(result.getMsg());
                    return;
                }
                else{
                    CachePerference.getInstance().save(result.getData());
                    activityUtils.showToast("修改成功！");
                    //back
                    onBackPressed();
                }
            }
        });

    }
}
