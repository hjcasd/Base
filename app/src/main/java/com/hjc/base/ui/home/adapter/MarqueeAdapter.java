package com.hjc.base.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.widget.marquee.BaseMarqueeAdapter;

import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:25
 * @Description: 消息滚动条adapter
 */
public class MarqueeAdapter  extends BaseMarqueeAdapter<String> {
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

        itemView.setOnClickListener(v -> ToastUtils.showShort(item));
    }
}