package com.hjc.module_other.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.BarUtils
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_common.global.EventCode
import com.hjc.module_other.R
import com.hjc.module_other.adapter.CabinAdapter
import com.hjc.module_other.adapter.ServiceAdapter
import com.hjc.module_other.http.entity.CabinBean

/**
 * @Author: HJC
 * @Date: 2021/9/27 10:55
 * @Description: 座位信息view
 */
class SeatInfoView @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(mContext, attrs, defStyleAttr) {

    private lateinit var llArrow: LinearLayout
    private lateinit var rvCabin: RecyclerView
    private lateinit var rvService: RecyclerView

    private lateinit var mCabinAdapter: CabinAdapter
    private lateinit var mServiceAdapter: ServiceAdapter

    init {
        initView()
        initData()
        addListener()
    }

    private fun initView() {
        LayoutInflater.from(mContext).inflate(R.layout.other_layout_seat_info, this)
        llArrow = findViewById(R.id.ll_arrow)
        rvCabin = findViewById(R.id.rv_cabin)
        rvService = findViewById(R.id.rv_service)

        val statusBarHeight = BarUtils.getStatusBarHeight()
        if (statusBarHeight > 80) {
            val layoutParams = rvCabin.layoutParams as LayoutParams
            layoutParams.rightMargin = BarUtils.getStatusBarHeight()
            rvCabin.layoutParams = layoutParams
        }

        val manager1 = GridLayoutManager(mContext, 4)
        manager1.orientation = GridLayoutManager.HORIZONTAL
        rvCabin.layoutManager = manager1

        val manager2 = GridLayoutManager(mContext, 5)
        manager2.orientation = GridLayoutManager.VERTICAL
        rvService.layoutManager = manager2
    }

    private fun initData() {
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
        rvCabin.adapter = mCabinAdapter

        val serviceList = mutableListOf("餐食", "Wi-Fi", "USB", "插座", "点播")
        mServiceAdapter = ServiceAdapter(serviceList)
        rvService.adapter = mServiceAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addListener() {
        llArrow.setOnClickListener {
            EventManager.sendEvent(MessageEvent(EventCode.HIDE_RIGHT_PANEL, 1))
        }

        mCabinAdapter.setOnItemClickListener { _, _, position ->
            val list = mCabinAdapter.data
            for ((index, item) in list.withIndex()) {
                item.isSelected = index == position
            }
            mCabinAdapter.notifyDataSetChanged()
        }
    }

}