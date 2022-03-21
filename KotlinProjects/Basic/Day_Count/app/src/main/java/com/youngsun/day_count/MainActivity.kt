package com.youngsun.day_count

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startDateBtn)
        val endButton = findViewById<Button>(R.id.endDateBtn)

        val calendar_start = Calendar.getInstance()
        val calendar_end = Calendar.getInstance()

        var startDate = ""

        // 시작일 버튼을 눌렀을 때
        startButton.setOnClickListener {

            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)

            val dlg = DatePickerDialog( this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    startDate = "${year}${month+1}${dayOfMonth}"

                    Log.d("startDate", startDate)

                    calendar_start.set( year, month+1, dayOfMonth )
                }
            }, year, month, day)

            dlg.show()
        }

        var endDate = ""

        // 종료일 버튼을 눌렀을 때
        endButton.setOnClickListener {
            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)

            val dlg = DatePickerDialog( this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    endDate = "${year}${month+1}${dayOfMonth}"
                    Log.d("endDate", endDate)

                    calendar_end.set(year, month+1, dayOfMonth)

                    // 종료 날짜와 시작 날짜의 차이 ms를 구한다.
                    // ms 단위를 day 단위로 변환한다.
                    val result = TimeUnit.MILLISECONDS.toDays( calendar_end.timeInMillis - calendar_start.timeInMillis )
                    
                    findViewById<TextView>(R.id.txtDayCount).setText( result.toString() )
                }
            }, year, month, day)

            dlg.show()
        }
    }
}