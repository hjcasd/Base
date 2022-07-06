package com.hjc.library_widget.blur;

import android.content.Context;
import android.graphics.Bitmap;

public class EmptyBlurImpl implements BlurImpl {
    @Override
    public boolean prepare(Context context, Bitmap buffer, float radius) {
        return false;
    }

    @Override
    public void release() {

    }

    @Override
    public void blur(Bitmap input, Bitmap output) {

    }
}
