package com.hjc.base.ui

import android.Manifest
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.hjc.base.R
import com.hjc.base.constant.RoutePath
import com.hjc.base.databinding.ActivityMainBinding
import com.hjc.base.receiver.NetworkChangeReceiver
import com.hjc.base.ui.fragment.Tab1Fragment
import com.hjc.base.ui.fragment.Tab2Fragment
import com.hjc.base.ui.fragment.Tab3Fragment
import com.hjc.base.ui.fragment.Tab4Fragment
import com.hjc.baselib.activity.BaseFragmentActivity
import com.hjc.baselib.viewmodel.CommonViewModel
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.request.ExplainScope

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:26
 * @Description: 主界面
 */
@Route(path = RoutePath.URL_MAIN)
class MainActivity : BaseFragmentActivity<ActivityMainBinding, CommonViewModel>() {

    private lateinit var mTab1Fragment: Tab1Fragment
    private lateinit var mTab2Fragment: Tab2Fragment
    private lateinit var mTab3Fragment: Tab3Fragment
    private lateinit var mTab4Fragment: Tab4Fragment

    private lateinit var mNetWorkChangeReceiver: NetworkChangeReceiver

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        mTab1Fragment = Tab1Fragment.newInstance()
        mTab2Fragment = Tab2Fragment.newInstance()
        mTab3Fragment = Tab3Fragment.newInstance()
        mTab4Fragment = Tab4Fragment.newInstance()

        showFragment(mTab1Fragment)

        requestPermission()
        registerBroadcastReceiver()
    }

    /**
     * 申请权限
     */
    private fun requestPermission() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        PermissionX.init(this)
            .permissions(*permissions)
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
                    ToastUtils.showShort("申请权限成功: $grantedList")
                } else {
                    ToastUtils.showShort("权限申请失败: $deniedList")
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

    override fun addListeners() {
        mBindingView.bottomView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_tab1 -> showFragment(mTab1Fragment)
                R.id.item_tab2 -> showFragment(mTab2Fragment)
                R.id.item_tab3 -> showFragment(mTab3Fragment)
                R.id.item_tab4 -> showFragment(mTab4Fragment)
            }
            true
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