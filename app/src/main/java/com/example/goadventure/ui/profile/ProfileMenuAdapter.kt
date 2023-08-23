package com.example.goadventure.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goadventure.R
import com.example.goadventure.model.dummy.ProfileMenu
import kotlinx.android.synthetic.main.item_menu_profile.view.*

class ProfileMenuAdapter(
    private val profileMenu : List<ProfileMenu>,
    private val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_menu_profile, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profileMenu.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(profileMenu[position], itemAdapterCallback)
    }

//    interface ItemAdapterCallback {
//        fun onCLick(view : View, profileMenu: ProfileMenu)
//    }

    class ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
        fun bind(profileMenu: ProfileMenu, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitleProfile.text = profileMenu.title

//                itemView.setOnClickListener {
//                    itemAdapterCallback.onCLick(it, profileMenu)
//                }

                itemView.setOnClickListener {
                    itemAdapterCallback.onClickMenuProfile(profileMenu)
                }
            }
        }
    }

    interface ItemAdapterCallback{
        fun onClickMenuProfile(profileMenu: ProfileMenu)
    }
}