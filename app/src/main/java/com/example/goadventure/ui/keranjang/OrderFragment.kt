package com.example.goadventure.ui.keranjang

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.goadventure.GoAdventure
import com.example.goadventure.R
import com.example.goadventure.model.response.transaksi.Data
import com.example.goadventure.model.response.transaksi.TransactionResponse
import com.example.goadventure.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_order.*


class OrderFragment : Fragment(), OrderContract.View {

    lateinit var presenter: OrderPresenter
    var progressDialog: Dialog? = null

    var inprogresList: ArrayList<Data>? = ArrayList()
    var pastorderList: ArrayList<Data>? = ArrayList()

    private lateinit var toolbar : Toolbar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)!!
        toolbar.title = "Pesanan Anda"
        toolbar.subtitle = "Barang Terbaikmu!"
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if (transactionResponse.data.isNullOrEmpty()) {
            ll_empty.visibility = View.VISIBLE
            ll_tab.visibility = View.GONE
            include_toolbar.visibility = View.GONE
        } else {
            for (a in transactionResponse.data.indices) {
                if (transactionResponse.data[a].status.equals("PENDING", true)) {
                    inprogresList?.add(transactionResponse.data[a])
                } else
                    if (transactionResponse.data[a].status.equals("DELIVERED", true)
                        || transactionResponse.data[a].status.equals("CANCELLED", true)
                        || transactionResponse.data[a].status.equals("SUCCESS", true)) {
                        pastorderList?.add(transactionResponse.data[a])
                    }
            }

            var sectionPagerAdapter = SectionPagerAdapter(
                childFragmentManager
            )
            sectionPagerAdapter.setData(inprogresList, pastorderList)
            view_pager.adapter = sectionPagerAdapter
            tab_layout.setupWithViewPager(view_pager)
        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(activity , message , Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}