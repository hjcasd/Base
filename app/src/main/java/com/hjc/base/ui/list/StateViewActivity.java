package com.hjc.base.ui.list;

import android.os.Bundle;
import android.view.View;

import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.widget.TitleBar;
import com.hjc.base.widget.StatusView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

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
        stateView.showLoading();
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
        titleBar.setOnViewClickListener(new TitleBar.onViewClick() {
            @Override
            public void leftClick(View view) {
                finish();
            }

            @Override
            public void rightClick(View view) {

            }
        });

        stateView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateView.showLoading();
                Observable.timer(2, TimeUnit.SECONDS)
                        .compose(RxSchedulers.ioToMain())
                        .subscribe(aLong -> {
                            stateView.showContent();
                        });
            }
        });
    }

    @Override
    public void onSingleClick(View v) {

    }
}
