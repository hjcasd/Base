package com.hjc.base.ui.frame.quickadapter.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjc.base.R;

import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:18
 * @Description: 测试adapter
 */
public class EmptyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public EmptyAdapter(@Nullable List<String> data) {
        super(R.layout.item_card_test, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content, item);

    }
}
