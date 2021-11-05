package com.hjc.module_other.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
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
import com.hjc.module_other.utils.MediaViewUtils

/**
 * @Author: HJC
 * @Date: 2021/9/27 10:55
 * @Description: 座位信息view
 */
class CabinInfoView @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(mContext, attrs, defStyleAttr) {

    private lateinit var rlShowArrow: RelativeLayout
    private lateinit var rlHideArrow: RelativeLayout
    private lateinit var llInfo: LinearLayout
    private lateinit var rvCabin: RecyclerView
    private lateinit var rvService: RecyclerView

    private lateinit var mCabinAdapter: CabinAdapter
    private lateinit var mServiceAdapter: ServiceAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.other_layout_cabin_info, this)

        initView()
        initData()
        addListener()
    }

    private fun initView() {
        rlShowArrow = findViewById(R.id.rl_show_arrow)
        rlHideArrow = findViewById(R.id.rl_hide_arrow)
        llInfo = findViewById(R.id.ll_info)
        rvCabin = findViewById(R.id.rv_cabin)
        rvService = findViewById(R.id.rv_service)

        initRecyclerView()
    }

    private fun initRecyclerView() {
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
        rlShowArrow.setOnClickListener {
            show()
        }

        rlHideArrow.setOnClickListener {
            hide()
        }

        mCabinAdapter.setOnItemClickListener { _, _, position ->
            val list = mCabinAdapter.data
            for ((index, item) in list.withIndex()) {
                item.isSelected = index == position
            }
            mCabinAdapter.notifyDataSetChanged()
        }
    }

    /**
     * 显示View
     */
    fun show(type: Int = 0) {
        if (type == 0) {
            MediaViewUtils.showRightView(llInfo, rlShowArrow)
        } else {
            rlShowArrow.visibility = View.VISIBLE
            llInfo.visibility = View.INVISIBLE
        }
    }

    /**
     * 隐藏View
     */
    fun hide(type: Int = 0) {
        if (type == 0) {
            MediaViewUtils.hideRightView(llInfo, rlShowArrow)
        } else {
            rlShowArrow.visibility = View.GONE
            llInfo.visibility = View.INVISIBLE
        }
    }

}