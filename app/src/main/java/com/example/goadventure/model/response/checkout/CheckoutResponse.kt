package com.example.goadventure.model.response.checkout


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckoutResponse(
    @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("id_produk")
    val idProduk: Int,
    @Expose
    @SerializedName("id_user")
    val idUser: Int,
    @Expose
    @SerializedName("produk")
    val produk: Produk,
    @Expose
    @SerializedName("status")
    val status: String,
    @Expose
    @SerializedName("total")
    val total: Int,
    @Expose
    @SerializedName("total_barang")
    val totalBarang: Int,
    @Expose
    @SerializedName("tgl_sewa")
    val tglSewa: String,
    @Expose
    @SerializedName("tgl_kembali")
    val tglKembali: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long,
    @Expose
    @SerializedName("user")
    val user: User
)