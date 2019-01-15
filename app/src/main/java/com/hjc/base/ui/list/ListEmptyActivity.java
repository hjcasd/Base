package com.hjc.base.ui.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.widget.TitleBar;
import com.hjc.base.widget.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

public class ListEmptyActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private View emptyView;
    private TextView tvStateContent;

    private LoadingDialog loadingDialog;
    private ListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_empty;
    }

    @Override
    public void initView() {
        emptyView = getLayoutInflater().inflate(R.layout.layout_empty_view, (ViewGroup) rvList.getParent(), false);
        tvStateContent = emptyView.findViewById(R.id.tv_state_content);

        loadingDialog = LoadingDialog.newInstance();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        loadingDialog.show(getSupportFragmentManager());

        rvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListAdapter(null);
        rvList.setAdapter(mAdapter);

        Observable.timer(2, TimeUnit.SECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> {
                    mAdapter.setEmptyView(emptyView);
                    loadingDialog.dismiss();
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

        tvStateContent.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()){
            case R.id.tv_state_content:
                loadingDialog.show(getSupportFragmentManager());
                Observable.timer(2, TimeUnit.SECONDS)
                        .compose(RxSchedulers.ioToMain())
                        .subscribe(aLong -> {
                            List<String> list = new ArrayList<>();
                            for (int i = 0; i < 20; i++) {
                                list.add("i===" + i);
                            }
                            mAdapter.setNewData(list);
                            loadingDialog.dismiss();
                        });
                break;
        }
    }

}
