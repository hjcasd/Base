package com.hjc.baselib.dialog

import android.os.Bundle
import android.view.View
import com.hjc.baselib.R
import com.hjc.baselib.databinding.DialogLoadingBinding
import com.hjc.baselib.viewmodel.CommonViewModel

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:28
 * @Description: 加载框
 */
class LoadingDialog : BaseFragmentDialog<DialogLoadingBinding, CommonViewModel>() {

    companion object {
        fun newInstance(): LoadingDialog {
            return LoadingDialog()
        }
    }

    override fun getStyleRes(): Int {
        return R.style.Dialog_Base
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_loading
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