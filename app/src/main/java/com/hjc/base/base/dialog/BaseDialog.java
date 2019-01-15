package com.hjc.base.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.utils.FastClickUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:55
 * @Description: DialogFragment基类
 */
public abstract class BaseDialog extends DialogFragment implements View.OnClickListener {

    protected Context mContext;
    protected Activity mActivity;
    private Unbinder mBinder;

    private float widthScale = 0.8f;
    private int mGravity = Gravity.CENTER;  //位置
    private int mAnimStyle = 0; //进入退出动画
    private int mWidth = 0;
    private int mHeight = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getStyleRes());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mBinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(savedInstanceState);
        addListeners();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = mGravity;

            //设置dialog宽度
            if (mWidth == 0) {
                params.width = getWidth();
            } else {
                params.width = dp2px(mWidth);
            }

            //设置dialog高度
            if (mHeight == 0) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                params.height = dp2px(mHeight);
            }

            //设置dialog动画
            if (mAnimStyle != 0) {
                window.setWindowAnimations(mAnimStyle);
            }
            window.setAttributes(params);
        }
    }

    /**
     * 设置Dialog位置
     * @param gravity
     * @return
     */
    public BaseDialog setGravity(int gravity) {
        this.mGravity = gravity;
        return this;
    }

    /**
     * 设置宽高
     *
     * @param width
     * @param height
     * @return
     */
    public BaseDialog setSize(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        return this;
    }

    /**
     * 设置动画类型
     *
     * @param animStyle
     * @return
     */
    public BaseDialog setAnimStyle(@StyleRes int animStyle) {
        this.mAnimStyle = animStyle;
        return this;
    }

    /**
     * 显示Fragment
     *
     * @param fm
     */
    public void show(FragmentManager fm) {
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        Fragment fragment = fragmentManager.findFragmentByTag("dialogFragment");
//        //避免重复弹窗
//        if (fragment != null) {
//            ft.remove(fragment);
//        }
        show(fm, "dialogFragment");
//        fragmentManager.executePendingTransactions();
    }

    private int getWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        return (int) (width * widthScale);
    }

    private int dp2px(float dipValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinder.unbind();
    }

    /**
     * 获取Dialog的Theme
     */
    public abstract int getStyleRes();

    /**
     * 获取布局的ID
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据
     */
    public abstract void initData(Bundle savedInstanceState);

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
        if (FastClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!");
            return;
        }
        onSingleClick(view);
    }
}
