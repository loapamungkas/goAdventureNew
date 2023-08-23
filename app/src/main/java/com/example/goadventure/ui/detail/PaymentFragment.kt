package com.example.goadventure.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.goadventure.GoAdventure
import com.example.goadventure.R
import com.example.goadventure.model.response.checkout.CheckoutResponse
import com.example.goadventure.model.response.home.Data
import com.example.goadventure.model.response.login.User
import com.example.goadventure.ui.MainActivity
import com.example.goadventure.utils.Helpers.formatHarga
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlinx.android.synthetic.main.item_home_horizontal.view.*
import java.text.SimpleDateFormat
import java.util.*

class PaymentFragment : Fragment(), PaymentContract.View {

    var progressDialog: Dialog? = null
    lateinit var presenter: PaymentPresenter
    private var totalHari : Int? = null
    var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarPayment()

        var data = arguments?.getParcelable<Data>("data")
        totalHari = arguments?.getInt("totalHari")!!
        val tglSewaTimestamp = arguments?.getLong("tglSewaTimestamp", 0)
        val tglKembaliTimestamp = arguments?.getLong("tglKembaliTimestamp", 0)
        initView(data, totalHari!!, tglSewaTimestamp!!, tglKembaliTimestamp!!)
        initView()
        presenter = PaymentPresenter(this)

//        btnCheckout.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_payment_success)
//        }
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initView(data: Data?, totalHari:Int, tglSewaTimestamp:Long, tglKembaliTimestamp:Long) {
        tvTitleDetail.text = data?.nama_produk
        val harga = data?.harga?.toString() ?: "0"
        tvHargaDetail.formatHarga(harga)
        tvHargaSubtotal.formatHarga(harga)
        tvTotalHari.text = "${totalHari} Hari"

        var tglSewaFormatted:String = ""
        var tglKembaliFormatted:String = ""

        if (tglSewaTimestamp != null && tglKembaliTimestamp != null) {
            val tglSewa = Date(tglSewaTimestamp)
            val tglKembali = Date(tglKembaliTimestamp)

            tglSewaFormatted = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(tglSewa)
            tglKembaliFormatted = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(tglKembali)

            tvTglSewa.text = "$tglSewaFormatted"
            tvTglKembali.text = "$tglKembaliFormatted"
        }else{
            tvTglSewa.text = "kosong"
            tvTglKembali.text = "kosong"
        }

        var user = GoAdventure.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        data?.harga?.let {
            val totalHarga = it.toInt()
            total = totalHarga * totalHari
            tvHargaTotal.formatHarga(total.toString())
        }

        tvNama.text = userResponse?.nama
        tvNoTelepon.text = userResponse?.no_telepon
        tvAlamat.text = userResponse?.alamat

        Glide.with(this)
            .load("http://192.168.100.11/go_adventure_backend/public/storage/"+data?.produk_photo_path)
            .into(ivPosterDetail)

//        btnCheckout.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_payment_success)
//        }

        btnCheckout.setOnClickListener {
            presenter.getCheckout(
                data?.id.toString(),
                userResponse?.id.toString(),
                totalHari.toString(),
                total.toString(),
                tglSewaFormatted,
                tglKembaliFormatted,
                it
            )
        }
    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {
        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
//        val home = Intent(activity, MainActivity::class.java)
//        startActivity(home)
//        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        Log.d("Erorr","$message")
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}
