package com.william_zhang.williamapp.mvp.contact;

import com.william_zhang.base.mvp.BasePresenter;
import com.william_zhang.base.mvp.BaseView;
import com.william_zhang.williamapp.mvp.activity.NodeLoginActivity;

/**
 * Created by william_zhang on 2018/2/8.
 */

public interface NodeLoginContact {
    interface presenter extends BasePresenter{
        void init();
        void time(int time);

        void smsRequest(NodeLoginActivity nodeLoginActivity, String s);

        void verifySmsCode(NodeLoginActivity nodeLoginActivity, String s);
    }

    interface view extends BaseView{
        void init();

        void setTimeText(String s);

        void main();
    }
}
