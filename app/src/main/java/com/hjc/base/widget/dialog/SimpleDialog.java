package com.hjc.base.widget.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.hjc.base.R;
import com.hjc.baselib.dialog.BaseDialog;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:28
 * @Description: 简单的dialog
 */
public class SimpleDialog extends BaseDialog {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    public static SimpleDialog newInstance(String title) {
        SimpleDialog dialog = new SimpleDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static SimpleDialog newInstance(String title, String content) {
        SimpleDialog dialog = new SimpleDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("content", content);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int getStyleRes() {
        return R.style.Dialog_Base;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_simple;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString("title");
            tvTitle.setText(title);

            String content = arguments.getString("content");
            if (!StringUtils.isEmpty(content)) {
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText(content);
            } else {
                tvContent.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void addListeners() {
        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:

            case R.id.tv_confirm:
                dismiss();
                break;

            default:
                break;
        }
    }

}
