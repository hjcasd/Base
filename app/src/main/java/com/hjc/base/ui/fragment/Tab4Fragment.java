package com.hjc.base.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.AppUtils;
import com.hjc.base.R;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.http.RetrofitClient2;
import com.hjc.base.http.helper.RxHelper;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.http.observer.BaseProgressObserver;
import com.hjc.base.model.request.UpdateReq;
import com.hjc.base.model.response.VersionResp;
import com.hjc.base.ui.other.DrawerCustomActivity;
import com.hjc.base.ui.other.DrawerNavigationActivity;
import com.hjc.base.ui.other.update.UpdateDialog;
import com.hjc.base.utils.ApkUtils;
import com.hjc.base.widget.dialog.LoadingDialog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:45
 * @Description: 其他框架使用及常用效果实现
 */
public class Tab4Fragment extends BaseImmersionFragment {
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    @BindView(R.id.btn_drawer_navigation)
    Button btnDrawerNavigation;
    @BindView(R.id.btn_drawer_custom)
    Button btnDrawerCustom;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    private LoadingDialog loadingDialog;


    public static Tab4Fragment newInstance() {
        return new Tab4Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab4;
    }

    @Override
    public void initView() {
        loadingDialog = LoadingDialog.newInstance();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnDialog.setOnClickListener(this);
        btnDrawerNavigation.setOnClickListener(this);
        btnDrawerCustom.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                loadingDialog.showDialog(getFragmentManager());
                Observable.timer(2, TimeUnit.SECONDS)
                        .compose(RxSchedulers.ioToMain())
                        .subscribe(aLong -> loadingDialog.dismiss());
                break;

            case R.id.btn_drawer_navigation:
                startActivity(new Intent(mContext, DrawerNavigationActivity.class));
                break;

            case R.id.btn_drawer_custom:
                startActivity(new Intent(mContext, DrawerCustomActivity.class));
                break;

            case R.id.btn_update:
                checkVersion();
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
