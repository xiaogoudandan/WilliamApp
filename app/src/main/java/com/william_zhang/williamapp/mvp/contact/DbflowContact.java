package com.william_zhang.williamapp.mvp.contact;

import com.william_zhang.base.mvp.BasePresenter;
import com.william_zhang.base.mvp.BaseView;

import java.util.List;

/**
 * Created by william_zhang on 2018/3/27.
 */

public interface DbflowContact {
    interface presenter extends BasePresenter {
        void save(String name);
        void refreshRecycler();

        void delete(int id, String name);
    }

    interface view extends BaseView {
        void SetRecyclerData(List list);
    }
}
