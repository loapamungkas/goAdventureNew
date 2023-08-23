package com.example.goadventure.ui.profile.goadventure

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goadventure.R
import com.example.goadventure.model.dummy.ProfileMenu
import com.example.goadventure.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_profile_go_adventure.*

class ProfileGoAdventureFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {

    private lateinit var profileMenuList : ArrayList<ProfileMenu>
    private lateinit var profileListAdapter : ProfileMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_go_adventure, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummyMenu()
        showProfileMenuList()
    }

    fun initDummyMenu() {
        profileMenuList = ArrayList()
        profileMenuList.add(ProfileMenu("Rate App"))
        profileMenuList.add(ProfileMenu("Help Center"))
        profileMenuList.add(ProfileMenu("Privacy & Policy"))
        profileMenuList.add(ProfileMenu("Term & Conditions"))
    }

    fun showProfileMenuList(){
        profileListAdapter = ProfileMenuAdapter(profileMenuList , this)

        rcListProfile.setHasFixedSize(true)
        rcListProfile.layoutManager = LinearLayoutManager(activity)
        rcListProfile.adapter = profileListAdapter
    }

//    override fun onCLick(view: View, profileMenu: ProfileMenu) {
//        Toast.makeText(activity , "${profileMenu.title}" , Toast.LENGTH_SHORT).show()
//
//    }

    override fun onClickMenuProfile(profileMenu: ProfileMenu) {
        Toast.makeText(activity , "${profileMenu.title}" , Toast.LENGTH_SHORT).show()

    }


}