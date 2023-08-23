package com.example.goadventure.utils

import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    fun TextView.formatHarga(value: String){
        this.text = getCurrencyIDR(java.lang.Double.parseDouble(value))
//        this.text = getCurrencyIDR(value.toDouble())
    }

    fun convertTimestampToDate(timestamp: Long, format: String, locale: Locale = Locale("id", "ID")): String {
        val date = Date(timestamp)
        val dateFormat = SimpleDateFormat(format, locale)
        return dateFormat.format(date)
    }

    fun getCurrencyIDR(harga : Double) : String{
        val format = DecimalFormat("#,###,###")
        return "Rp. "+format.format(harga).replace(",".toRegex(),".")
    }

    fun getDefaultGson():Gson{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .registerTypeAdapter(Date::class.java, JsonDeserializer{json, _,_,->
                val formatServer = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date>{src, _,_,->
                val format = SimpleDateFormat("", Locale.ENGLISH)
                format.timeZone = TimeZone.getTimeZone("UTC")
                if (src != null){
                    JsonPrimitive(format.format(src))
                }else{
                    null
                }
            }).create()
    }

}