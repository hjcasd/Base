package com.hjc.base.ui.home;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.google.gson.Gson;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.model.LocalBean;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/11/1 17:54
 * @Description: AndroidPickerView的使用
 */
@SuppressWarnings("ALL")
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
    @BindView(R.id.wheel_view)
    WheelView wheelView;

    private List<LocalBean> options1Items;
    private List<List<String>> options2Items;
    private List<List<List<String>>> options3Items;

    @Override
    public int getLayoutId() {
        return R.layout.activity_picker;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        options1Items = new ArrayList<>();
        options2Items = new ArrayList<>();
        options3Items = new ArrayList<>();

        initJsonData();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("我是滚动的条目" + (i + 1));
        }
        WheelAdapter adapter = new ArrayWheelAdapter(list);
        wheelView.setAdapter(adapter);
        wheelView.setCurrentItem(0);
        wheelView.setCyclic(false);
    }

    /**
     * 初始化数据
     */
    private void initJsonData() {
        String json = getJson();
        List<LocalBean> provinceList = parseData(json);

        /*
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = provinceList;

        // 遍历省份
        for (int i = 0; i < provinceList.size(); i++) {
            // 该省的城市列表（第二级）
            List<String> cityList = new ArrayList<>();
            // 该省的所有地区列表（第三级）
            List<List<String>> provinceAreaList = new ArrayList<>();

            // 遍历该省份的所有城市
            for (int j = 0; j < provinceList.get(i).getCity().size(); j++) {
                String cityName = provinceList.get(i).getCity().get(j).getName();
                // 添加城市
                cityList.add(cityName);
                // 该城市的所有地区列表
                List<String> cityAreaList = new ArrayList<>();

                // 如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (provinceList.get(i).getCity().get(j).getArea() == null || provinceList.get(i).getCity().get(j).getArea().size() == 0) {
                    cityAreaList.add("");
                } else {
                    cityAreaList.addAll(provinceList.get(i).getCity().get(j).getArea());
                }
                cityAreaList.addAll(provinceList.get(i).getCity().get(j).getArea());
                // 添加该省所有地区数据
                provinceAreaList.add(cityAreaList);
            }
            // 添加城市数据
            options2Items.add(cityList);
            // 添加地区数据
            options3Items.add(provinceAreaList);
        }
    }

    /**
     * 获取Assets目录下的json
     *
     * @return json字符串
     */
    private String getJson() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("province.json");
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private List<LocalBean> parseData(String result) {//Gson 解析
        List<LocalBean> list = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                LocalBean entity = gson.fromJson(data.optJSONObject(i).toString(), LocalBean.class);
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
        switch (v.getId()) {
            case R.id.btn_date:
                showTimePicker();
                break;

            case R.id.btn_address:
                showOptionPicker();
                break;

            case R.id.btn_custom:
                showCustomPicker();
                break;

            default:
                break;
        }
    }

    /**
     * 日期选择器
     */
    private void showTimePicker() {
        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> ToastUtils.showShort(TimeUtils.date2String(date)))
                .setTitleText("请选择时间")
                .setTitleSize(18)
                .setTitleColor(Color.BLUE)
                .setContentTextSize(14)
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();

        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }

    /**
     * 地区选择器
     */
    private void showOptionPicker() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String address = options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get(options2)
                        + options3Items.get(options1).get(options2).get(options3);
                ToastUtils.showShort(address);
            }
        })
                .setTitleText("请选择地址")
                .setTitleSize(18)
                .setTitleColor(Color.BLUE)
                .setContentTextSize(14)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }


    /**
     * 自定义选择器
     */
    private void showCustomPicker() {
        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> ToastUtils.showShort(TimeUtils.date2String(date)))
                .setTitleText("请选择时间")
                .setTitleSize(18)
                .setTitleColor(Color.BLUE)
                .setContentTextSize(12)
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true)
                .build();

        // dialog形式显示在底部
        Dialog dialog = pvTime.getDialog();
        if (dialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = dialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.4f);
            }
        }
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }
}
