package com.example.goadventure.ui.keranjang.pastorder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goadventure.BuildConfig
import com.example.goadventure.R
import com.example.goadventure.model.response.transaksi.Data
import com.example.goadventure.utils.Helpers.convertTimestampToDate
import com.example.goadventure.utils.Helpers.formatHarga
import kotlinx.android.synthetic.main.fragment_detail_transaksi.*
import kotlinx.android.synthetic.main.item_pastorder.view.*
import java.text.SimpleDateFormat
import java.util.*

class PastorderAdapter(
    private val listData : List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<PastorderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastorderAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastorder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    class ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.produk.nama_produk
                tvHarga.formatHarga(data.total.toString())
//                val timestamp = data.produk.createdAt // Timestamp dalam milidetik
//                Log.d("Timestamp", timestamp.toString())
//                tvDate.text = "Tanggal: " + data.produk.createdAt.toString()

                val timestamp: Long = data.createdAt!!

                val formattedDate = convertTimestampToDate(timestamp, "dd MMMM yyyy", Locale("id", "ID"))

                tvDate.text = "$formattedDate"

                tvCancelled.text = data.status

                val imageData = "${BuildConfig.BASE_URL}storage/${data.produk.produk_photo_path}"
                Glide.with(itemView.context)
                    .load(imageData)
                    .apply(RequestOptions().override(60,60))
                    .into(ivPoster)

//                Glide.with(context)
//                    .load(data.produk_photo_path)
//                    .into(ivPoster)

//                if (data.status.equals("CANCELLED", true)){
//                    tvCancelled.visibility = View.VISIBLE
//                }
//                Glide.with(context)
//                    .load("http://192.168.100.11/go_adventure_backend/public/storage/"+data.produk.produk_photo_path) // image url
//                    .placeholder(R.drawable.iv_sample_1)
//                    .error(R.drawable.iv_sample_1)
//                    .override(200, 200)
//                    .centerCrop()
//                    .into(ivPoster);

//                itemView.setOnClickListener {
//                    itemAdapterCallback.onCLick(it, data)
//                }
                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(data)
                }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(data:Data)
    }
}