package com.example.goadventure.model.response.transaksi


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @Expose
    @SerializedName("alamat")
    val alamat: String,
    @Expose
    @SerializedName("created_at")
    val createdAt: Long?,
    @Expose
    @SerializedName("current_team_id")
    val currentTeamId: String?,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("nama")
    val nama: String,
    @Expose
    @SerializedName("no_telepon")
    val noTelepon: String,
    @Expose
    @SerializedName("profile_photo_path")
    val profilePhotoPath: String,
    @Expose
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String,
    @Expose
    @SerializedName("two_factor_confirmed_at")
    val twoFactorConfirmedAt: String?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?
): Parcelable