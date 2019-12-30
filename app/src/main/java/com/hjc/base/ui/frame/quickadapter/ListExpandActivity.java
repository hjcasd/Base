package com.hjc.base.ui.frame.quickadapter;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.ui.frame.quickadapter.adapter.ExpandAdapter;
import com.hjc.base.ui.frame.quickadapter.bean.Level1Item;
import com.hjc.base.ui.frame.quickadapter.bean.Level2Item;
import com.hjc.base.ui.frame.quickadapter.bean.Level3Item;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:22
 * @Description: 多级伸缩列表
 */
@Route(path = RoutePath.URL_LIST_EXPAND)
public class ListExpandActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private ExpandAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_expand;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvList.setLayoutManager(manager);

        List<MultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Level1Item level1Item = new Level1Item();
            level1Item.setTitle("我是一级列表中的第" + (i+ 1) + "个条目");

            for (int j = 0; j < 4; j++) {
                Level2Item level2Item = new Level2Item();
                level2Item.setTitle("我是二级列表中的第" + (j+ 1) + "个条目");

                for (int k = 0; k < 2; k++) {
                    Level3Item level3Item = new Level3Item();
                    level3Item.setTitle("我是三级列表中的第" + (k+ 1) + "个条目");

                    level2Item.addSubItem(level3Item);
                }
                level1Item.addSubItem(level2Item);
            }
            list.add(level1Item);
        }
        adapter = new ExpandAdapter(list);
        rvList.setAdapter(adapter);
    }

    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {

    }
}
