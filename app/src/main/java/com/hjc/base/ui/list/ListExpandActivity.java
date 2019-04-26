package com.hjc.base.ui.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.widget.TitleBar;

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
        titleBar.setOnViewClickListener(new TitleBar.onViewClick() {
            @Override
            public void leftClick(View view) {
                finish();
            }

            @Override
            public void rightClick(View view) {

            }
        });
    }

    @Override
    public void onSingleClick(View v) {

    }
}
