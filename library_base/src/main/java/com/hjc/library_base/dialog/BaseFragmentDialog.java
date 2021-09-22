package com.hjc.library_base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.library_base.utils.ClickUtils;
import com.hjc.library_base.viewmodel.BaseViewModel;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:55
 * @Description: DialogFragment基类
 */
public abstract class BaseFragmentDialog<VDB extends ViewDataBinding, VM extends BaseViewModel> extends DialogFragment implements View.OnClickListener {

    protected Context mContext;

    // ViewDataBinding
    protected VDB mBindingView;

    // ViewModel
    protected VM mViewModel;

    //位置
    private int mGravity = Gravity.CENTER;

    //进入退出动画
    private int mAnimStyle = 0;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getStyleRes());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBindingView = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mBindingView.setLifecycleOwner(this);
        return mBindingView.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initView();
        initData(savedInstanceState);
        addListeners();
    }

    private void initViewModel() {
        if (mViewModel == null) {
            mViewModel = createViewModel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();

                params.gravity = mGravity;
                params.width = getWidth();
                params.height = getHeight();

                //设置dialog动画
                if (mAnimStyle != 0) {
                    window.setWindowAnimations(mAnimStyle);
                }
                window.setAttributes(params);
            }
        }
    }

    /**
     * 获取Dialog的Theme
     */
    @StyleRes
    public abstract int getStyleRes();

    /**
     * 获取布局的ID
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * 获取viewModel
     */
    public abstract VM createViewModel();

    /**
     * 初始化View
     */
    public void initView() {

    }

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
    public void onClick(View v) {
        //避免快速点击
        if (ClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!");
            return;
        }
        onSingleClick(v);
    }

    /**
     * 设置宽度为屏幕宽度的0.8
     */
    protected int getWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        return (int) (width * 0.8);
    }

    /**
     * 设置高度wrap
     */
    protected int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 设置Dialog位置
     */
    public BaseFragmentDialog<?,?> setGravity(int gravity) {
        this.mGravity = gravity;
        return this;
    }

    /**
     * 设置动画类型
     */
    public BaseFragmentDialog<?,?> setAnimStyle(@StyleRes int animStyle) {
        this.mAnimStyle = animStyle;
        return this;
    }

    /**
     * 显示Fragment
     */
    public void showDialog(FragmentManager fm) {
        show(fm, "DialogFragment");
    }

}
