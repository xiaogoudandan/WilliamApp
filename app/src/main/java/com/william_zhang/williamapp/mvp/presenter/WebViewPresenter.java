package com.william_zhang.williamapp.mvp.presenter;

import com.william_zhang.base.mvp.baseImpl.BasePresenterImpl;
import com.william_zhang.williamapp.mvp.contact.WebViewContact;

/**
 * Created by william_zhang on 2018/2/24.
 */

public class WebViewPresenter extends BasePresenterImpl<WebViewContact.view> implements WebViewContact.presenter {
    public WebViewPresenter(WebViewContact.view view) {
        super(view);
    }

    @Override
    public void init() {
        view.init();
    }
}
