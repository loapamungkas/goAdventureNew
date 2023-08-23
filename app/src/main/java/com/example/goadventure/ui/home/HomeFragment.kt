package com.example.goadventure.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goadventure.GoAdventure
import com.example.goadventure.R
import com.example.goadventure.databinding.FragmentHomeBinding
import com.example.goadventure.model.response.home.Data
import com.example.goadventure.model.response.home.HomeResponse
import com.example.goadventure.model.response.login.User
import com.example.goadventure.ui.MainActivity
import com.example.goadventure.ui.detail.DetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View,View.OnClickListener{

    private var terbaruList: ArrayList<Data> = ArrayList()
    private var popularList: ArrayList<Data> = ArrayList()
    private var recommendedList: ArrayList<Data> = ArrayList()

    private lateinit var presenter: HomePresenter
    var progressDialog: Dialog? = null

    private lateinit var sectionPagerAdapter: SectionPagerAdapter

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        initDataDummy()

        initView()

        showPhotoProfileUser()

        presenter = HomePresenter(this)
        presenter.getHome()

        ivProfile.setOnClickListener(this)
    }

    fun showPhotoProfileUser(){

        val user = GoAdventure.getApp().getUser()

        if (user != null) {
            val userResponse = Gson().fromJson(user, User::class.java)
            if (!userResponse.profile_photo_path.isNullOrEmpty()) {
                Glide.with(requireActivity())
                    .load(userResponse.profile_photo_url) // Ubah ke profile_photo_path
                    .apply(RequestOptions().override(110, 110).circleCrop())
                    .into(ivProfile)
            } else {
                Glide.with(requireActivity())
                    .load(R.drawable.ic_picture_empty) // Ubah ke profile_photo_path
                    .apply(RequestOptions().override(110, 110).circleCrop())
                    .into(ivProfile)
            }
        } else {
            Toast.makeText(activity , "RAKETEMU AKUN E" , Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

//        var user = GoAdventure.getApp().getUser()
//        var userResponse = Gson().fromJson(user, User::class.java)
//
//
//        if (!userResponse.profile_photo_path.isNullOrEmpty()) {
//            Glide.with(requireActivity())
//                .load("http://192.168.100.11/go_adventure_backend/public/storage/"+userResponse.profile_photo_path)
//                .into(ivProfile)
//        }
    }

//    fun initDataDummy(){
//        alatList = ArrayList()
//        alatList.add(HomeModel("Tendaki Moluccas 6 Pro","","75000"))
//        alatList.add(HomeModel("Tendaki Java 4 Pro","", "50000"))
//        alatList.add(HomeModel("Tendaki Borneo 4","", "40000"))
//        alatList.add(HomeModel("Bassic Pollux 2","", "35000"))
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCLick(view: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {

        showHomeCardHorizontal(homeResponse)
        showPagerAdapter(homeResponse)

//        for(a in homeResponse.data.indices){
//            var items:List<String> = homeResponse.data[a].type?.split(",") ?: ArrayList()
//            for (x in items.indices){
//                if (items[x].equals("terbaru",true)){
//                    terbaruList?.add(homeResponse.data[a])
//                }else if (items[x].equals("popular",true)){
//                    popularList?.add(homeResponse.data[a])
//                }else if (items[x].equals("recomended",true)){
//                    recommendedList?.add(homeResponse.data[a])
//                }
//            }
//        }
//
//        val adapter = HomeAdapter(homeResponse.data,this)
//        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        rcListHorizontal.layoutManager = layoutManager
//        rcListHorizontal.adapter = adapter
//
//        sectionPagerAdapter = SectionPagerAdapter(
//            childFragmentManager
//        )
//        sectionPagerAdapter.setData(terbaruList, popularList, recommendedList)
//
//        viewPager.adapter = sectionPagerAdapter
//        tabLayout.setupWithViewPager(viewPager)
    }

    fun showPagerAdapter(homeResponse: HomeResponse){
        for(a in homeResponse.data.indices){
            var items:List<String> = homeResponse.data[a].type?.split(",") ?: ArrayList()
            for (x in items.indices){
                if (items[x].equals("terbaru",true)){
                    terbaruList?.add(homeResponse.data[a])
                }else if (items[x].equals("popular",true)){
                    popularList?.add(homeResponse.data[a])
                }else if (items[x].equals("recomended",true)){
                    recommendedList?.add(homeResponse.data[a])
                }
            }
        }

        sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        sectionPagerAdapter.setData(terbaruList, popularList, recommendedList)

        viewPager.adapter = sectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    fun showHomeCardHorizontal(homeResponse: HomeResponse){
        val adapter = HomeAdapter(homeResponse.data,this)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcListHorizontal.layoutManager = layoutManager
        rcListHorizontal.adapter = adapter
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(activity , "${message}" , Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.ivProfile -> {
                Navigation.findNavController(view)
                    .navigate(R.id.navigation_profile , null)
            }
        }
    }


}