package com.william_zhang.williamapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by william_zhang on 2018/3/10.
 */

public class EventTestView extends android.support.v7.widget.AppCompatTextView {
    static final String TAG="EventTestView";
    public EventTestView(Context context) {
        super(context);
    }

    public EventTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"onTouchEvent down");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"onTouchEvent up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"onTouchEvent move");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
