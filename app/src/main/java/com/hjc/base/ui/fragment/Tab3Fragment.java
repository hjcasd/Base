package com.hjc.base.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjc.base.R;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.ui.list.ListEmptyActivity;
import com.hjc.base.ui.list.ListRefreshActivity;
import com.hjc.base.ui.list.StateViewActivity;

import butterknife.BindView;

public class Tab3Fragment extends BaseImmersionFragment {
    @BindView(R.id.tv_event)
    TextView tvEvent;
    @BindView(R.id.btn_empty)
    Button btnEmpty;
    @BindView(R.id.btn_refresh)
    Button btnRefresh;
    @BindView(R.id.btn_router)
    Button btnRouter;
    @BindView(R.id.btn_state)
    Button btnState;

    public static Tab3Fragment newInstance() {
        return new Tab3Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab3;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnEmpty.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);
        btnState.setOnClickListener(this);
        btnRouter.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_empty:
                startActivity(new Intent(mContext, ListEmptyActivity.class));
                break;

            case R.id.btn_refresh:
                startActivity(new Intent(mContext, ListRefreshActivity.class));
                break;

            case R.id.btn_state:
                startActivity(new Intent(mContext, StateViewActivity.class));
                break;

            case R.id.btn_router:
                ARouter.getInstance().build(RoutePath.URL_LOGIN).navigation();
                break;
        }
    }

}
