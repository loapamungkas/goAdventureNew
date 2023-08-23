package com.example.goadventure.ui.keranjang

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.goadventure.model.response.transaksi.Data
import com.example.goadventure.ui.keranjang.inprogres.InprogresFragment
import com.example.goadventure.ui.keranjang.pastorder.PastorderFragment

class SectionPagerAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm) {

    var inprogresList:ArrayList<Data>? = ArrayList()
    var pastorderList:ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Dalam Progres"
            1->"History"
            else->""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = InprogresFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogresList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastorderFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastorderList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = InprogresFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogresList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(
        inprogresListParms: ArrayList<Data>?,
        pastorderListParms: ArrayList<Data>?,
    ) {
        inprogresList = inprogresListParms
        pastorderList = pastorderListParms
    }

}