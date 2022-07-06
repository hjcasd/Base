package com.hjc.module_main.ui

import android.Manifest
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseFragmentActivity
import com.hjc.library_common.router.path.*
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_main.R
import com.hjc.module_main.databinding.MainActivityBinding
import com.hjc.module_main.receiver.NetworkChangeReceiver
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.request.ExplainScope


/**
 * @Author: HJC
 * @Date: 2021/1/9 15:26
 * @Description: 主界面
 */
@Route(path = RouteMainPath.URL_MAIN)
class MainActivity : BaseFragmentActivity<MainActivityBinding, CommonViewModel>() {

    private lateinit var mTab1Fragment: Fragment
    private lateinit var mTab2Fragment: Fragment
    private lateinit var mTab3Fragment: Fragment
    private lateinit var mTab4Fragment: Fragment

    private lateinit var mNetWorkChangeReceiver: NetworkChangeReceiver

    override fun getLayoutId(): Int {
        return R.layout.main_activity
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mTab1Fragment =
            ARouter.getInstance().build(RouteHomePath.URL_HOME_FRAGMENT).navigation() as Fragment?
                ?: TestFragment.getInstance()

        mTab2Fragment =
            ARouter.getInstance().build(RouteFramePath.URL_FRAME_FRAGMENT).navigation() as Fragment?
                ?: TestFragment.getInstance()

        mTab3Fragment =
            ARouter.getInstance().build(RouteSeniorPath.URL_SENIOR_FRAGMENT).navigation() as Fragment?
                ?: TestFragment.getInstance()

        mTab4Fragment =
            ARouter.getInstance().build(RouteOtherPath.URL_OTHER_FRAGMENT).navigation() as Fragment?
                ?: TestFragment.getInstance()

        if (savedInstanceState == null) {
            showFragment(mTab1Fragment)
        }

        requestPermission()
        registerBroadcastReceiver()
        getSmallestWidth()
    }

    /**
     * 申请权限
     */
    private fun requestPermission() {
        val permissions = arrayListOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        )
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
//            permissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
//        }
        PermissionX.init(this)
            .permissions(permissions)
            .onExplainRequestReason { scope: ExplainScope, deniedList: List<String?>? ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "请授予以下权限，以便程序继续运行",
                    "确定",
                    "取消"
                )
            }
            .request { allGranted: Boolean, grantedList: List<String?>?, deniedList: List<String?> ->
                if (allGranted) {
                    LogUtils.e("成功权限: $grantedList")
                    ToastUtils.showShort("申请权限成功")
                } else {
                    LogUtils.e("失败权限: $deniedList")
                    ToastUtils.showShort("权限申请失败")
                }
            }
    }

    /**
     * 动态注册广播接受者
     */
    private fun registerBroadcastReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        mNetWorkChangeReceiver = NetworkChangeReceiver()
        //注册广播
        registerReceiver(mNetWorkChangeReceiver, intentFilter)
    }

    /**
     * 获取屏幕最小宽度
     */
    private fun getSmallestWidth() {
        val heightPixels = ScreenUtils.getScreenHeight()
        val widthPixels = ScreenUtils.getScreenWidth()

        val density = resources.displayMetrics.density
        val heightDP = heightPixels / density
        val widthDP = widthPixels / density

        val smallestWidthDP: Float = if (widthDP < heightDP) {
            widthDP
        } else {
            heightDP
        }
        LogUtils.e("smallestWidth: $smallestWidthDP")
    }

    override fun addListeners() {
        mBindingView.bottomView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_tab1 -> showFragment(mTab1Fragment)
                R.id.item_tab2 -> showFragment(mTab2Fragment)
                R.id.item_tab3 -> showFragment(mTab3Fragment)
                R.id.item_tab4 -> showFragment(mTab4Fragment)
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onSingleClick(v: View?) {

    }

    override fun getFragmentContentId(): Int {
        return R.id.fl_content
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mNetWorkChangeReceiver)
    }

}