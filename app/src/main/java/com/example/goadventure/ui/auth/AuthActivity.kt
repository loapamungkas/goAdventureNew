package com.example.goadventure.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.goadventure.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class AuthActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        toolbar = findViewById(R.id.toolbar) // Inisialisasi toolbar dari layout
        setSupportActionBar(toolbar)

        val pageRequest = intent.getIntExtra("page_request", 0)
        if(pageRequest == 2){
            toolbarSignUp()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()
            Navigation.findNavController(findViewById(R.id.authHostFragment))
                .navigate(R.id.action_signup,null,navOptions)
        }
    }

    fun toolbarSignUp(){

        toolbar.title = "Daftar"
        toolbar.subtitle = "Daftar Sekarang Juga !!!"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    fun toolbarSignUpAddress(){
        toolbar.title = "Alamat"
        toolbar.subtitle = "Isi Alamat yang Valid !!!"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener{onBackPressed()}
    }

    fun toolbarSignUpSuccess(){
        toolbar.visibility = View.GONE
    }
}