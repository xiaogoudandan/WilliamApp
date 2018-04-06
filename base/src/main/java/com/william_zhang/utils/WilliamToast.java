package com.william_zhang.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by william_zhang on 2018/4/6.
 */

/**
 * Toast不会重复产生
 */
public class WilliamToast {
    private static Context mContext;
    private static Toast mToast;

    /**
     * applicationContext 绑定给Toast
     * 建议在application中调用init（）；
     *
     * @param context
     */
    public static void init(@NonNull Context context) {
        mContext = context;
    }

    public static void show(String test) {
        if (mContext == null) {
            throw new NullPointerException();
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, test, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(test);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void show(int id) {
        if (mContext == null) {
            throw new NullPointerException();
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, id, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(id);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLong(int id) {
        if (mContext == null) {
            throw new NullPointerException();
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, id, Toast.LENGTH_LONG);
        } else {
            mToast.setText(id);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }


    public static void showLong(String test) {
        if (mContext == null) {
            throw new NullPointerException();
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, test, Toast.LENGTH_LONG);
        } else {
            mToast.setText(test);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
