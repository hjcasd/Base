package com.hjc.base.ui.home.stateview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.widget.StatusView;
import com.hjc.base.widget.bar.TitleBar;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

@SuppressLint("CheckResult")
@Route(path = RoutePath.URL_STATE_VIEW)
public class StateViewActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.state_view)
    StatusView stateView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_state_view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        stateView.showLoading();
        Observable.timer(2, TimeUnit.SECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> {
                    stateView.showError();
                });
    }


    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(view -> finish());

        stateView.setOnRetryClickListener(v -> {
            stateView.showLoading();
            Observable.timer(2, TimeUnit.SECONDS)
                    .compose(RxSchedulers.ioToMain())
                    .subscribe(aLong -> {
                        stateView.showContent();
                    });
        });
    }

    @Override
    public void onSingleClick(View v) {

    }
}
