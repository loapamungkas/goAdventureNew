package com.example.goadventure.model.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRequest(
    @Expose
    @SerializedName("nama")
    var nama : String? = null,
    @Expose
    @SerializedName("no_telepon")
    var no_telepon : String? = null,
    @Expose
    @SerializedName("alamat")
    var alamat : String? = null,

    ): Parcelable