package com.example.goadventure.ui.detail

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.goadventure.R
import com.example.goadventure.model.response.home.Data
import com.example.goadventure.ui.MainActivity
import com.example.goadventure.utils.Helpers.formatHarga
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment : Fragment() {

    var bundle:Bundle?= null

    private var totalHari: Int = 0

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }
        }


        btnOrderNow.setOnClickListener{
            if(totalHari!=0){
                Navigation.findNavController(it).navigate(R.id.action_payment, bundle)
            }else{
                Toast.makeText(context, "Silahkan Pilih Tanggal", Toast.LENGTH_SHORT).show()
            }
        }

        icBackDetail.setOnClickListener {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
        }


        tvTglSewa.setOnClickListener {
            showDatePickerDialog(tvTglSewa)
        }

        tvTglKembali.setOnClickListener {
            showDatePickerDialog(tvTglKembali)
        }
    }

    private fun showDatePickerDialog(textView: TextView) {
        val currentDate = Calendar.getInstance()

        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                // Create a Calendar instance for the selected date
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                // Check if selected date is after or equal to today's date
                if (selectedDate >= currentDate) {
                    // Update the calendar with the selected date
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    updateTextView(textView)
                } else {
                    // Show an error message or feedback to the user
                    Toast.makeText(requireContext(), "Tanggal tidak boleh sebelum hari ini.", Toast.LENGTH_SHORT).show()
                }
            }, year, month, day
        )

        // Set minimum date to today's date
        datePickerDialog.datePicker.minDate = currentDate.timeInMillis

        datePickerDialog.show()
    }

//    private fun showDatePickerDialog(textView: TextView) {
//        val currentDate = Calendar.getInstance()
//        val oneWeekAhead = Calendar.getInstance()
//        oneWeekAhead.add(Calendar.DAY_OF_MONTH, 7)
//
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//        val datePickerDialog = DatePickerDialog(requireContext(),
//            { _, selectedYear, selectedMonth, selectedDay ->
//                // Create a Calendar instance for the selected date
//                val selectedDate = Calendar.getInstance()
//                selectedDate.set(selectedYear, selectedMonth, selectedDay)
//
//                // Check if selected date is within the allowed range
//                if (selectedDate.after(currentDate) && selectedDate.before(oneWeekAhead)) {
//                    // Update the calendar with the selected date
//                    calendar.set(selectedYear, selectedMonth, selectedDay)
//                    updateTextView(textView)
//                } else {
//                    // Show an error message or feedback to the user
//                    Toast.makeText(requireContext(), "Maximal Penyewaan 1 Minggu.", Toast.LENGTH_SHORT).show()
//                }
//            }, year, month, day)
//
//        // Set minimum and maximum date for the DatePickerDialog
//        datePickerDialog.datePicker.minDate = currentDate.timeInMillis
//        datePickerDialog.datePicker.maxDate = oneWeekAhead.timeInMillis
//
//        datePickerDialog.show()
//    }

    private fun updateTextView(textView: TextView) {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val selectedDate = calendar.time
        val formattedDate = dateFormat.format(selectedDate)
        textView.text = formattedDate
        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }
        }
    }

    private fun initView(data: Data?) {

//        bundle = bundleOf("data" to data)
        bundle = Bundle()
        bundle?.putInt("totalHari" , totalHari)
        bundle?.putParcelable("data" , data)


        Glide.with(requireContext())
            .load("http://192.168.100.11/go_adventure_backend/public/storage/"+data?.produk_photo_path)
            .into(ivPoster)

        tvTitleDetail.text = data?.nama_produk
        tvHargaDetail.formatHarga(data?.harga.toString())
        tvDeskripsi.text = data?.keterangan
        tvStok.text = "Stok : "+data?.stok.toString()

        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val tglSewaText = tvTglSewa.text.toString()
        val tglKembaliText = tvTglKembali.text.toString()


        if (tglSewaText.isNotBlank() && tglKembaliText.isNotBlank() &&
            tglKembaliText != "Pilih Tanggal Kembali") {
            try {
                val tglSewa = dateFormat.parse(tglSewaText)
                val tglKembali = dateFormat.parse(tglKembaliText)

                val tglSewaTimestamp = tglSewa.time
                val tglKembaliTimestamp = tglKembali.time

                val diffInMillis = tglKembali.time - tglSewa.time
                totalHari = (diffInMillis / (1000 * 60 * 60 * 24)).toInt()
                tvTotalDays.text = "Total : $totalHari hari"

                bundle?.putInt("totalHari" , totalHari)
                bundle?.putLong("tglSewaTimestamp", tglSewaTimestamp)
                bundle?.putLong("tglKembaliTimestamp", tglKembaliTimestamp)

                if (data?.harga != null) {
                    val totalHarga: Int = data.harga * totalHari
                    tvTotalHarga.formatHarga(totalHarga.toString())
                } else {
                    // Handle jika data?.harga null
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        } else {
            tvTotalDays.text = "Total :"
        }
    }

}