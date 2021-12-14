package com.hdd.pakwan.domain.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide

class PIAdapter(private val fragmentList: List<Fragment>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


       val productImage = ImageView(container.context)
        Glide.with(container.context).load(fragmentList[position]).into(productImage)
        val vp = container as ViewPager
        vp.addView(productImage, 0)
        return productImage
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

}

