package com.william_zhang.williamapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by william_zhang on 2018/3/10.
 */

public class EventRelativeLayout extends RelativeLayout {
    static final String TAG="EventRelativeLayout";
    public EventRelativeLayout(Context context) {
        super(context);
    }

    public EventRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"onInterceptTouchEvent down");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"onInterceptTouchEvent up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"onInterceptTouchEvent move");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"onTouchEvent onTouchEvent down");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"onTouchEvent onTouchEvent up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"onTouchEvent onTouchEvent move");
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"dispatchTouchEvent down");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"dispatchTouchEvent up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"dispatchTouchEvent move");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


}
