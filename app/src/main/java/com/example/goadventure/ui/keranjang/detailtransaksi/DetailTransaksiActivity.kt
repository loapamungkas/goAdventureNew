package com.example.goadventure.ui.keranjang.detailtransaksi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.navigation.Navigation
import com.example.goadventure.R
import com.example.goadventure.model.response.transaksi.Data
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailTransaksiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi)

        var data = intent?.getParcelableExtra<Data>("data")

        var bundle = Bundle()
        bundle.putParcelable("data" , data as Parcelable)
        val navController = Navigation.findNavController(findViewById(R.id.nav_detail_transaction))
        navController.setGraph(navController.graph , bundle)

    }

    fun toolbarPayment(){
        toolbar.visibility = View.VISIBLE
        toolbar.title = "Detail Transaksi"
        toolbar.subtitle = "Pesanan Anda!"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener{ onBackPressed() }
    }
}