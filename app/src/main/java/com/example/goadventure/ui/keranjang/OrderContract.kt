package com.example.goadventure.ui.keranjang

import com.example.goadventure.base.BasePresenter
import com.example.goadventure.base.BaseView
import com.example.goadventure.model.response.home.HomeResponse
import com.example.goadventure.model.response.transaksi.TransactionResponse

interface OrderContract {
    interface View : BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }

}