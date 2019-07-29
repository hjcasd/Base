package com.hjc.base.ui.frame.quickadapter.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hjc.base.ui.frame.quickadapter.adapter.ExpandAdapter;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:20
 * @Description: 二级列表bean
 */
public class Level2Item extends AbstractExpandableItem<Level3Item> implements MultiItemEntity {
    private int image;
    private String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return ExpandAdapter.TYPE_LEVEL_2;
    }

    @Override
    public int getLevel() {
        return 2;
    }
}