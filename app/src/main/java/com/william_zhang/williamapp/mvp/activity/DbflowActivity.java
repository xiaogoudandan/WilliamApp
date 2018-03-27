package com.william_zhang.williamapp.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.william_zhang.base.mvp.baseImpl.BaseActivity;
import com.william_zhang.williamapp.R;
import com.william_zhang.williamapp.adapter.DbflowAdapter;
import com.william_zhang.DbFlowModel;
import com.william_zhang.williamapp.mvp.contact.DbflowContact;
import com.william_zhang.williamapp.mvp.presenter.DbflowPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by william_zhang on 2018/3/27.
 */

public class DbflowActivity extends BaseActivity<DbflowContact.presenter> implements DbflowContact.view {

    @BindView(R.id.dbflow_name)
    EditText dbflowName;
    @BindView(R.id.dbflow_save)
    Button dbflowSave;
    @BindView(R.id.dbflow_recylerview)
    RecyclerView dbflowRecylerview;

    @OnClick(R.id.dbflow_save)
    void save() {
        String name = dbflowName.getText().toString();
        if (!name.isEmpty()) {
            presenter.save(name);
            presenter.refreshRecycler();
        }
    }

    private DbflowAdapter adapter;
    private List<DbFlowModel> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbflow);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        adapter = new DbflowAdapter(R.layout.item_flow, null);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                list = adapter.getData();
                presenter.delete(list.get(position).id, list.get(position).name);
            }
        });
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dbflowRecylerview.setLayoutManager(layoutManager);
        dbflowRecylerview.setAdapter(adapter);
    }

    @Override
    public DbflowContact.presenter initPresenter() {
        return new DbflowPresenter(this);
    }

    @Override
    public void SetRecyclerData(List list) {
        adapter.setNewData(list);
    }

}
