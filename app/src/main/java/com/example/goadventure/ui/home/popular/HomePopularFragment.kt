package com.example.goadventure.ui.home.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goadventure.R
import com.example.goadventure.model.dummy.HomeVerticalModel
import com.example.goadventure.model.response.home.Data
import com.example.goadventure.ui.detail.DetailActivity
import com.example.goadventure.ui.home.terbaru.HomeNewAdapter
import kotlinx.android.synthetic.main.fragment_home_popular.*

class HomePopularFragment : Fragment(), HomeNewAdapter.ItemAdapterCallback  {

    private var popularList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        popularList = arguments?.getParcelableArrayList("data")

        val adapter = HomeNewAdapter(popularList!!,this)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcListVertical.layoutManager = layoutManager
        rcListVertical.adapter = adapter

    }

    override fun onCLick(view: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}
