package com.hjc.base.ui.frame.quickadapter.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hjc.base.ui.frame.quickadapter.adapter.ExpandAdapter;


public class Level3Item implements MultiItemEntity {
    private String title;
    private int image;

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
        return ExpandAdapter.TYPE_LEVEL_3;
    }
}