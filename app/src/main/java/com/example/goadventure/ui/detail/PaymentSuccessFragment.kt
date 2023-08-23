package com.example.goadventure.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goadventure.R
import com.example.goadventure.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_payment_success.*


class PaymentSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

//        btnHome.setOnClickListener{
//            val home = Intent(activity, MainActivity::class.java)
//            startActivity(home)
//        }

        btnHome.setOnClickListener {
            requireActivity().finish()
        }
        btnDetail.setOnClickListener {
            requireActivity().finish()
        }

    }

}