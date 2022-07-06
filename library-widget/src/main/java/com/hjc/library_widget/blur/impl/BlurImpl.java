package com.hjc.library_widget.blur.impl;

import android.content.Context;
import android.graphics.Bitmap;

public interface BlurImpl {

    boolean prepare(Context context, Bitmap buffer, float radius);

    void release();

    void blur(Bitmap input, Bitmap output);

}
