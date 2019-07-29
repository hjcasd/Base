package com.hjc.base.ui.frame.quickadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.base.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/7/26 11:27
 * @Description: BaseQuickAdapterHelper的使用
 */
@Route(path = RoutePath.URL_LIST_HELPER)
public class ListHelperActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_empty)
    Button btnEmpty;
    @BindView(R.id.btn_expand)
    Button btnExpand;

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_helper;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnEmpty.setOnClickListener(this);
        btnExpand.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_empty:
                SchemeUtils.jump(RoutePath.URL_LIST_EMPTY);
                break;

            case R.id.btn_expand:
                SchemeUtils.jump(RoutePath.URL_LIST_EXPAND);
                break;

            default:
                break;
        }
    }

}
