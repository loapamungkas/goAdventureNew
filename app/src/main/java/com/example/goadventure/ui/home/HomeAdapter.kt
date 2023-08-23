package com.example.goadventure.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goadventure.GoAdventure
import com.example.goadventure.R
import com.example.goadventure.model.response.home.Data
import com.example.goadventure.model.response.login.User
import com.example.goadventure.utils.Helpers.formatHarga
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_home_horizontal.view.*
import kotlinx.android.synthetic.main.item_home_vertical.view.*

class HomeAdapter(
    private val listData : List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    interface ItemAdapterCallback {
        fun onCLick(view : View, data:Data)
    }

    class ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitleHorizontal.text = data.nama_produk
                tvHargaHorizontal.formatHarga(data.harga.toString())

//                Glide.with(context)
//                    .load(data.produk_photo_path)
//                    .into(ivPoster)

                Glide.with(context)
                    .load("http://192.168.100.11/go_adventure_backend/public/storage/"+data.produk_photo_path) // image url
                    .placeholder(R.drawable.iv_sample_1)
                    .error(R.drawable.iv_sample_1)
                    .override(200, 200)
                    .centerCrop()
                    .into(ivPoster);

                itemView.setOnClickListener {
                    itemAdapterCallback.onCLick(it, data)
                }
            }
        }
    }
}