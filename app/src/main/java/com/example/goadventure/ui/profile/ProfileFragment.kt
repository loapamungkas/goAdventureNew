package com.example.goadventure.ui.profile

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goadventure.GoAdventure
import com.example.goadventure.R
import com.example.goadventure.model.response.login.User
import com.example.goadventure.model.response.logout.LogoutResponse
import com.example.goadventure.model.response.updateprofile.UpdateProfileResponse
import com.example.goadventure.ui.MainActivity
import com.example.goadventure.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(),ProfileContract.View, View.OnClickListener {

    private lateinit var sectionPagerAdapter: SectionPagerAdapter
    private var profilePhotoPath: Uri? = null
    private var progressBar:Dialog?=null
    private lateinit var presenterProfile: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        val sectionPagerAdapter = SectionPagerAdapter(
//            childFragmentManager
//        )
//        viewPagerProfile.adapter = sectionPagerAdapter
//        tabLayoutProfile.setupWithViewPager(viewPagerProfile)
//
//        var user = GoAdventure.getApp().getUser()
//        var userResponse = Gson().fromJson(user, User::class.java)
//
//        tvNamaProfile.setText(userResponse.nama)
//        tvEmail.setText(userResponse.email)
//
//        if (!userResponse.profile_photo_url.isNullOrEmpty()) {
//            Glide.with(requireActivity())
//                .load(userResponse.profile_photo_url)
//                .into(ivProfil)
//        }
//
////        Glide.with(requireActivity())
////            .load(userResponse.profile_photo_url) // image url
////            .placeholder(R.drawable.ic_picture_empty)
////            .error(R.drawable.ic_picture_empty)
////            .centerCrop()
////            .into(ivProfil);
//
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        var user = GoAdventure.getApp().getUser()
//        var userResponse = Gson().fromJson(user, User::class.java)
//
//        tvNamaProfile.setText(userResponse.nama)
//        tvEmail.setText(userResponse.email)

        presenterProfile = ProfilePresenter(this)

        showPagerAdapterProfile()
        showProfileUser()
        initViewLoading()
        ivProfil.setOnClickListener(this)
        btnLogout.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){

            profilePhotoPath = data?.data

            Glide.with(this)
                .load(profilePhotoPath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfil)

            profilePhotoPath?.let { presenterProfile.submitPhoto(it) }

        }else if(requestCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(activity , ImagePicker.getError(data) , Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity , "Task Canceled" , Toast.LENGTH_SHORT).show()
        }
    }

    fun showPagerAdapterProfile(){
        sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        viewPagerProfile.adapter = sectionPagerAdapter
        tabLayoutProfile.setupWithViewPager(viewPagerProfile)
    }

    fun editPhotoProfile(){
        ImagePicker.with(this)
//            .cameraOnly()
            .start()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.ivProfil -> {
                editPhotoProfile()
            }
            R.id.btnLogout -> {
                actionLogout(view)
            }
        }
    }

    private fun showProfileUser(){

//        try {
//            var user = GoAdventure.getApp().getUser()
//            var userResponse = Gson().fromJson(user, User::class.java)
//            if(!userResponse.profile_photo_path.isNullOrEmpty()) {
//                Glide.with(requireActivity())
//                    .load(userResponse.profile_photo_url)
//                    .apply(RequestOptions().override(110, 110).circleCrop())
//                    .into(ivProfile)
//            }
//
//            tvNamaProfile.text = userResponse.nama
//            tvEmail.text = userResponse.email
//        } catch (e: NullPointerException) {
//            Toast.makeText(activity , "KONTOLLL" , Toast.LENGTH_SHORT).show()
//        }

        val user = GoAdventure.getApp().getUser()

        if (user != null) {
            val userResponse = Gson().fromJson(user, User::class.java)
            if (!userResponse.profile_photo_path.isNullOrEmpty()) {
                Glide.with(requireActivity())
                    .load(userResponse.profile_photo_url) // Ubah ke profile_photo_path
                    .apply(RequestOptions().override(250, 250).circleCrop())
                    .into(ivProfil)
            } else {
                Glide.with(requireActivity())
                    .load(R.drawable.ic_picture_empty) // Ubah ke profile_photo_path
                    .apply(RequestOptions().override(250, 250).circleCrop())
                    .into(ivProfil)
            }

            tvNamaProfile.text = userResponse.nama
            tvEmail.text = userResponse.email
        } else {
            Toast.makeText(activity , "RAKETEMU AKUN E" , Toast.LENGTH_LONG).show()
        }

//        var user = GoAdventure.getApp().getUser()
//        var userResponse = Gson().fromJson(user, User::class.java)
//
//        if (!userResponse.profile_photo_path.isNullOrEmpty()) {
//            Glide.with(requireActivity())
//                .load("http://192.168.100.11/go_adventure_backend/public/storage/"+userResponse.profile_photo_path)
//                .into(ivProfile)
//        }



    }

    override fun onUpdatePhotoSuccess(updateProfileResponse: UpdateProfileResponse) {
        val gson = Gson()
        val users = gson.toJson(updateProfileResponse.user)
        GoAdventure.getApp().setUser(users)


        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onUpdatePhotoFailed(message: String) {
//        Toast.makeText(activity,"Failed update Photo" , Toast.LENGTH_SHORT).show()
        Toast.makeText(activity,message , Toast.LENGTH_SHORT).show()
    }

    private fun actionLogout(view:View){
        presenterProfile.submitLogout(view)
    }

    private fun initViewLoading(){
        progressBar = Dialog(requireContext())
        val progressBarLayout = layoutInflater.inflate(R.layout.dialog_loader , null)

        progressBar?.let {
            it.setContentView(progressBarLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onLogoutSuccess(logoutResponse: LogoutResponse, view: View) {
        GoAdventure.getApp().setUser("")
        GoAdventure.getApp().setToken("")

        if(GoAdventure.getApp().getToken().isNullOrEmpty()){
            var intent = Intent(activity , AuthActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun onLogoutFailed(mesage: String) {
        GoAdventure.getApp().setUser("")
        GoAdventure.getApp().setToken("")

        if(GoAdventure.getApp().getToken().isNullOrEmpty()){
            var intent = Intent(activity , AuthActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun showLoading() {
        progressBar?.show()
    }

    override fun dismissLoading() {
        progressBar?.dismiss()
    }

}