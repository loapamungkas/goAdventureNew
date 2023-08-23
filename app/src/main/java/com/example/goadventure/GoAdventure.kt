package com.example.goadventure

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.example.goadventure.network.HttpClient

class GoAdventure : MultiDexApplication() {

    companion object {
        lateinit var instance: GoAdventure

        fun getApp(): GoAdventure {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setUser(user : String){
        getPreferences().edit().putString("PREFERENCES_USER" , user).apply()
    }

    fun getUser() : String? {
        return getPreferences().getString("PREFERENCES_USER",null)
    }

    fun setToken(token : String){
        getPreferences().edit().putString("PREFERENCES_TOKEN" , token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken() : String? {
        return getPreferences().getString("PREFERENCES_TOKEN",null)
    }

//    fun clearToken() {
//        getPreferences().edit().remove(PREFERENCES_TOKEN).apply()
//        HttpClient.getInstance().buildRetrofitClient("")
//    }
}