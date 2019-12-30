package com.hjc.base.ui.frame.quickadapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.ui.frame.quickadapter.adapter.EmptyAdapter;
import com.hjc.base.widget.dialog.LoadingDialog;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:21
 * @Description: 列表空布局
 */
@SuppressLint("CheckResult")
@Route(path = RoutePath.URL_LIST_EMPTY)
public class ListEmptyActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private View emptyView;
    private TextView tvStateContent;

    private LoadingDialog loadingDialog;
    private EmptyAdapter mAdapter;

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
        rvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EmptyAdapter(null);
        rvList.setAdapter(mAdapter);

        loadingDialog.showDialog(getSupportFragmentManager());

        Observable.timer(2, TimeUnit.SECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> {
                    mAdapter.setEmptyView(emptyView);
                    loadingDialog.dismiss();
                });
    }

    @Override
    public void addListeners() {
        tvStateContent.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort("position---" + position);
            }
        });
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.tv_state_content:
                loadingDialog.showDialog(getSupportFragmentManager());

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

            default:
                break;
        }
    }

}
