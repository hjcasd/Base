package com.hjc.library_base.dialog

import android.os.Bundle
import android.view.View
import com.hjc.library_base.R
import com.hjc.library_base.databinding.BaseDialogLoadingBinding
import com.hjc.library_base.viewmodel.CommonViewModel

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:28
 * @Description: 加载框
 */
class LoadingDialog : BaseFragmentDialog<BaseDialogLoadingBinding, CommonViewModel>() {

    companion object {
        fun newInstance(): LoadingDialog {
            return LoadingDialog()
        }
    }

    override fun getStyleRes(): Int {
        return R.style.Base_Dialog
    }

    override fun getLayoutId(): Int {
        return R.layout.base_dialog_loading
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        //去掉遮盖层
        dialog?.let {
            val window = it.window
            window?.setDimAmount(0f)
        }
        isCancelable = true
    }

    override fun addListeners() {}

    override fun onSingleClick(v: View?) {}

}