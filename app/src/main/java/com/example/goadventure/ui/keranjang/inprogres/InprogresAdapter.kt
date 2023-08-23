package com.example.goadventure.ui.keranjang.inprogres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goadventure.BuildConfig
import com.example.goadventure.R
import com.example.goadventure.model.response.transaksi.Data
import com.example.goadventure.utils.Helpers
import com.example.goadventure.utils.Helpers.formatHarga
import kotlinx.android.synthetic.main.item_inprogres.view.*
import kotlinx.android.synthetic.main.item_inprogres.view.ivPoster
import kotlinx.android.synthetic.main.item_inprogres.view.tvDate
import java.util.*

class InprogresAdapter(
    private val listData : List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<InprogresAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InprogresAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_inprogres, parent, false)
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
                tvTitleInprogres.text = data.produk.nama_produk
                tvHargaInprogres.formatHarga(data.total.toString())
                tvStatus.text = data.status

                val timestamp: Long = data.createdAt!!.toLong()

                val formattedDate =
                    Helpers.convertTimestampToDate(timestamp, "dd MMMM yyyy", Locale("id", "ID"))

                tvDate.text = "$formattedDate"


                val imageData = "${BuildConfig.BASE_URL}storage/${data.produk.produk_photo_path}"

                Glide.with(itemView.context)
                    .load(imageData)
                    .apply(RequestOptions().override(60,60))
                    .into(ivPoster)

//                Glide.with(context)
//                    .load("http://192.168.100.11/go_adventure_backend/public/storage/"+data.produk.produk_photo_path) // image url
//                    .placeholder(R.drawable.iv_sample_1)
//                    .error(R.drawable.iv_sample_1)
//                    .override(200, 200)
//                    .centerCrop()
//                    .into(ivPoster);

//                itemView.setOnClickListener {
//                    itemAdapterCallback.onClick(it, data)
//                }
                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(data)
                }
            }
        }
    }

//    interface ItemAdapterCallback{
//        fun onClick(v: View, data: Data)
//    }

    interface ItemAdapterCallback{
        fun onClick(data: Data)
    }
}