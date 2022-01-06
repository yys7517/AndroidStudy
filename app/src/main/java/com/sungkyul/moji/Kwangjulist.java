package com.sungkyul.moji;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sungkyul.moji.info.KwangjuInfo;
import com.sungkyul.moji.listview.ListViewAdapter;
import com.sungkyul.moji.listview.ListViewItem;

import java.util.ArrayList;
import java.util.List;

public class Kwangjulist extends AppCompatActivity {

    static String tname;
    String a;
    private MyDBHelper mHelper;

    private final static int DATABASE_VERSION = 1;
    private Button btnSearch;
    private AutoCompleteTextView edtStation;
    private TextView lineName,teststation;
    private String hosun = "";

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_click);

        mHelper = new MyDBHelper(this, DATABASE_VERSION);
        db = mHelper.getWritableDatabase();

        btnSearch = findViewById(R.id.btnSearch);
        edtStation = findViewById(R.id.edtStation);
        lineName = findViewById(R.id.lineName);
        teststation = findViewById(R.id.teststation);

        Intent intent = getIntent();

        final String test = intent.getStringExtra("test");

        String line_1 = intent.getStringExtra("line_1");


        final String location = intent.getStringExtra("location");


//        final ListView listview = (ListView) findViewById(R.id.stationlistview);
//
//        final List<String> list = new ArrayList<>();
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, list);

        final List<String> list = new ArrayList<>();
        final ListView listview ;
        final ListViewAdapter adapter;
        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.stationlistview);
        listview.setAdapter(adapter);

        if (test.equals(line_1)) {
            lineName.setText(line_1);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `광주` ", null);
            tname = "";
            hosun="1호선";

            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                tname += name;
                adapter.addItem(tname);
                list.add(tname);
                tname = "";
            }
            listview.setAdapter(adapter);
            cursor.close();

        }

        //////////-----------------------------------------------------------------------------------
        edtStation = findViewById(R.id.edtStation);
        ArrayAdapter<String> searchList = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        edtStation.setAdapter(searchList);


        edtStation.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    a = edtStation.getText().toString().trim().replace(" ", "");
                    edtStation.setText("");

                    return true;
                }
                return false;
            }
        });


//////////-----------------------------------------------------------------------------------


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                String product = item.getTitle();
                Intent it = new Intent(getApplicationContext(), KwangjuInfo.class);   // 인텐트 처리
                it.putExtra("station", product); // 역 이름
                it.putExtra("location",location); // 지역 이름
                it.putExtra("hosun",hosun); // 호선 명
                startActivity(it);

            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = mHelper.getWritableDatabase();
                a = edtStation.getText().toString();
                boolean flag = true;
                if (a.equals("")) {
                    Toast.makeText(getApplicationContext(), "검색 할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if (a.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "검색 할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (flag ) {
                        adapter.clearItem();
                        Cursor cursor = db.rawQuery("SELECT distinct `역명` from `광주` where `역명` LIKE '%"+a+"%'",null);
                        tname = "";

                        while (cursor.moveToNext()) {
                            String name = cursor.getString(0);
                            tname += name;
                            adapter.addItem(tname);
                            tname = "";
                        }
                        listview.setAdapter(adapter);
                        Toast.makeText(getApplicationContext()," '"+a+"'(으)로 검색된 광주 '"+test+"'내 역 검색 결과입니다.",Toast.LENGTH_SHORT).show();

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                                String product = item.getTitle();
                                Intent it = new Intent(getApplicationContext(), KwangjuInfo.class);   // 인텐트 처리
                                it.putExtra("station", product); // 역 이름
                                it.putExtra("location",location); // 지역 이름
                                it.putExtra("hosun",hosun); // 호선 명
                                startActivity(it);

                                edtStation.setText("");
                                adapter.clearItem();
                                Cursor cursor = db.rawQuery("SELECT distinct `역명` from `광주`",null);
                                tname = "";

                                while (cursor.moveToNext()) {
                                    String name = cursor.getString(0);
                                    tname += name;
                                    adapter.addItem(tname);
                                    tname = "";
                                }
                                listview.setAdapter(adapter);

                            }
                        });

                        flag = false;

                    }
                }
            }
        });

        ///--------------------------------------------------------------------------------------------




    }
}
