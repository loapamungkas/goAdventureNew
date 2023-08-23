package com.example.goadventure.ui.home.terbaru

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goadventure.R
import com.example.goadventure.model.response.home.Data
import com.example.goadventure.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home_new.*

class HomeNewFragment : Fragment(), HomeNewAdapter.ItemAdapterCallback {

    private var terbaruList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        terbaruList = arguments?.getParcelableArrayList("data")
//        initDataDummy()
        val adapter = HomeNewAdapter(terbaruList!!,this)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcListVertical.layoutManager = layoutManager
        rcListVertical.adapter = adapter

//        val sectionsPagerAdapter = SectionPagerAdapter(
//            childFragmentManager
//        )
//        viewPager.adapter = sectionsPagerAdapter
//        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCLick(view: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}