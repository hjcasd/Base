package com.hjc.library_common.adapter

import android.graphics.Color
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.library_common.R
import com.hjc.library_common.databinding.CommonItemShimmerBinding
import me.samlss.broccoli.Broccoli
import me.samlss.broccoli.BroccoliGradientDrawable
import me.samlss.broccoli.PlaceholderParameter
import java.util.*

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: ShimmerAdapter
 */
class ShimmerAdapter(data: MutableList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(
    R.layout.common_item_shimmer, data
) {

    private val mViewPlaceholderManager: HashMap<View, Broccoli> = HashMap()

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        val binding = DataBindingUtil.getBinding<CommonItemShimmerBinding>(holder.itemView)

        var broccoli = mViewPlaceholderManager[holder.itemView]
        if (broccoli == null) {
            broccoli = Broccoli()
            mViewPlaceholderManager[holder.itemView] = broccoli
        }

        val animation1 = ScaleAnimation(0.3f, 1f, 1f, 1f)
        animation1.duration = 600
        animation1.repeatMode = Animation.REVERSE
        animation1.repeatCount = Animation.INFINITE

        val animation2 = ScaleAnimation(0.4f, 1f, 1f, 1f)
        animation2.duration = 800
        animation2.repeatMode = Animation.REVERSE
        animation2.repeatCount = Animation.INFINITE

        broccoli.removeAllPlaceholders()
        broccoli.addPlaceholders(
            PlaceholderParameter.Builder()
                .setView(binding?.ivSquare)
                .setDrawable(
                    BroccoliGradientDrawable(
                        Color.parseColor("#DEDEDE"),
                        Color.parseColor("#CCCCCC"), 0f, 1000, LinearInterpolator()
                    )
                )
                .build(),
            PlaceholderParameter.Builder()
                .setView(binding?.tvLine1)
                .setAnimation(animation1)
                .build(),
            PlaceholderParameter.Builder()
                .setView(binding?.tvLine2)
                .setAnimation(animation2)
                .build(),
            PlaceholderParameter.Builder()
                .setView(binding?.tvSquare)
                .setDrawable(
                    BroccoliGradientDrawable(
                        Color.parseColor("#DEDEDE"),
                        Color.parseColor("#CCCCCC"), 0f, 1000, LinearInterpolator()
                    )
                )
                .build(),
        )

        broccoli.show()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        //防止使用BroccoliGradientDrawable时内存泄露
        for (broccoli in mViewPlaceholderManager.values) {
            broccoli.removeAllPlaceholders()
        }
        mViewPlaceholderManager.clear()
    }
}