package com.example.goadventure.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.navigation.Navigation
import com.example.goadventure.R
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"))

        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detailHostFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
//            navController.setGraph(navController.graph, bundle)
            navController.setGraph(R.navigation.nav_detail, bundle)
        }
    }

    fun toolbarDetail(){
        toolbar.visibility = View.GONE
    }

    fun toolbarPayment(){
        toolbar.visibility = View.VISIBLE
        toolbar.title = "Keranjang"
        toolbar.subtitle = "Pesan sekarang juga!"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener{ onBackPressed() }
    }
//

}