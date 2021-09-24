package com.hjc.base.ui.video;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.databinding.ActivityVideoBinding;
import com.hjc.base.router.RoutePath;
import com.hjc.base.ui.video.adapter.MyFragmentPagerAdapter;
import com.hjc.base.ui.video.fragment.PictureFragment;
import com.hjc.base.ui.video.fragment.VideoFragment;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.activity.BaseActivity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:29
 * @Description: 视频播放页
 */
@Route(path = RoutePath.URL_VIDEO)
public class VideoActivity extends BaseActivity<ActivityVideoBinding, CommonViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_video;
    }

    @Nullable
    @Override
    public CommonViewModel createViewModel() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mBindingView.clRoot.getLayoutParams();
        layoutParams.rightMargin = BarUtils.getStatusBarHeight();
        mBindingView.clRoot.setLayoutParams(layoutParams);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(VideoFragment.newInstance());
        fragments.add(PictureFragment.newInstance());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments);
        mBindingView.viewPager.setAdapter(adapter);
    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                finish();
                break;

            case R.id.ll_bottom_panel:
                ToastUtils.showShort("左边面板");
                break;

            case R.id.ll_right_panel:
                ToastUtils.showShort("右边面板");
                break;
        }
    }

}
