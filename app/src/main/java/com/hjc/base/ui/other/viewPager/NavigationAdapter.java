package com.hjc.base.ui.other.viewPager;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjc.base.R;

import java.util.List;

public class NavigationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private OnSelectListener listener;
    private int selectedPosition = -1;

    public NavigationAdapter(@Nullable List<String> data) {
        super(R.layout.item_rv_navigation, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        tvTitle.setText(item);

        int position = helper.getAdapterPosition();
        if (selectedPosition == position) {
            tvTitle.setSelected(true);
        } else {
            tvTitle.setSelected(false);
        }

        if (listener != null) {
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelection(position);
                    listener.onSelected(position);
                }
            });
        }
    }

    public void setSelection(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    public interface OnSelectListener {
        void onSelected(int position);
    }

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }
}
