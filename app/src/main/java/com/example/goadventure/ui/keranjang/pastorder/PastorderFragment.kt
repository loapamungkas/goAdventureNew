package com.example.goadventure.ui.keranjang.pastorder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goadventure.R
import com.example.goadventure.model.response.transaksi.Data
import com.example.goadventure.ui.keranjang.detailtransaksi.DetailTransaksiActivity
import kotlinx.android.synthetic.main.fragment_pastorder.*

class PastorderFragment : Fragment(), PastorderAdapter.ItemAdapterCallback{

    private var adapter: PastorderAdapter?=null
    var pastorderList:ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pastorder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pastorderList = arguments?.getParcelableArrayList("data")

        if (!pastorderList.isNullOrEmpty()) {
            adapter = PastorderAdapter(pastorderList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            rcListProfilePastorder.setHasFixedSize(true)
            rcListProfilePastorder.layoutManager = layoutManager
            rcListProfilePastorder.adapter = adapter

        }
    }

//    override fun onClick(data: Data) {
////        Toast.makeText(activity, "Coba Klik", Toast.LENGTH_LONG).show()
//        var intent = Intent(activity , DetailTransaksiActivity::class.java)
//        intent.putExtra("data" , data)
//        startActivity(intent)
//    }

    override fun onClick(data: Data) {
        var intent = Intent(activity , DetailTransaksiActivity::class.java)
        intent.putExtra("data" , data)
        startActivity(intent)
    }

}