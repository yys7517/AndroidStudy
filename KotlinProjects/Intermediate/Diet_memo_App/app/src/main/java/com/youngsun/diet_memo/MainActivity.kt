package com.youngsun.diet_memo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {

    val list_item = mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.mainLV)

        val adapter = ListViewAdapter( list_item )
        listView.adapter = adapter

        val database = Firebase.database
        val myRef = database.getReference("myMemo")

        myRef.child(Firebase.auth.currentUser!!.uid).addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // onDataChange() 메서드는 리스너가 연결될 때 한 번 트리거된 후 하위 항목을 포함하여 데이터가 변경될 때마다 다시 트리거됩니다.

                // 초기에 리스트 값을 비워놓고
                list_item.clear()

                // snapshot에 모든 데이터를 가져와 담는다.
                // snapshot의 내용을 반복문을 통해 꺼내온다.
                for( dataModel in snapshot.children ) {
                    Log.d("Data", dataModel.toString() )

                    list_item.add( dataModel.getValue(DataModel::class.java)!! )     // DataModel 형식으로 값을 변환하여 가져온다.
                }

                adapter.notifyDataSetChanged()  // 값이 들어가서 데이터가 바뀌었을 수도 있으니 어댑터를 새롭게 만들어줘라.
               /*
               val adapter = ListViewAdapter( list_item )
               listView.adapter = adapter
               위와 같이 값을 받고, 어댑터를 지정해주려고 하였다.

               위에서 리스트 뷰의 어댑터를 지정해주고 adapter.notifyDataSetChanged()를 사용하여 값을 새로고침 해줘도 된다.
                 */
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

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

                val database = Firebase.database
                // val myRef = database.getReference("myMemo")     // 값이 저장될 Path.

                // 사용자 별로 uid에 따라 Path를 지정해보자.
                val myRef = database.getReference("myMemo").child(Firebase.auth.currentUser!!.uid)     // 값이 저장될 Path.
                // myMemo / uid / DataModel( 날짜, 메모 내용 )

                // 입력된 메모 내용을 가져온다.
                val myMemo = mAlertDialog.findViewById<EditText>(R.id.edtMemo)?.text.toString()

                // myRef.setValue("Hello, World!")          // database 내 값 설정하기.
                // myRef.push().setValue("Hello, World!")   // database 내에 값 push하기.

                if( dateText == "" ) {  // 날짜 선택 미 완료 시
                    Toast.makeText(this, "날짜를 선택하세요 !", Toast.LENGTH_SHORT).show()
                }
                else if ( myMemo == "" || myMemo == null ) {    // 메모 내용 미 입력 시
                    Toast.makeText(this, "메모 내용을 입력하세요 !", Toast.LENGTH_SHORT).show()
                }
                else {  // 날짜 선택 + 메모 내용 입력 완료 시
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