package com.hjc.base.ui.list;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjc.base.R;

import java.util.List;

public class EmptyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public EmptyAdapter(@Nullable List<String> data) {
        super(R.layout.item_rv_test, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content, item);

    }
}
