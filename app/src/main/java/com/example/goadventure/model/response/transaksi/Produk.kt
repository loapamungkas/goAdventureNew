package com.example.goadventure.model.response.transaksi


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produk(
    @Expose
    @SerializedName("created_at")
    val createdAt: String?,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,
    @Expose
    @SerializedName("harga")
    val harga: Int,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("id_kategori")
    val id_kategori: Int,
    @Expose
    @SerializedName("keterangan")
    val keterangan: String,
    @Expose
    @SerializedName("nama_produk")
    val nama_produk: String,
    @Expose
    @SerializedName("produk_photo_path")
    val produk_photo_path: String,
    @Expose
    @SerializedName("stok")
    val stok: Int,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String?
): Parcelable