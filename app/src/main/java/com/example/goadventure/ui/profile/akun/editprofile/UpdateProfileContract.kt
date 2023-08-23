package com.example.goadventure.ui.profile.akun.editprofile

import android.net.Uri
import com.example.goadventure.base.BasePresenter
import com.example.goadventure.base.BaseView
import com.example.goadventure.model.request.UserRequest
import com.example.goadventure.model.response.logout.LogoutResponse
import com.example.goadventure.model.response.updateprofile.UpdateProfileResponse

interface UpdateProfileContract {
    interface View: BaseView {
        fun onUpdateProfileSuccess(updateProfileResponse: UpdateProfileResponse)
        fun onUpdateProfileFailed(message:String)
    }

    interface Presenter: UpdateProfileContract, BasePresenter {
        fun submitUpdateProfile(userRequest: UserRequest)
    }

}