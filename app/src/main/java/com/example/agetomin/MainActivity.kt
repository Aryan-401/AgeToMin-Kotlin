package com.example.agetomin

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectDate : TextView? = null
    private var differenceMins : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectDate = findViewById(R.id.tvSelectDate)
        differenceMins = findViewById(R.id.differenceMins)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    @SuppressLint("SetTextI18n")
    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayofMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, year, month, dayofMonth ->
            val selectedDate: String = "$dayofMonth/${month+1}/$year"

            tvSelectDate?.text = selectedDate
            val sqf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sqf.parse(selectedDate)
            val selectedDateInMinutes = theDate.time / 60000

            val currentDate = sqf.parse(sqf.format(System.currentTimeMillis()))
            val currentDateInMin = currentDate.time / 60000
            val difference = currentDateInMin - selectedDateInMinutes

            differenceMins?.text = (difference).toString()


        }, year, month, dayofMonth
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }

}
