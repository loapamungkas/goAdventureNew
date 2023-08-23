package com.example.goadventure.ui.home

import com.example.goadventure.base.BasePresenter
import com.example.goadventure.base.BaseView
import com.example.goadventure.model.response.home.HomeResponse

interface HomeContract {
    interface  View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}