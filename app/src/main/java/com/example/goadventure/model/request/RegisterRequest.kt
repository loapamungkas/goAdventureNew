package com.example.goadventure.model.request

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class RegisterRequest(
    @Expose
    @SerializedName("nama")
    var nama: String,
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("password")
    var password: String,
    @Expose
    @SerializedName("password_confirmation")
    var password_confirmation: String,
    @Expose
    @SerializedName("alamat")
    var alamat: String,
    @Expose
    @SerializedName("no_telepon")
    var no_telepon: String,
    @Expose
    @SerializedName("filePath")
    var filePath: Uri ?= null,
) : Parcelable