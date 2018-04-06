package com.william_zhang.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.annotations.NonNull;

/**
 * Created by william_zhang on 2018/4/6.
 */

public class NetWorkUtil {
    public static final int INVALID_NETWORK_TYPE = -1;//不可用
    public static final int MOBILE_NETWORK_TYPE = 0;//数据流量
    public static final int WIFI_NETWORK_TYPE = 1;//wifi

    /**
     * 是否wifi或者数据可用
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(@NonNull Context context) {
        boolean isWifi = false;
        boolean isMoblie = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equals("WIFI")) {
                if (info.isConnected() && info.isAvailable()) {
                    isWifi = true;
                }
            }
            if (info.getTypeName().equals("MOBILE")) {
                if (info.isConnected() && info.isAvailable()) {
                    isMoblie = true;
                }
            }
        }
        return isMoblie || isWifi;
    }

    /**
     * 当前网络类型
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * 当前连接是否是wifi
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable()&&mWiFiNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * 当前连接是否是数据
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable()&&mMobileNetworkInfo.isConnected();
            }
        }
        return false;
    }

}
