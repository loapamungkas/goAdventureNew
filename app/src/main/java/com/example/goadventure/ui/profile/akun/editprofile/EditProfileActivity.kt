package com.example.goadventure.ui.profile.akun.editprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.goadventure.R

class EditProfileActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
//
//        val navController = Navigation.findNavController(findViewById(R.id.nav_host_edit_profile))
////        navController.navigate(R.id.nav_host_edit_profile)
//        navController.setGraph(R.navigation.nav_edit_profile, null)
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val navController = Navigation.findNavController(this,R.id.nav_host_edit_profile)
        navController.setGraph(navController.graph,null)

    }

}