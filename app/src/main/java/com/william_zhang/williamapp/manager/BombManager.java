//package com.william_zhang.williamapp.manager;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.EditText;
//
//import cn.bmob.newsmssdk.BmobSMS;
//import cn.bmob.newsmssdk.listener.RequestSMSCodeListener;
//import cn.bmob.newsmssdk.listener.SMSCodeListener;
//import cn.bmob.newsmssdk.listener.VerifySMSCodeListener;
//import cn.bmob.sms.BmobSMS;
//
///**
// * Created by william_zhang on 2018/2/8.
// */
//
//public class BombManager {
//    private static final String appid = "ad0e3fbaece03298818d978be1695f2f";
//
//    /**
//     * 初始化操作
//     * @param context
//     * @param editText
//     */
//    public static void SMSInit(Context context, final EditText editText) {
//        BmobSMS.initialize(context, appid, new SMSCodeListener() {
//            @Override
//            public void onReceive(String s) {
//                Log.e("Bmob SMS onReceive:  ", s);
//                editText.setText(s);
//            }
//        });
//    }
//
//    /**
//     * 请求短信
//     * @param context
//     * @param phone
//     * @param requestSMSCodeListener 回调接口
//     */
//    public static void SMSRequest(Context context, String phone, RequestSMSCodeListener requestSMSCodeListener) {
//        BmobSMS.requestSMSCode(context, phone, "", requestSMSCodeListener);
//    }
//
//    /**
//     * 短信验证
//     * @param context
//     * @param phone 手机号
//     * @param code  验证码
//     * @param verifySMSCodeListener  回调接口
//     */
//    public static void verifySmsCode(Context context, String phone, String code, VerifySMSCodeListener verifySMSCodeListener) {
//        BmobSMS.verifySmsCode(context, phone, code, verifySMSCodeListener);
//    }
//}
