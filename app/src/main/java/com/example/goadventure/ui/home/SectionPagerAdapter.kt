package com.example.goadventure.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.goadventure.model.response.home.Data
import com.example.goadventure.ui.home.popular.HomePopularFragment
import com.example.goadventure.ui.home.recomended.HomeRecomendedFragment
import com.example.goadventure.ui.home.terbaru.HomeNewFragment

class SectionPagerAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm) {

    var terbaruList:ArrayList<Data>? = ArrayList()
    var popularList:ArrayList<Data>? = ArrayList()
    var recommendedList:ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Terbaru"
            1->"Populer"
            2->"Recomended"
            else->""
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position){
            0 -> {
                fragment = HomeNewFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", terbaruList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = HomePopularFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", popularList)
                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                fragment = HomeRecomendedFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", recommendedList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = HomeNewFragment()
                return fragment
            }
        }
    }

    fun setData(terbaruListParms: ArrayList<Data>?, popularListParms: ArrayList<Data>?, recommendedListParms: ArrayList<Data>?){
        terbaruList = terbaruListParms
        popularList = popularListParms
        recommendedList = recommendedListParms
    }

}