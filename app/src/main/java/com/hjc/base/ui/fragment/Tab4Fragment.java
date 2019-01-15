package com.hjc.base.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hjc.base.R;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.ui.drawer.DrawerCustomActivity;
import com.hjc.base.ui.drawer.DrawerNavigationActivity;
import com.hjc.base.ui.keyboard.KeyboardActivity;
import com.hjc.base.widget.dialog.LoadingDialog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;


public class Tab4Fragment extends BaseImmersionFragment {
    @BindView(R.id.btn_keyboard)
    Button btnKeyboard;
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    @BindView(R.id.btn_drawer_navigation)
    Button btnDrawerNavigation;
    @BindView(R.id.btn_drawer_custom)
    Button btnDrawerCustom;

    private LoadingDialog loadingDialog;

    public static Tab4Fragment newInstance() {
        Tab4Fragment fragment = new Tab4Fragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab4;
    }

    @Override
    public void initView() {
        loadingDialog = LoadingDialog.newInstance();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnKeyboard.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        btnDrawerNavigation.setOnClickListener(this);
        btnDrawerCustom.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_keyboard:
                startActivity(new Intent(mContext, KeyboardActivity.class));
                break;

            case R.id.btn_dialog:
                loadingDialog.show(getFragmentManager());
                Observable.timer(2, TimeUnit.SECONDS)
                        .compose(RxSchedulers.ioToMain())
                        .subscribe(aLong -> {
                            loadingDialog.dismiss();
                        });
                break;

            case R.id.btn_drawer_navigation:
                startActivity(new Intent(mContext, DrawerNavigationActivity.class));
                break;

            case R.id.btn_drawer_custom:
                startActivity(new Intent(mContext, DrawerCustomActivity.class));
                break;

        }
    }

}
