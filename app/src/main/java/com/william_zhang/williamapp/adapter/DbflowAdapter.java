package com.william_zhang.williamapp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.william_zhang.williamapp.R;
import com.william_zhang.DbFlowModel;

import java.util.List;

/**
 * Created by william_zhang on 2018/3/27.
 */

public class DbflowAdapter extends BaseQuickAdapter<DbFlowModel, BaseViewHolder> {

    public DbflowAdapter(int layoutResId, @Nullable List<DbFlowModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DbFlowModel item) {
        helper.setText(R.id.item_flow_name, item.name).addOnClickListener(R.id.item_flow_delete);
    }

}
