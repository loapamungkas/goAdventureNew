package com.example.goadventure.model.response.checkout


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Produk(
    @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @Expose
    @SerializedName("harga")
    val harga: Int,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("id_kategori")
    val idKategori: Int,
    @Expose
    @SerializedName("keterangan")
    val keterangan: String,
    @Expose
    @SerializedName("nama_produk")
    val namaProduk: String,
    @Expose
    @SerializedName("produk_photo_path")
    val produkPhotoPath: String,
    @Expose
    @SerializedName("stok")
    val stok: Int,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long
)