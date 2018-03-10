package com.william_zhang.williamapp.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.william_zhang.williamapp.R;
import com.william_zhang.williamapp.widget.EventRelativeLayout;
import com.william_zhang.williamapp.widget.EventTestView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by william_zhang on 2018/3/10.
 */

public class EventActivity extends AppCompatActivity {
    static final String TAG = "EventActivity";
    @BindView(R.id.EventTestView)
    EventTestView EventTestView;
    @BindView(R.id.EventRelativeLayout)
    EventRelativeLayout EventRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent down");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent move");
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent down");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent move");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
