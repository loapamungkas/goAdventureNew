package com.example.goadventure.ui.detail

import com.example.goadventure.base.BasePresenter
import com.example.goadventure.base.BaseView
import com.example.goadventure.model.response.checkout.CheckoutResponse

interface PaymentContract {
    interface  View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(
            idProduk: String,
            idUser: String,
            totalBarang: String,
            total: String,
            tglSewa: String,
            tglKembali: String,
            view: android.view.View
        )
    }
}