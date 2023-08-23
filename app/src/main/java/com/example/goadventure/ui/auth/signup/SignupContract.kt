package com.example.goadventure.ui.auth.signup

import android.net.Uri
import com.example.goadventure.base.BasePresenter
import com.example.goadventure.base.BaseView
import com.example.goadventure.model.request.RegisterRequest
import com.example.goadventure.model.response.login.LoginResponse

interface SignupContract {
    interface  View: BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view: android.view.View)
//        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter : SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
//        fun submitPhotoRegister(filePath: Uri, view: android.view.View)
    }
}