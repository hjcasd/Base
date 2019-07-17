package com.hjc.base.ui.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.widget.bar.OnViewLeftClickListener;
import com.hjc.base.widget.bar.TitleBar;

import butterknife.BindView;

public class ListExpandActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_expand;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(new OnViewLeftClickListener() {
            @Override
            public void leftClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onSingleClick(View v) {

    }
}
