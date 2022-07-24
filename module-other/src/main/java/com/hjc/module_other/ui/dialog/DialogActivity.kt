package com.hjc.module_other.ui.dialog

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.utils.ActivityHelper
import com.hjc.library_common.router.ServicePath
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.router.services.ILoginService
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherActivityDialogBinding
import com.hjc.module_other.dialog.TestDialog
import com.hjc.module_other.dialog.UpdateDialog

/**
 * @Author: HJC
 * @Date: 2021/7/7 22:44
 * @Description: 弹框演示
 */
@Route(path = RouteOtherPath.URL_DIALOG)
class DialogActivity : BaseActivity<OtherActivityDialogBinding, CommonViewModel>() {

    @JvmField
    @Autowired(name = ServicePath.LOGIN_SERVICE)
    var loginService: ILoginService? = null

    override fun getLayoutId(): Int {
        return R.layout.other_activity_dialog
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
        LogUtils.e("username: ${loginService?.getUsername()}")
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                AlertDialog.Builder(this)
                    .setTitle("确认退出App吗")
                    .setNegativeButton("取消") { dialog: DialogInterface?, _: Int -> dialog?.dismiss() }
                    .setPositiveButton("确定") { dialog: DialogInterface?, _: Int ->
                        dialog?.dismiss()
                        ActivityHelper.finishAllActivities()
                    }
                    .show()
            }

            R.id.btn2 -> UpdateDialog.newInstance().showDialog(supportFragmentManager)

            R.id.btn3 -> TestDialog.newInstance().showDialog(supportFragmentManager)

            else -> {
            }
        }
    }

}