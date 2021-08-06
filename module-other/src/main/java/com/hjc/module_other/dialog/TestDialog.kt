package com.hjc.module_other.dialog

import android.os.Bundle
import android.view.View
import com.hjc.library_base.dialog.BaseBottomSheetDialog
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherDialogTestBinding

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:30
 * @Description: 测试dialog
 */
class TestDialog : BaseBottomSheetDialog<OtherDialogTestBinding, CommonViewModel>() {

    companion object {
        fun newInstance(): TestDialog {
            return TestDialog()
        }
    }

    override fun getStyleRes(): Int {
        return R.style.Base_Dialog_Sheet
    }

    override fun getLayoutId(): Int {
        return R.layout.other_dialog_test
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {

    }

}