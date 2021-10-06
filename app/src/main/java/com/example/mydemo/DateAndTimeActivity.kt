package com.example.mydemo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import java.util.*

class DateAndTimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_and_time)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val rBtn = findViewById<Button>(R.id.btn_dt)
        val dTxt = findViewById<TextView>(R.id.txt_date)

        rBtn.setOnClickListener {
            val date : Date = Calendar.getInstance().time
            dTxt.text = date.toString()

        }

        dTxt.setOnClickListener {
            val c = Calendar.getInstance()
            val tYear = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in text
                    val d = "$dayOfMonth / ${monthOfYear+1} / $year"
                    dTxt.text = d

                }, tYear, month, day).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}