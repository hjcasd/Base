package com.hjc.base.ui.frame.quickadapter.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hjc.base.ui.frame.quickadapter.adapter.ExpandAdapter;


public class Level1Item extends AbstractExpandableItem<Level2Item> implements MultiItemEntity {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getItemType() {
        return ExpandAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}