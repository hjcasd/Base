package com.hjc.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.widget.bar.OnViewLeftClickListener;
import com.hjc.base.widget.bar.TitleBar;
import com.hjc.base.widget.web.X5WebView;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:47
 * @Description: 含有X5WebView的Activity基类
 */
public class WebActivity extends BaseActivity {
    @BindView(R.id.web_view)
    X5WebView webView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String title = bundle.getString("title");
                String url = bundle.getString("url");

                titleBar.setTitle(title);
                webView.loadUrl(url);
            }
        }
    }

    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(new OnViewLeftClickListener() {
            @Override
            public void leftClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onSingleClick(View v) {

    }

}
