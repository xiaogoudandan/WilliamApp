package com.william_zhang.williamapp.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.william_zhang.base.mvp.baseImpl.BaseActivity;
import com.william_zhang.williamapp.MainActivity;
import com.william_zhang.williamapp.R;
//import com.william_zhang.williamapp.manager.BombManager;
import com.william_zhang.williamapp.mvp.contact.NodeLoginContact;
import com.william_zhang.williamapp.mvp.presenter.NodeLoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;

/**
 * Created by william_zhang on 2018/2/8.
 */

public class NodeLoginActivity extends BaseActivity<NodeLoginContact.presenter> implements NodeLoginContact.view {
    @BindView(R.id.nodelogin_phone)
    EditText loginPhone;
    @BindView(R.id.nodelogin_sms)
    EditText loginSms;
    @BindView(R.id.nodelogin_time)
    Button loginTime;
    @BindView(R.id.nodelogin_login)
    Button loginLogin;

    @OnClick(R.id.nodelogin_time)
    void time() {
        //发送短信
        presenter.smsRequest(this,loginPhone.getText().toString());
        //倒计时开始
        loginTime.setEnabled(false);//不可点击
        presenter.time(60);
    }

    @OnClick(R.id.nodelogin_login)
    void login(){
        presenter.verifySmsCode(this,loginSms.getText().toString());
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodelogin);
        ButterKnife.bind(this);
        presenter.init();
    }

    @Override
    public void init() {
        loginPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();
                if (str.length() == 11) {
                    loginTime.setEnabled(true);
                    loginTime.setAlpha(1.0f);
                } else {
                    loginTime.setEnabled(false);
                    loginTime.setAlpha(0.5f);
                }
            }
        });
        //初始化短信验证
//        BombManager.SMSInit(context,loginSms);
    }

    @Override
    public void setTimeText(String s) {
        if(s.equals("接收")){
            loginTime.setEnabled(true);//不可点击
        }
        loginTime.setText(s);
    }

    @Override
    public void main() {
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public NodeLoginContact.presenter initPresenter() {
        return new NodeLoginPresenter(this);
    }

    @Override
    public void newStateChange(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
