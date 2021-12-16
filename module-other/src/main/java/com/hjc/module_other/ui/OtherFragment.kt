package com.hjc.module_other.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.path.RouteLoginPath
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherFragmentBinding

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: OtherFragment
 */
@Route(path = RouteOtherPath.URL_OTHER_FRAGMENT)
class OtherFragment : BaseFragment<OtherFragmentBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.other_fragment
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.other_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel?.show()
    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> RouteManager.jump(RouteOtherPath.URL_DIALOG)

            R.id.btn2 -> RouteManager.jumpWithTransition(
                mContext,
                RouteLoginPath.URL_LOGIN_ACTIVITY,
                R.anim.common_login_open_enter,
                R.anim.common_login_open_exit
            )

            R.id.btn3 -> RouteManager.jump(RouteOtherPath.URL_AUDIO)

            R.id.btn4 -> RouteManager.jump(RouteOtherPath.URL_SCROLL)

            R.id.btn5 -> RouteManager.jump(RouteOtherPath.URL_RICH_MEDIA)

            else -> {

            }
        }
    }

}