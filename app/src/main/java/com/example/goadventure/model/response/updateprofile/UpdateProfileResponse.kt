package com.example.goadventure.model.response.updateprofile


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(
    @Expose
    @SerializedName("user")
    val user: User
)