package com.hjc.base.ui.search.adapter;

import android.content.Context;

import com.hjc.base.R;
import com.hjc.base.base.adapter.BaseRecyclerAdapter;
import com.hjc.base.base.adapter.BaseViewHolder;

import java.util.List;

public class TestAdapter extends BaseRecyclerAdapter<String> {

    public TestAdapter(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_rv_test;
    }

    @Override
    protected void initView(BaseViewHolder holder) {

    }

    @Override
    protected void bindData(String data, int position) {

    }

    @Override
    protected void addListener(String data, int position) {

    }
}
