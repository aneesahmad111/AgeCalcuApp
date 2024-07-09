package com.example.dobcaluc

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var datePickerbtn : Button
    lateinit var showDateTv : TextView
    lateinit var  showDateInMits : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        datePickerbtn = findViewById(R.id.Pickerbtn)
        showDateTv = findViewById(R.id.setDate)
        showDateInMits = findViewById(R.id.showDatInMint)

        datePickerbtn.setOnClickListener {


                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this,
                    { view, year, monthOfYear, dayOfMonth ->
                        val selectedDate = "$year/$monthOfYear/$dayOfMonth"
                        showDateTv.text = selectedDate

                        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
                        val theDate = sdf.parse(selectedDate)
                        val selectedDateInMinutes = theDate.time/60000
                        val currentDate  = sdf.parse(sdf.format(System.currentTimeMillis()))
                        val currentDateInMints = currentDate.time/60000
                        val diffInMints = currentDateInMints-selectedDateInMinutes

                        showDateInMits.text = diffInMints.toString()



                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()


        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}