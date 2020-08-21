package jp.co.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class CustomPagerAdapter(_fm: FragmentManager, private val fragmentList: List<Fragment>) :
    FragmentStatePagerAdapter(_fm) {

    val TAG = "CustomPagerAdapter"

    // 表示するフラグメントを制御する
    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "getItem")
        val bundle : Bundle = Bundle()
        bundle.putInt("pageNum", position)
        fragmentList[position].arguments = bundle
        return fragmentList[position]
    }

    // viewPagerにセットするコンテンツ(フラグメントリスト)のサイズ
    override fun getCount(): Int {
        return fragmentList.size
    }
}