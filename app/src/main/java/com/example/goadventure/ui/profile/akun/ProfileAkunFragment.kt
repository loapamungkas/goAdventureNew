package com.example.goadventure.ui.profile.akun

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goadventure.R
import com.example.goadventure.model.dummy.ProfileMenu
import com.example.goadventure.ui.detail.DetailActivity
import com.example.goadventure.ui.profile.ProfileMenuAdapter
import com.example.goadventure.ui.profile.akun.editprofile.EditProfileActivity
import kotlinx.android.synthetic.main.fragment_profile_akun.*

class ProfileAkunFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback{

    private lateinit var profileMenuAdapter: ProfileMenuAdapter
    private lateinit var profileMenuList : ArrayList<ProfileMenu>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_akun, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummyMenu()
        showProfileMenuList()
    }

    fun initDummyMenu(){
        profileMenuList = ArrayList()
        profileMenuList.add(ProfileMenu("Edit Profile"))
        profileMenuList.add(ProfileMenu("Home Address"))
        profileMenuList.add(ProfileMenu("Security"))
        profileMenuList.add(ProfileMenu("Payment"))
    }

    fun showProfileMenuList(){
        profileMenuAdapter = ProfileMenuAdapter(profileMenuList , this)

        rcListProfile.setHasFixedSize(true)
        rcListProfile.layoutManager = LinearLayoutManager(activity)
        rcListProfile.adapter = profileMenuAdapter
    }

//    override fun onClickMenuProfile(profileMenu: ProfileMenu) {
//
//        if(profileMenu.title.equals("Edit Profile" , true)){
//            val intent = Intent(activity , EditProfileActivity::class.java)
//            startActivity(intent)
//        }else{
//            Toast.makeText(activity , "${profileMenu.title}" , Toast.LENGTH_SHORT).show()
//        }
//
//    }

//    override fun onCLick(view: View, profileMenu: ProfileMenu) {
//        if(profileMenu.title.equals("Edit Profile" , true)){
////            Toast.makeText(activity , "ASUASUASUAUSUA" , Toast.LENGTH_SHORT).show()
//
//            val detail = Intent(activity, EditProfileActivity::class.java)
//            startActivity(detail)
//        }else{
//            Toast.makeText(activity , "${profileMenu.title}" , Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onClickMenuProfile(profileMenu: ProfileMenu) {
        if(profileMenu.title.equals("Edit Profile" , true)){
            val detail = Intent(activity, EditProfileActivity::class.java)
            startActivity(detail)
//            Toast.makeText(activity , "${profileMenu.title}" , Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity , "${profileMenu.title}" , Toast.LENGTH_SHORT).show()
        }
    }


}