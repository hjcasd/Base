package com.hjc.base.ui.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityRoomBinding;
import com.hjc.base.viewmodel.RoomViewModel;
import com.hjc.baselib.activity.BaseActivity;

@Route(path = RoutePath.URL_ROOM)
public class RoomActivity extends BaseActivity<ActivityRoomBinding, RoomViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_room;
    }

    @Override
    protected RoomViewModel getViewModel() {
        return new ViewModelProvider(this).get(RoomViewModel.class);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mBindingView.setRoomViewModel(mViewModel);
    }

    @Override
    protected void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                mViewModel.insert();
                break;

            case R.id.btn2:
                mViewModel.query();
                break;

            case R.id.btn3:
                mViewModel.update();
                break;

            case R.id.btn4:
                mViewModel.delete();
                break;
        }
    }
}
