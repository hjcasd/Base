package com.hjc.base.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.model.LocalBean;
import com.hjc.base.widget.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RoutePath.URL_PICK_VIEW)
public class PickerActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_date)
    Button btnDate;
    @BindView(R.id.btn_address)
    Button btnAddress;
    @BindView(R.id.btn_custom)
    Button btnCustom;

    private List<LocalBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>>options3Items = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_picker;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    /**
     * 初始化数据
     */
    private void initJsonData() {

    }

    @Override
    public void addListeners() {
        btnDate.setOnClickListener(this);
        btnAddress.setOnClickListener(this);
        btnCustom.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()){
            case R.id.btn_date:
                break;

            case R.id.btn_address:
                break;

            case R.id.btn_custom:
                break;
        }
    }
}
