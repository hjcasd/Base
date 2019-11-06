package com.hjc.base.ui.media.webview;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;
import com.hjc.webviewlib.FileReaderView;

import java.io.File;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/11/6 14:17
 * @Description: 文件加载(打开pdf, word等文件)
 */
@Route(path = RoutePath.URL_WEB_FILE_READER)
public class WebFileReaderActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.reader_view)
    FileReaderView readerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_file_reader;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test.pdf";
        readerView.show(filePath);
    }

    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(view -> {
            finish();
        });
    }

    @Override
    public void onSingleClick(View v) {

    }

    @Override
    public void onDestroy() {
        if (readerView != null) {
            readerView.stop();
        }
        super.onDestroy();
    }
}
