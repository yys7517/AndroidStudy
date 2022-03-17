package com.youngsun.diet_memo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val writeBtn = findViewById<ImageView>(R.id.writeBtn)

        writeBtn.setOnClickListener {
            // 메모 작성 다이얼로그 화면 띄우기.
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("운동 메모 다이얼로그")

            val mAlertDialog = mBuilder.show()

            // 다이얼로그 내 날짜 선택 버튼.
            val dateSelectBtn = mAlertDialog.findViewById<Button>(R.id.dateSelectBtn)

            var dateText = ""

            dateSelectBtn?.setOnClickListener {

                val today = GregorianCalendar()
                val year = today.get(Calendar.YEAR)
                val month = today.get(Calendar.MONTH)
                val date = today.get(Calendar.DATE)

                val dlg = DatePickerDialog(this, object  : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        dateText = "${year}. ${month + 1}. ${dayOfMonth}"
                        Log.d("MAIN",dateText)
                        dateSelectBtn!!.text = dateText
                    }
                }, year, month, date )

                dlg.show()
            }

            // 다이얼로그 내 저장하기 버튼
            val saveBtn = mAlertDialog.findViewById<Button>(R.id.saveBtn)

            saveBtn?.setOnClickListener {
                // Firebase Realtime database 에 메모 내용과 날짜를 저장.
                // Write a message to the database
                val database = Firebase.database
                val myRef = database.getReference("myMemo")

                // 입력된 메모 내용을 가져온다.
                val myMemo = mAlertDialog.findViewById<EditText>(R.id.edtMemo)?.text.toString()

                // myRef.setValue("Hello, World!")          // database 내 값 설정하기.
                // myRef.push().setValue("Hello, World!")   // database 내에 값 push하기.

                if( dateText == "" ) {
                    Toast.makeText(this, "날짜를 선택하세요 !", Toast.LENGTH_SHORT).show()
                }
                else if ( myMemo == "" || myMemo == null ) {
                    Toast.makeText(this, "메모 내용을 입력하세요 !", Toast.LENGTH_SHORT).show()
                }
                else {
                    // 값 삽입.
                    val data = DataModel( dateText, myMemo )
                    myRef
                        .push()
                        .setValue( data )

                    Toast.makeText(this, "메모가 성공적으로 저장되었습니다 !", Toast.LENGTH_SHORT).show()

                    // Dialog 창 닫기.
                    mAlertDialog.dismiss()
                }
            }
        }
    }
}