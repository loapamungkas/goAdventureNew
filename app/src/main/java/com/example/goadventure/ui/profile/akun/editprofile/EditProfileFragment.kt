package com.example.goadventure.ui.profile.akun.editprofile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goadventure.GoAdventure
import com.example.goadventure.R
import com.example.goadventure.model.dummy.ProfileMenu
import com.example.goadventure.model.dummy.ProfileMenuModel
import com.example.goadventure.model.request.UserRequest
import com.example.goadventure.model.response.login.User
import com.example.goadventure.model.response.updateprofile.UpdateProfileResponse
import com.example.goadventure.ui.MainActivity
import com.example.goadventure.ui.profile.ProfileMenuAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile_akun.*

class EditProfileFragment : Fragment(),UpdateProfileContract.View {

    private lateinit var userReqeust : UserRequest
    private var progressBar : Dialog?=null
    private lateinit var presenter : UpdateProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = UpdateProfilePresenter(this)

        initViewLoading()
        initViewUpdateProfile()
    }

    private fun initViewUpdateProfile(){
        val gson = Gson()
        val user = gson.fromJson(GoAdventure.getApp().getUser(), User::class.java)

        edtName.setText("${user.nama}")
        edtPhone.setText("${user.no_telepon}")
        edtAddress.setText("${user.alamat}")

        btnUpdateProfile.setOnClickListener {

            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            val address = edtAddress.text.toString()

            if(name.isNullOrEmpty()){
                edtName.error ="Masukan nama"
                edtName.requestFocus()
            }else if(phone.isNullOrEmpty()){
                edtPhone.error="Masukan No Telepon"
                edtPhone.requestFocus()
            }else if(address.isNullOrEmpty()){
                edtAddress.error="Masukan Alamat"
                edtAddress.requestFocus()
            }else {
                userReqeust = UserRequest(
                    name,
                    phone,
                    address)

                presenter.submitUpdateProfile(userReqeust)

            }
        }
    }

    fun initViewLoading(){
        progressBar = Dialog(requireContext())
        val progressBarLayout = layoutInflater.inflate(R.layout.dialog_loader , null)

        progressBar?.let {
            it.setContentView(progressBarLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

    }

    override fun onUpdateProfileSuccess(updateProfileResponse: UpdateProfileResponse) {
        val gson = Gson()
        val updateUsers = gson.toJson(updateProfileResponse.user)
        GoAdventure.getApp().setUser(updateUsers)

        val intent = Intent(activity,MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onUpdateProfileFailed(message: String) {
        Toast.makeText(activity,"Failed Update Profile" , Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBar?.show()
    }

    override fun dismissLoading() {
        progressBar?.dismiss()
    }
}