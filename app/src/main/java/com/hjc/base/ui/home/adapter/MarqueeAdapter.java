package com.hjc.base.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hjc.base.R;
import com.hjc.base.widget.marquee.MarqueeBaseAdapter;

import java.util.List;

public class MarqueeAdapter  extends MarqueeBaseAdapter<String> {
    public MarqueeAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_marquee;
    }

    @Override
    protected void initView(View itemView, int position, String item) {
        TextView tvContent = itemView.findViewById(R.id.tv_content);
        tvContent.setText(item);
    }
}
