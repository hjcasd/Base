package com.hjc.base.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.utils.FastClickUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:58
 * @Description: Fragment基类
 */
public abstract class BaseFragment extends RxFragment implements View.OnClickListener {
	/**
	 * Fragment对应的Activity(避免使用getActivity()导致空指针异常)
	 */
	protected Context mContext;

    private Unbinder mBinder;

    @Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mContext = context;
	}

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
        mBinder = ButterKnife.bind(this, view);
		return view;
	}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		ARouter.getInstance().inject(this);
        initView();
        initData(savedInstanceState);
        addListeners();
    }

    /**
	 * 获取布局的ID
	 */
	public abstract int getLayoutId();

    /**
     * 初始化View
     */
    public abstract void initView();

	/**
	 * 初始化数据
	 */
	public abstract void initData(@Nullable Bundle savedInstanceState);

	/**
	 * 设置监听器
	 */
	public abstract void addListeners();

	/**
	 * 设置点击事件
	 */
	public abstract void onSingleClick(View v);

	@Override
	public void onClick(View view) {
        //避免快速点击
        if (FastClickUtils.isFastClick()){
            ToastUtils.showShort("点的太快了,歇会呗!");
            return;
        }
		onSingleClick(view);
	}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinder != null){
            mBinder.unbind();
        }
    }
}
