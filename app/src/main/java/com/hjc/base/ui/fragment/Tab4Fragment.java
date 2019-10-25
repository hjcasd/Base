package com.hjc.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.AppUtils;
import com.hjc.base.R;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.http.RetrofitClient2;
import com.hjc.base.http.helper.RxHelper;
import com.hjc.base.http.observer.BaseProgressObserver;
import com.hjc.base.model.request.UpdateReq;
import com.hjc.base.model.response.VersionResp;
import com.hjc.base.ui.other.update.UpdateDialog;
import com.hjc.base.utils.ApkUtils;
import com.hjc.base.utils.SchemeUtils;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:45
 * @Description: 其他框架使用及常用效果实现
 */
public class Tab4Fragment extends BaseImmersionFragment {
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    @BindView(R.id.btn_drawer)
    Button btnDrawer;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_touch)
    Button btnTouch;
    @BindView(R.id.btn_view)
    Button btnView;


    public static Tab4Fragment newInstance() {
        return new Tab4Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab4;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnDialog.setOnClickListener(this);
        btnDrawer.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnTouch.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                SchemeUtils.jump(RoutePath.URL_DIALOG);
                break;

            case R.id.btn_drawer:
                SchemeUtils.jump(RoutePath.URL_DRAWER);
                break;

            case R.id.btn_update:
                checkVersion();
                break;

            case R.id.btn_touch:
                SchemeUtils.jump(RoutePath.URL_TOUCH);
                break;

            case R.id.btn_view:
                SchemeUtils.jump(RoutePath.URL_VIEW);
                break;

            default:
                break;
        }
    }

    private void checkVersion() {
        UpdateReq request = new UpdateReq();
        request.setAppType("1");
        RetrofitClient2.getInstance().getAPI()
                .checkVersion(request)
                .compose(RxHelper.bind(this))
                .subscribe(new BaseProgressObserver<VersionResp>(getChildFragmentManager()) {
                    @Override
                    public void onSuccess(VersionResp result) {
                        String newVersion = result.getNewVersion();
                        String lowVersion = result.getLowVersion();

                        String currentVersionName = AppUtils.getAppVersionName();
                        int flag1 = ApkUtils.compareVersion(currentVersionName, lowVersion);
                        //强制更新
                        if (flag1 == -1) {
                            showUpdateDialog(result, true);
                            return;
                        }

                        int flag2 = ApkUtils.compareVersion(currentVersionName, newVersion);
                        //需要更新
                        if (flag2 == -1) {
                            showUpdateDialog(result, false);
                        }
                    }
                });
    }

    /**
     * 显示更新dialog
     */
    private void showUpdateDialog(VersionResp result, boolean isForceUpdate) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("version", result);
        bundle.putBoolean("isForceUpdate", isForceUpdate);
        UpdateDialog.newInstance(bundle)
                .setAnimStyle(R.style.ActionSheetDialogAnimation)
                .showDialog(getChildFragmentManager());
    }

}
