package com.example.goadventure.model.response

import com.example.goadventure.model.response.login.LoginResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wrapper<T>(
    @Expose
    @SerializedName("data")
    val data: T? = null,
    @Expose
    @SerializedName("meta")
    val meta: Meta ? = null
)