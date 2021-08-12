package com.hjc.library_widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.library_widget.R;

import java.util.ArrayList;

/**
 * @Author: HJC
 * @Date: 2021/7/25 16:30
 * @Description: 自定义录音声音波形View
 */
public class WaveView extends View {
    private Paint mWavePaint;
    private Paint baseLinePaint;

    private final ArrayList<Short> dataList = new ArrayList<>();
    private short max = 300;
    private float mWidth;
    private float mHeight;
    private float space = 1f;
    private int mWaveColor = Color.BLACK;
    private int mBaseLineColor = Color.BLACK;
    private float waveStrokeWidth = 4f;
    private int invalidateTime = 20;
    private long drawTime;
    private boolean isMaxConstant = false;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WaveView, defStyle, 0);
        mWaveColor = a.getColor(R.styleable.WaveView_waveColor, mWaveColor);
        mBaseLineColor = a.getColor(R.styleable.WaveView_baselineColor, mBaseLineColor);
        waveStrokeWidth = a.getDimension(R.styleable.WaveView_waveStokeWidth, waveStrokeWidth);
        max = (short) a.getInt(R.styleable.WaveView_maxValue, max);
        invalidateTime = a.getInt(R.styleable.WaveView_invalidateTime, invalidateTime);
        space = a.getDimension(R.styleable.WaveView_space, space);

        a.recycle();
        initPainters();
    }

    private void initPainters() {
        mWavePaint = new Paint();
        // 画笔为color
        mWavePaint.setColor(mWaveColor);
        // 设置画笔粗细
        mWavePaint.setStrokeWidth(waveStrokeWidth);
        mWavePaint.setAntiAlias(true);
        mWavePaint.setFilterBitmap(true);
        mWavePaint.setStrokeCap(Paint.Cap.ROUND);
        mWavePaint.setStyle(Paint.Style.FILL);

        baseLinePaint = new Paint();
        // 画笔为color
        baseLinePaint.setColor(mBaseLineColor);
        // 设置画笔粗细
        baseLinePaint.setStrokeWidth(1f);
        baseLinePaint.setAntiAlias(true);
        baseLinePaint.setFilterBitmap(true);
        baseLinePaint.setStyle(Paint.Style.FILL);
    }

    public short getMax() {
        return max;
    }

    public void setMax(short max) {
        this.max = max;
    }

    public float getSpace() {
        return space;
    }

    public void setSpace(float space) {
        this.space = space;
    }

    public int getWaveColor() {
        return mWaveColor;
    }

    public void setWaveColor(int mWaveColor) {
        this.mWaveColor = mWaveColor;
        invalidateNow();
    }

    public int getBaseLineColor() {
        return mBaseLineColor;
    }

    public void setBaseLineColor(int mBaseLineColor) {
        this.mBaseLineColor = mBaseLineColor;
        invalidateNow();
    }

    public float getWaveStrokeWidth() {
        return waveStrokeWidth;
    }

    public void setWaveStrokeWidth(float waveStrokeWidth) {
        this.waveStrokeWidth = waveStrokeWidth;
        invalidateNow();
    }

    public int getInvalidateTime() {
        return invalidateTime;
    }

    public void setInvalidateTime(int invalidateTime) {
        this.invalidateTime = invalidateTime;
    }

    public boolean isMaxConstant() {
        return isMaxConstant;
    }

    public void setMaxConstant(boolean maxConstant) {
        isMaxConstant = maxConstant;
    }

    /**
     * 如果改变相应配置  需要刷新相应的paint设置
     */
    public void invalidateNow() {
        initPainters();
        invalidate();
    }

    public void addData(short data) {
        if (data < 0) {
            data = (short) -data;
        }
        if (data > max && !isMaxConstant) {
            max = data;
        }
        if (dataList.size() > mWidth / space) {
            synchronized (this) {
                dataList.remove(0);
                dataList.add(data);
            }
        } else {
            dataList.add(data);
        }
        if (System.currentTimeMillis() - drawTime > invalidateTime) {
            invalidate();
            drawTime = System.currentTimeMillis();
        }
    }

    public void clear() {
        dataList.clear();
        invalidateNow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(0, mHeight / 2);
        drawBaseLine(canvas);
        drawWave(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    private void drawWave(Canvas canvas) {
        for (int i = 0; i < dataList.size(); i++) {
            float x = (i) * space;
            float y = (float) dataList.get(i) / max * mHeight / 2;
            canvas.drawLine(x, -y, x, y, mWavePaint);
        }
    }

    private void drawBaseLine(Canvas canvas) {
        canvas.drawLine(0, 0, mWidth, 0, baseLinePaint);
    }
}
