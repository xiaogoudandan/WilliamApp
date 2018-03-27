package com.william_zhang.williamapp.mvp.presenter;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.william_zhang.DbFlowModel_Table;
import com.william_zhang.base.mvp.baseImpl.BasePresenterImpl;
import com.william_zhang.DbFlowModel;
import com.william_zhang.williamapp.mvp.contact.DbflowContact;

import java.util.List;

/**
 * Created by william_zhang on 2018/3/27.
 */

public class DbflowPresenter extends BasePresenterImpl<DbflowContact.view> implements DbflowContact.presenter {
    public DbflowPresenter(DbflowContact.view view) {
        super(view);
    }

    @Override
    public void save(String name) {
        List<DbFlowModel> list = SQLite.select().from(DbFlowModel.class).where(DbFlowModel_Table.name.eq(name)).queryList();
        if (list.size() > 0) {
            view.toast(name + " 已经存在");
            return;
        }
        DbFlowModel dbFlowModel = new DbFlowModel();
        dbFlowModel.name = name;
        dbFlowModel.save();
    }

    @Override
    public void refreshRecycler() {
        List<DbFlowModel> list = SQLite.select().from(DbFlowModel.class).queryList();
        view.SetRecyclerData(list);
    }

    @Override
    public void delete(int id, String name) {
        SQLite.delete().from(DbFlowModel.class).where(DbFlowModel_Table.id.eq(id), DbFlowModel_Table.name.eq(name)).execute();
        refreshRecycler();
    }
}
