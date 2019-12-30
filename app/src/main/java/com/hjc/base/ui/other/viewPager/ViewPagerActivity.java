package com.hjc.base.ui.other.viewPager;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;
import com.hjc.baselib.widget.viewpager.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RoutePath.URL_VIEW_PAGER)
public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.view_pager)
    VerticalViewPager viewPager;

    private NavigationAdapter mNavigationAdapter;

    private int oldPosition = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_pager;
    }

    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvList.setLayoutManager(manager);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("条目" + (i + 1));
        }

        mNavigationAdapter = new NavigationAdapter(list);
        rvList.setAdapter(mNavigationAdapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(TestFragment.newInstance("页面1"));
        fragments.add(TestFragment.newInstance("页面2"));
        fragments.add(TestFragment.newInstance("页面3"));
        fragments.add(TestFragment.newInstance("页面4"));
        fragments.add(TestFragment.newInstance("页面5"));

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        mNavigationAdapter.setSelection(0);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(view -> finish());

                //点击侧边栏菜单,滑动到指定位置
        mNavigationAdapter.setOnSelectListener(position -> viewPager.setCurrentItem(position, true));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (oldPosition != i) {
                    rvList.smoothScrollToPosition(i);
                    mNavigationAdapter.setSelection(i);
                    oldPosition = i;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onSingleClick(View v) {

    }
}
