package com.example.goadventure.network

import com.example.goadventure.model.response.Wrapper
import com.example.goadventure.model.response.checkout.CheckoutResponse
import com.example.goadventure.model.response.home.HomeResponse
import com.example.goadventure.model.response.login.LoginResponse
import com.example.goadventure.model.response.logout.LogoutResponse
import com.example.goadventure.model.response.transaksi.TransactionResponse
import com.example.goadventure.model.response.updateprofile.UpdateProfileResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
        @Field("alamat") alamat: String,
        @Field("no_telepon") no_telepon: String
    ): Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(
        @Part profilePhotoPath: MultipartBody.Part,
    ): Observable<Wrapper<Any>>

    @GET("produk")
    fun home() : Observable<Wrapper<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("id_produk") id_produk: String,
        @Field("id_user") id_user: String,
        @Field("total_barang") total_barang: String,
        @Field("total") total: String,
        @Field("status") status: String,
        @Field("tgl_sewa") tgl_sewa: String,
        @Field("tgl_kembali") tgl_kembali: String
    ): Observable<Wrapper<CheckoutResponse>>


    @GET("penyewaan")
    fun transaction(): Observable<Wrapper<TransactionResponse>>


    @FormUrlEncoded
    @Headers("Accept: application/json")
    @PUT("user")
    fun updateProfile(
        @Field("nama") nama:String,
        @Field("no_telepon") no_telepon : String,
        @Field("alamat") alamat:String
    ) : Observable<Wrapper<UpdateProfileResponse>>

    @Multipart
    @Headers("Accept: application/json")
    @POST("user/photo")
    fun updatePhoto(
        @Part profile_photo_path : MultipartBody.Part
    ) : Observable<Wrapper<UpdateProfileResponse>>


    @Headers("Accept: application/json")
    @POST("logout")
    fun logout() : Observable<Wrapper<LogoutResponse>>
}