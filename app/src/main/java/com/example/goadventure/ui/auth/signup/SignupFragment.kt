package com.example.goadventure.ui.auth.signup

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.goadventure.R
import com.example.goadventure.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goadventure.model.request.RegisterRequest
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment(), View.OnClickListener {

    var filePath: Uri? = null
    private lateinit var registerRequest : RegisterRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        initListener()
        btnContinue.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnContinue -> {
                actionRegister(view)
            }
        }
    }

    fun actionRegister(view : View?){
        view?.let {
            var fullname = etFullname.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if (fullname.isNullOrEmpty()) {
                etFullname.error = "Silahkan masukkan fullname"
                etFullname.requestFocus()
            } else if (email.isNullOrEmpty()) {
                etEmail.error = "Silahkan masukkan email"
                etEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                etPassword.error = "Silahkan masukkan password"
                etPassword.requestFocus()
            } else {
                val user = RegisterRequest(
                    fullname,
                    email,
                    password,
                    password,
                    "", "",
                    filePath
                )

                Log.d("DATA_USER", user.toString())
                var bundle = Bundle()
                bundle.putParcelable(SignupAddressFragment.DATA_USER, user)
                Navigation.findNavController(it)
                    .navigate(R.id.fragmentSignUpAddress, bundle)
                (activity as AuthActivity).toolbarSignUpAddress();
            }
        }
    }

//    private fun initListener() {
//        ivProfil.setOnClickListener {
//            ImagePicker.with(this)
//                .galleryOnly()
//                .start()
//        }
//
//        btnContinue.setOnClickListener {
//            var fullname =etFullname.text.toString()
//            var email =etEmail.text.toString()
//            var password =etPassword.text.toString()
//
//            if (fullname.isNullOrEmpty()) {
//                etFullname.error = "Silahkan masukkan fullname"
//                etFullname.requestFocus()
//            } else if (email.isNullOrEmpty()) {
//                etEmail.error = "Silahkan masukkan email"
//                etEmail.requestFocus()
//            } else if (password.isNullOrEmpty()) {
//                etPassword.error = "Silahkan masukkan password"
//                etPassword.requestFocus()
//            } else {
//                var data = RegisterRequest(
//                    fullname, email, password, password, "", "", filePath
//                )
//
//                var bundle = Bundle()
//                bundle.putParcelable("data", data)
//
//                Navigation.findNavController(it)
//                    .navigate(R.id.action_signup_address, bundle)
//
//                (activity as AuthActivity).toolbarSignUpAddress()
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            filePath = data?.data
//
//            Glide.with(this)
//                .load(filePath)
//                .apply(RequestOptions.circleCropTransform())
//                .into(ivProfil)
//        } else if (resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_LONG).show()
//        }
//    }
}