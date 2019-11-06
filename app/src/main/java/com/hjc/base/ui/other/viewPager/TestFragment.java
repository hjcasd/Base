package com.hjc.base.ui.other.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hjc.base.R;
import com.hjc.baselib.fragment.BaseFragment;

import butterknife.BindView;

public class TestFragment extends BaseFragment {

    @BindView(R.id.tv_test)
    TextView tvTest;

    public static TestFragment newInstance(String text) {
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null){
            String text = arguments.getString("text");
            tvTest.setText(text);
        }
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void onSingleClick(View v) {

    }
}
