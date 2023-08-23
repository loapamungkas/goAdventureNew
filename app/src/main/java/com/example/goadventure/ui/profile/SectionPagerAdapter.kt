package com.example.goadventure.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.goadventure.ui.home.popular.HomePopularFragment
import com.example.goadventure.ui.home.recomended.HomeRecomendedFragment
import com.example.goadventure.ui.home.terbaru.HomeNewFragment
import com.example.goadventure.ui.profile.akun.ProfileAkunFragment
import com.example.goadventure.ui.profile.goadventure.ProfileGoAdventureFragment

class SectionPagerAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Akun"
            1->"Go Adventure"
            else->""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position){
            0 -> {
                fragment = ProfileAkunFragment()
                return fragment
            }
            1 -> {
                fragment = ProfileGoAdventureFragment()
                return fragment
            }
            else -> {
                fragment = ProfileAkunFragment()
                return fragment
            }
        }
    }

}