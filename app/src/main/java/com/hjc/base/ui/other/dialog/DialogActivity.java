package com.hjc.base.ui.other.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.widget.bar.TitleBar;
import com.hjc.base.widget.dialog.ConfirmDialog;
import com.hjc.base.widget.dialog.LoadingDialog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

@Route(path = RoutePath.URL_DIALOG)
public class DialogActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_loading)
    Button btnLoading;
    @BindView(R.id.btn_animation)
    Button btnAnimation;

    private LoadingDialog loadingDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    public void initView() {
        loadingDialog = LoadingDialog.newInstance();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnLoading.setOnClickListener(this);
        btnAnimation.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loading:
                loadingDialog.showDialog(getSupportFragmentManager());
                Observable.timer(2, TimeUnit.SECONDS)
                        .compose(RxSchedulers.ioToMain())
                        .subscribe(aLong -> loadingDialog.dismissDialog());
                break;


            case R.id.btn_animation:
                ConfirmDialog.newInstance()
                        .setAnimStyle(R.style.dialog_anim_bottom)
                        .showDialog(getSupportFragmentManager());
                break;

            default:
                break;
        }
    }
}
