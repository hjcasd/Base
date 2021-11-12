package com.hjc.module_other.ui.video.child

import android.os.Bundle
import android.view.View
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_common.utils.image.ImageManager
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherFragmentPictureBinding

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: 展示图片
 */
class PictureFragment : BaseFragment<OtherFragmentPictureBinding, CommonViewModel>() {

    companion object {
        fun newInstance(imageUrl: String): PictureFragment {
            val fragment = PictureFragment()
            val bundle = Bundle()
            bundle.putString("imageUrl", imageUrl)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int{
        return  R.layout.other_fragment_picture
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        arguments?.let {
            val imageUrl = it.getString("imageUrl", "")
            ImageManager.loadImage(mBindingView.ivCover, imageUrl)
        }
    }

    override fun addListeners() {

    }

    override fun onSingleClick(v: View?) {

    }

}