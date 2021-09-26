package com.hjc.module_other.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @Author: HJC
 * @Date: 2021/9/26 16:36
 * @Description: ViewPager Adapter
 */
class MyFragmentPagerAdapter(fm: FragmentManager, behavior: Int, private val mFragments: List<Fragment>) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }
}