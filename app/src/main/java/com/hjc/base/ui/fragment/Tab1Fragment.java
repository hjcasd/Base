package com.hjc.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.ui.home.adapter.MarqueeAdapter;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.base.utils.image.GlideImageLoader;
import com.hjc.base.widget.marquee.MarqueeBaseAdapter;
import com.hjc.base.widget.marquee.MarqueeLayout;
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
    @BindView(R.id.marquee_layout)
    MarqueeLayout marqueeLayout;
    @BindView(R.id.btn_toasty)
    Button btnToasty;
    @BindView(R.id.btn_picker)
    Button btnPicker;
    @BindView(R.id.btn_state)
    Button btnState;

    private MarqueeAdapter marqueeAdapter;


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
        initBanner();
        initMarquee();
    }

    private void initBanner() {
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

    private void initMarquee() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("我是滚动消息" + (i + 1));
        }

        marqueeAdapter = new MarqueeAdapter(mContext, list);
        marqueeLayout.setAdapter(marqueeAdapter);

        marqueeLayout.start();
    }

    @Override
    public void addListeners() {
        btnToasty.setOnClickListener(this);
        btnPicker.setOnClickListener(this);
        btnState.setOnClickListener(this);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showShort("position---" + position);
            }
        });

        marqueeAdapter.setOnItemClickListener(new MarqueeBaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ToastUtils.showShort("position---" + position);
            }
        });
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_toasty:
                SchemeUtils.jump(RoutePath.URL_TOASTY);
                break;

            case R.id.btn_picker:
                SchemeUtils.jump(RoutePath.URL_PICK_VIEW);
                break;

            case R.id.btn_state:
                SchemeUtils.jump(RoutePath.URL_STATE_VIEW);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        if (banner != null) {
            banner.stopAutoPlay();
        }
        if (marqueeLayout != null) {
            marqueeLayout.stop();
        }
        super.onDestroyView();
    }

}
