package com.william_zhang.williamapp.mvp.contact;

import com.william_zhang.base.mvp.BasePresenter;
import com.william_zhang.base.mvp.BaseView;

/**
 * Created by william_zhang on 2018/2/24.
 */

public interface WebViewContact {
    interface presenter extends BasePresenter{
        void init();
    }
    interface view extends BaseView{
        void init();
    }
}
