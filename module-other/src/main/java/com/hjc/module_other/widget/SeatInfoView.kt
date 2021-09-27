package com.hjc.module_other.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
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

    private lateinit var llRootView: View
    private lateinit var ivHide: ImageView
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
        llRootView = LayoutInflater.from(mContext).inflate(R.layout.other_layout_seat_info, this)
        ivHide = findViewById(R.id.iv_hide)
        rvCabin = findViewById(R.id.rv_cabin)
        rvService = findViewById(R.id.rv_service)

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

    private fun addListener() {
        ivHide.setOnClickListener {
            EventManager.sendEvent(MessageEvent(EventCode.HIDE_SEAT_VIEW, null))
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