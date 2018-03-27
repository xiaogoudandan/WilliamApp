package com.william_zhang.williamapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.william_zhang.williamapp.mvp.activity.DbflowActivity;
import com.william_zhang.williamapp.mvp.activity.EventActivity;
import com.william_zhang.williamapp.mvp.activity.NodeLoginActivity;
import com.william_zhang.williamapp.mvp.activity.SerializationActivity_A;
import com.william_zhang.williamapp.mvp.activity.WebViewAcitivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void button(View view) {
        switch (view.getId()) {
            case R.id.btn_mob:
                forword(MainActivity.this, NodeLoginActivity.class);
                break;
            case R.id.btn_webview:
                forword(MainActivity.this, WebViewAcitivity.class);
                break;
            case R.id.btn_Serialization:
                forword(MainActivity.this, SerializationActivity_A.class);
                break;
            case R.id.btn_event:
                forword(MainActivity.this, EventActivity.class);
                break;
            case R.id.btn_dbflow:
                forword(MainActivity.this, DbflowActivity.class);
                break;
        }
    }

    public void forword(Context packageContext, Class<?> cls) {
        startActivity(new Intent(packageContext, cls));
    }
}
