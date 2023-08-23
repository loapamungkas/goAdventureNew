package com.example.goadventure.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.goadventure.GoAdventure
import com.example.goadventure.R
import com.example.goadventure.model.request.RegisterRequest
import com.example.goadventure.model.response.login.LoginResponse
import com.example.goadventure.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signup_address.*

class SignupAddressFragment : Fragment(), SignupContract.View, View.OnClickListener  {

    companion object{
        val DATA_USER = "DATA_USER"
    }

//    private lateinit var data: RegisterRequest
    private lateinit var user : RegisterRequest
    lateinit var presenter: SignupPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)

//        data = arguments?.getParcelable<RegisterRequest>("data")!!

//        initDummy()
//        initListener()

        btnSignUpNow.setOnClickListener(this)
        initView()

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnSignUpNow -> {
                actionRegisterAddress(view)
            }
        }
    }

    private fun actionRegisterAddress(view : View?){
        view?.let{
            var no_telepon = etNoTelepon.text.toString()
            var alamat = etAlamat.text.toString()

            if (no_telepon.isNullOrEmpty()) {
                etNoTelepon.error = "Silahkan masukkan phone number"
                etNoTelepon.requestFocus()
            } else if (alamat.isNullOrEmpty()) {
                etAlamat.error = "Silahkan masukkan address"
                etAlamat.requestFocus()
            }else{
                user = arguments?.getParcelable<RegisterRequest>(DATA_USER)!!

                user?.let {
                    it.no_telepon = no_telepon
                    it.alamat = alamat
                }

                Log.d("DATA_USER" , user.toString())
                presenter.submitRegister(user,it)

            }

        }

    }


//    private fun initListener() {
//        btnSignUpNow.setOnClickListener {
//
//            var no_telepon = etNoTelepon.text.toString()
//            var alamat = etAlamat.text.toString()
//
//            data.let {
//                it.no_telepon = no_telepon
//                it.alamat = alamat
//            }
//
//            if (no_telepon.isNullOrEmpty()) {
//                etNoTelepon.error = "Silahkan masukkan phone number"
//                etNoTelepon.requestFocus()
//            } else if (alamat.isNullOrEmpty()) {
//                etAlamat.error = "Silahkan masukkan address"
//                etAlamat.requestFocus()
//            } else {
//                presenter.submitRegister(data, it)
//                presenter.submitPhotoRegister(data.filePath!!, it)
//            }
//        }
//    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        GoAdventure.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val users = gson.toJson(loginResponse.user)
        GoAdventure.getApp().setUser(users)

//        if (data.filePath == null) {
//            Navigation.findNavController(view)
//                .navigate(R.id.action_signup_success, null)
//
//            (activity as AuthActivity).toolbarSignUpSuccess()
//        } else {
//            presenter.submitPhotoRegister(data.filePath!!, view)
//        }

        Navigation.findNavController(view)
            .navigate(R.id.fragmentSignUpSuccess , null)

        (activity as AuthActivity).toolbarSignUpSuccess();
    }

//    override fun onRegisterPhotoSuccess(view: View) {
//        Navigation.findNavController(view)
//            .navigate(R.id.action_signup_success, null)
//
//        (activity as AuthActivity).toolbarSignUpSuccess()
//    }

    override fun onRegisterFailed(message: String) {
//        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        Toast.makeText(context , "${message.toString()}" , Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}