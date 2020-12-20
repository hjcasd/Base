package com.hjc.base.ui.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityWebBinding;
import com.hjc.base.utils.AppUtils;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2020/8/27 15:50
 * @Description: web页面
 */
@Route(path = RoutePath.URL_WEB)
public class WebActivity extends BaseMvmActivity<ActivityWebBinding, CommonViewModel> {
    @Autowired(name = "title")
    String mTitle = "";

    @Autowired(name = "url")
    String mUrl = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mBindingView.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mBindingView.toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.mipmap.icon_more));
        mBindingView.tvTitle.setSelected(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);

        if (!StringUtils.isEmpty(mTitle) && !StringUtils.isEmpty(mUrl)) {
            mBindingView.tvTitle.setText(mTitle);
            mBindingView.webLayout.loadUrl(mUrl);
//            mBindingView.webView.loadUrl(mUrl);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            // 刷新页面
            case R.id.item_refresh:
                mBindingView.webLayout.reload();
//                mBindingView.webView.reload();
                break;

            // 分享
            case R.id.item_share:
                AppUtils.share(this, mUrl);
                break;

            // 复制链接
            case R.id.item_copy:
                AppUtils.copy(this, mUrl);
                ToastUtils.showShort("复制成功");
                break;

            // 打开链接
            case R.id.item_open:
                AppUtils.openLink(this, mUrl);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void onSingleClick(View v) {

    }

    @Override
    public void onBackPressed() {
        if (mBindingView.webLayout.canGoBack()) {
            mBindingView.webLayout.goBack();
        } else {
            finish();
        }
//        if (mBindingView.webView.canGoBack()) {
//            mBindingView.webView.goBack();
//        } else {
//            finish();
//        }
    }
}
