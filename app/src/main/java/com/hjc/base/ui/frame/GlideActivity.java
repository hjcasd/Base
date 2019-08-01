package com.hjc.base.ui.frame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.utils.image.ImageManager;
import com.hjc.base.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/8/1 11:24
 * @Description: Glide的使用
 */
@Route(path = RoutePath.URL_GLIDE)
public class GlideActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_round)
    Button btnRound;
    @BindView(R.id.btn_circle)
    Button btnCircle;
    @BindView(R.id.btn_blur)
    Button btnBlur;
    @BindView(R.id.iv_pic1)
    ImageView ivPic1;
    @BindView(R.id.iv_pic2)
    ImageView ivPic2;

    private static final String TEST_URL1 = "http://img5.imgtn.bdimg.com/it/u=3533273899,4010966762&fm=27&gp=0.jpg";
    private static final String TEST_URL2 = "http://img0.imgtn.bdimg.com/it/u=1824237584,1741633210&fm=27&gp=0.jpg";
    private static final String TEST_URL3 = "http://img0.imgtn.bdimg.com/it/u=3389517482,693147730&fm=27&gp=0.jpg";

    @Override
    public int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ImageManager.loadImage(ivPic1, TEST_URL1);
    }

    @Override
    public void addListeners() {
        btnRound.setOnClickListener(this);
        btnCircle.setOnClickListener(this);
        btnBlur.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(v -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_round:
                ImageManager.loadRoundImage(ivPic1, TEST_URL2, ConvertUtils.dp2px(10));
                break;

            case R.id.btn_circle:
                ImageManager.loadCircleImage(ivPic2, TEST_URL3);
                break;

            case R.id.btn_blur:
                ImageManager.loadBlurImage(ivPic1, TEST_URL2,  15, 2);
                break;

            default:
                break;
        }
    }
}
