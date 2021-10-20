package com.hjc.module_other.dialog

import android.annotation.SuppressLint
import android.content.DialogInterface
import com.hjc.library_base.dialog.BaseFragmentDialog
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import android.view.WindowManager
import android.widget.LinearLayout
import com.blankj.utilcode.util.BarUtils
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_common.global.EventCode
import com.hjc.module_other.adapter.CabinAdapter
import com.hjc.module_other.adapter.ServiceAdapter
import com.hjc.module_other.databinding.OtherDialogSeatInfoBinding
import com.hjc.module_other.http.entity.CabinBean

/**
 * @Author: HJC
 * @Date: 2021/9/26 14:03
 * @Description: 座位信息dialog
 */
class SeatInfoDialog : BaseFragmentDialog<OtherDialogSeatInfoBinding, CommonViewModel>() {

    private lateinit var mCabinAdapter: CabinAdapter
    private lateinit var mServiceAdapter: ServiceAdapter

    companion object {
        fun newInstance(): SeatInfoDialog {
            return SeatInfoDialog()
        }
    }

    override fun getStyleRes(): Int {
        return R.style.Base_Dialog_Right
    }

    override fun getLayoutId(): Int {
        return R.layout.other_dialog_seat_info
    }

    override fun getWidth(): Int {
        val dm: DisplayMetrics = resources.displayMetrics
        val width = dm.widthPixels
        return (width * 0.6).toInt()
    }

    override fun getHeight(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initView() {
        super.initView()
        val statusBarHeight = BarUtils.getStatusBarHeight()
        if (statusBarHeight > 80) {
            val layoutParams = mBindingView.rvCabin.layoutParams as LinearLayout.LayoutParams
            layoutParams.rightMargin = BarUtils.getStatusBarHeight()
            mBindingView.rvCabin.layoutParams = layoutParams
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val manager1 = GridLayoutManager(mContext, 4)
        manager1.orientation = GridLayoutManager.HORIZONTAL
        mBindingView.rvCabin.layoutManager = manager1

        val manager2 = GridLayoutManager(mContext, 5)
        manager2.orientation = GridLayoutManager.VERTICAL
        mBindingView.rvService.layoutManager = manager2
    }

    override fun initData(savedInstanceState: Bundle?) {
        dialog?.window?.setDimAmount(0f)

        val titleList = mutableListOf("豪华公务舱", "公务舱", "超级经济舱", "经济舱")
        val cabinList = mutableListOf<CabinBean>()
        for ((index, value) in titleList.withIndex()) {
            val cabinBean: CabinBean
            if (index == 0) {
                cabinBean = CabinBean(value, true)
            } else {
                cabinBean = CabinBean(value, false)
            }
            cabinList.add(cabinBean)
        }
        mCabinAdapter = CabinAdapter(cabinList)
        mBindingView.rvCabin.adapter = mCabinAdapter

        val serviceList = mutableListOf("餐食", "Wi-Fi", "USB", "插座", "点播")
        mServiceAdapter = ServiceAdapter(serviceList)
        mBindingView.rvService.adapter = mServiceAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun addListeners() {
        mBindingView.onClickListener = this

        mCabinAdapter.setOnItemClickListener { _, _, position ->
            val list = mCabinAdapter.data
            for ((index, item) in list.withIndex()) {
                item.isSelected = index == position
            }
            mCabinAdapter.notifyDataSetChanged()
        }
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.ll_arrow -> {
                dismiss()
            }

            else -> {
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        EventManager.sendEvent(MessageEvent(EventCode.HIDE_RIGHT_PANEL, null))
    }

}