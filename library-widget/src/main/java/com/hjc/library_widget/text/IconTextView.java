package com.hjc.library_widget.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.hjc.library_widget.R;

/**
 * @Author: HJC
 * @Date: 2021/8/26 10:47
 * @Description: 带图片的TextView(可修改图片大小)
 */
public class IconTextView extends AppCompatTextView {

    private final int leftDrawableWidth;
    private final int leftDrawableHeight;
    private final int rightDrawableWidth;
    private final int rightDrawableHeight;
    private final int topDrawableWidth;
    private final int topDrawableHeight;
    private final int bottomDrawableWidth;
    private final int bottomDrawableHeight;

    private final int DRAWABLE_LEFT = 0;
    private final int DRAWABLE_TOP = 1;
    private final int DRAWABLE_RIGHT = 2;
    private final int DRAWABLE_BOTTOM = 3;

    public IconTextView(Context context) {
        this(context, null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconTextView, defStyleAttr, 0);

        leftDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.IconTextView_leftDrawableHeight, -1);
        leftDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.IconTextView_leftDrawableWidth, -1);

        rightDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.IconTextView_rightDrawableHeight, -1);
        rightDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.IconTextView_rightDrawableWidth, -1);

        topDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.IconTextView_topDrawableHeight, -1);
        topDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.IconTextView_topDrawableWidth, -1);

        bottomDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.IconTextView_bottomDrawableHeight, -1);
        bottomDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.IconTextView_bottomDrawableWidth, -1);

        typedArray.recycle();

        Drawable[] drawables = getCompoundDrawables();
        for (int i = 0; i < drawables.length; i++) {
            setDrawableSize(drawables[i], i);
        }
        //放置图片
        setCompoundDrawables(drawables[DRAWABLE_LEFT], drawables[DRAWABLE_TOP], drawables[DRAWABLE_RIGHT], drawables[DRAWABLE_BOTTOM]);
    }

    /**
     * 设置图片的高度和宽度
     *
     * @param drawable 图片
     * @param index    位置(左上右下)
     */
    private void setDrawableSize(Drawable drawable, int index) {
        if (drawable == null) {
            return;
        }

        int width = 0, height = 0;
        switch (index) {
            case DRAWABLE_LEFT:
                width = leftDrawableWidth;
                height = leftDrawableHeight;
                break;

            case DRAWABLE_TOP:
                width = topDrawableWidth;
                height = topDrawableHeight;
                break;

            case DRAWABLE_RIGHT:
                width = rightDrawableWidth;
                height = rightDrawableHeight;
                break;

            case DRAWABLE_BOTTOM:
                width = bottomDrawableWidth;
                height = bottomDrawableHeight;
                break;

            default:
                break;
        }

        //如果没有设置图片的高度和宽度具使用默认的图片高度和宽度
        if (width < 0) {
            width = drawable.getIntrinsicWidth();
        }

        if (height < 0) {
            height = drawable.getIntrinsicHeight();
        }

        drawable.setBounds(0, 0, width, height);
    }

}