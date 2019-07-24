package com.hjc.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.utils.image.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class Tab1Fragment extends BaseImmersionFragment {

    @BindView(R.id.banner)
    Banner banner;

    public static Tab1Fragment newInstance() {
        return new Tab1Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab1;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<String> imgList = new ArrayList<>();
        imgList.add("http://img5.imgtn.bdimg.com/it/u=3533273899,4010966762&fm=27&gp=0.jpg");
        imgList.add("http://img0.imgtn.bdimg.com/it/u=1824237584,1741633210&fm=27&gp=0.jpg");
        imgList.add("http://img0.imgtn.bdimg.com/it/u=3389517482,693147730&fm=27&gp=0.jpg");

        banner.setImages(imgList)
                .setImageLoader(new GlideImageLoader())
                .setBannerAnimation(Transformer.Accordion)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }

    @Override
    public void addListeners() {
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showShort("position---" + position);
            }
        });
    }

    @Override
    public void onSingleClick(View v) {

    }

    @Override
    public void onDestroyView() {
        if (banner != null) {
            banner.stopAutoPlay();
        }
        super.onDestroyView();
    }
}
