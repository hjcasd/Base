package com.hjc.base.ui.frame.quickadapter.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hjc.base.R;
import com.hjc.base.ui.frame.quickadapter.bean.Level1Item;
import com.hjc.base.ui.frame.quickadapter.bean.Level2Item;
import com.hjc.base.ui.frame.quickadapter.bean.Level3Item;

import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:19
 * @Description: 多级可伸缩列表adapter
 */
public class ExpandAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    public static final int TYPE_LEVEL_3 = 3;

    public ExpandAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_1, R.layout.item_expand_level1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expand_level2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expand_level3);
    }


    @Override
    protected void convert(BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_1:
                initExpandItem1(holder, (Level1Item) item);
                break;

            case TYPE_LEVEL_2:
                initExpandItem2(holder, (Level2Item) item);
                break;

            case TYPE_LEVEL_3:
                initExpandItem3(holder, (Level3Item) item);
                break;

            default:
                break;
        }
    }

    private void initExpandItem1(BaseViewHolder holder, Level1Item item) {
        holder.setText(R.id.tv_title, item.getTitle())
                .setImageResource(R.id.iv_arrow, item.isExpanded() ? R.mipmap.icon_arrow_up : R.mipmap.icon_arrow_down);

        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (item.isExpanded()) {
                collapse(pos);
            } else {
                expand(pos);
            }
        });
    }

    private void initExpandItem2(BaseViewHolder holder, Level2Item item) {
        holder.setText(R.id.tv_title, item.getTitle())
                .setImageResource(R.id.iv_arrow, item.isExpanded() ? R.mipmap.icon_arrow_up : R.mipmap.icon_arrow_down);

        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (item.isExpanded()) {
                collapse(pos);
            } else {
                expand(pos);
            }
        });
    }

    private void initExpandItem3(BaseViewHolder holder, Level3Item item) {
        holder.setText(R.id.tv_title, item.getTitle());
    }
}
