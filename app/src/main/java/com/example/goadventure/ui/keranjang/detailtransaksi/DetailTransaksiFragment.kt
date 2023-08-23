package com.example.goadventure.ui.keranjang.detailtransaksi

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goadventure.BuildConfig
import com.example.goadventure.R
import com.example.goadventure.model.response.transaksi.Data
import com.example.goadventure.ui.detail.DetailActivity
import com.example.goadventure.utils.Helpers.formatHarga
import kotlinx.android.synthetic.main.fragment_detail_transaksi.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DetailTransaksiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_transaksi, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataTransaction = requireActivity().intent.getParcelableExtra<Data>("data")!!

        showDetailTransactionView(dataTransaction)

        (activity as DetailTransaksiActivity).toolbarPayment()
//
//        icBackDetail.setOnClickListener {
//            val home = Intent(activity, MainActivity::class.java)
//            startActivity(home)
//        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDetailTransactionView(data : Data){
        var imageData = "${BuildConfig.BASE_URL}storage/${data.produk.produk_photo_path}"

        Glide.with(requireContext())
            .load(imageData)
            .apply(RequestOptions().override(60,60))
            .into(ivPosterFood)

        tvName.text = data.produk.nama_produk
        tvPrice.formatHarga(data.produk.harga.toString())
        tvTotalItem.text = "${data.total_barang} Hari"
        tvNameDetail.text = data.produk.nama_produk


        if (data.createdAt != null) {
            val tglTransaksi = Date(data.createdAt)
            val tglTransaksiFormatted = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(tglTransaksi)
            tvTglTransaksi.text = "$tglTransaksiFormatted"
        }else{
            tvTglTransaksi.text = "kosong"
        }
// String timestamp dalam format "yyyy-MM-dd HH:mm:ss"
        val stringTimestamp = data.tglSewa

        // Format tanggal yang diinginkan
        val targetFormat = "dd MMMM yyyy" // Contoh: "23 Agustus 2023"

        // Parse string timestamp menjadi objek LocalDateTime
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(stringTimestamp, formatter)

        // Format kembali objek LocalDateTime menjadi string dengan format yang diinginkan
        val targetFormatter = DateTimeFormatter.ofPattern(targetFormat)
        val formattedString = localDateTime.format(targetFormatter)

        if (data.tglSewa != null) {
            tvTglSewa.text = "$formattedString"
        }else{
            tvTglSewa.text = "kosong"
        }

        var totalHarga = data.produk.harga * data.total_barang
        tvPriceDetail.formatHarga(data.produk.harga.toString())

//        tvTotalPayment.formatHarga(data.total.toString())
        tvTotalPayment.formatHarga(totalHarga.toString())

//        user
        tvNameUser.text = data.user.nama
        tvAddresUser.text = data.user.alamat
        tvPhoneUser.text = data.user.noTelepon.toString()



        tvStatus.text = data.status

        if(data?.status.equals("CANCELLED" , true)){
            tvStatus.setTextColor(Color.parseColor("#dc143c"))

        }else if(data?.status.equals("SUCCESS" , true)){
            tvStatus.setTextColor(Color.parseColor("#1ABC9C"))
        }else if(data?.status.equals("DELIVERED" , true)){
            tvStatus.setTextColor(Color.parseColor("#1976D2"))
        }

        btnCheckFood.setOnClickListener {

            var dataDetail = com.example.goadventure.model.response.home.Data(
                data?.produk?.createdAt,
                data?.produk?.deletedAt,
                data?.produk?.harga,
                data?.produk?.id,
                data?.produk?.id_kategori,
                data?.produk?.keterangan,
                data?.produk?.nama_produk,
                data?.produk?.produk_photo_path,
                data?.produk?.stok,
                data?.produk?.type,
                data?.produk?.updatedAt,
            )

            val intent = Intent(activity , DetailActivity::class.java)
            intent.putExtra("data" , dataDetail)
            startActivity(intent)
        }

    }
}