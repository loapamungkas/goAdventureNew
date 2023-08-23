package com.example.goadventure.model.response.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("alamat")
    val alamat: String,
    @Expose
    @SerializedName("created_at")
    val created_at: Long,
    @Expose
    @SerializedName("current_team_id")
    val current_team_id: Any,
    @Expose
    @SerializedName("deleted_at")
    val deleted_at: Any,
    @Expose
    @SerializedName("email")
    val email: String?,
    @Expose
    @SerializedName("email_verified_at")
    val email_verified_at: Any,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("nama")
    val nama: String?,
    @Expose
    @SerializedName("no_telepon")
    val no_telepon: String?,
    @Expose
    @SerializedName("profile_photo_path")
    val profile_photo_path: String?,
    @Expose
    @SerializedName("profile_photo_url")
    val profile_photo_url: String?,
    @Expose
    @SerializedName("two_factor_confirmed_at")
    val two_factor_confirmed_at: Any,
    @Expose
    @SerializedName("updated_at")
    val updated_at: Long
)