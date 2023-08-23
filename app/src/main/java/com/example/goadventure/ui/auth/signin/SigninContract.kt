package com.example.goadventure.ui.auth.signin

import com.example.goadventure.base.BasePresenter
import com.example.goadventure.base.BaseView
import com.example.goadventure.model.response.login.LoginResponse

interface SigninContract {
    interface  View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }
}