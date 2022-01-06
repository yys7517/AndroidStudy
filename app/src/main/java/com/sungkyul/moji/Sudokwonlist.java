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

import com.sungkyul.moji.info.SudokwonInfo;
import com.sungkyul.moji.listview.ListViewAdapter;
import com.sungkyul.moji.listview.ListViewItem;

import java.util.ArrayList;
import java.util.List;

public class Sudokwonlist extends AppCompatActivity {

    static String tname;
    String a;
    private MyDBHelper mHelper;

    private final static int DATABASE_VERSION = 1;
    private Button btnSearch;
    private AutoCompleteTextView edtStation;
    private TextView lineName,teststation;

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_click);

        mHelper = new MyDBHelper(this, DATABASE_VERSION);
        db = mHelper.getWritableDatabase();

        btnSearch = findViewById(R.id.btnSearch);

        lineName = findViewById(R.id.lineName);
        teststation = findViewById(R.id.teststation);

        Intent intent = getIntent();

        final String test = intent.getStringExtra("test");

        String line_1 = intent.getStringExtra("line_1");
        String line_2 = intent.getStringExtra("line_2");
        String line_3 = intent.getStringExtra("line_3");

        String line_4 = intent.getStringExtra("line_4");
        String line_5 = intent.getStringExtra("line_5");
        String line_6 = intent.getStringExtra("line_6");

        String line_7 = intent.getStringExtra("line_7");
        String line_8 = intent.getStringExtra("line_8");
        String line_9 = intent.getStringExtra("line_9");

        String line_kyeonggang = intent.getStringExtra("line_kyeonggang");
        String line_kyongchun = intent.getStringExtra("line_kyongchun");
        String line_airport = intent.getStringExtra("line_airport");

        String line_bundang = intent.getStringExtra("line_bundang");
        String line_sooin = intent.getStringExtra("line_sooin");
        String line_newbundang = intent.getStringExtra("line_newbundang");

        String line_ever = intent.getStringExtra("line_ever");
        String line_wooee = intent.getStringExtra("line_wooee");
        String line_euijungbu = intent.getStringExtra("line_euijungbu");

        String line_incheon1 = intent.getStringExtra("line_incheon1");
        String line_incheon2 = intent.getStringExtra("line_incheon2");
        String line_magnet = intent.getStringExtra("line_magnet");

        String line_center = intent.getStringExtra("line_center");

        final String location = intent.getStringExtra("location");


//        final ListView listview = (ListView) findViewById(R.id.stationlistview);
//
//        final List<String> list = new ArrayList<>();
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, list);

        final ListView listview ;
        final ListViewAdapter adapter;
        final List<String> list = new ArrayList<>();

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.stationlistview);
        listview.setAdapter(adapter);


        if (test.equals(line_1)) {
            lineName.setText(line_1);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `1호선` ", null);
            tname = "";

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
        else if (test.equals(line_2)) {
            lineName.setText(line_2);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `2호선` ", null);
            tname = "";

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
        else if (test.equals(line_3)) {
            lineName.setText(line_3);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `3호선` ", null);
            tname = "";

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
        ////////////////////////////////////////////////////////////
        else if (test.equals(line_4)) {
            lineName.setText(line_4 );
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `4호선` ", null);
            tname = "";

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
        else if (test.equals(line_5)) {
            lineName.setText(line_5);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `5호선` ", null);
            tname = "";

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
        else if (test.equals(line_6)) {
            lineName.setText(line_6 );
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `6호선` ", null);
            tname = "";

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
        else if (test.equals(line_7)) {
            lineName.setText(line_7);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `7호선` ", null);
            tname = "";

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
        else if (test.equals(line_8)) {
            lineName.setText(line_8);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `8호선` ", null);
            tname = "";

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
        else if (test.equals(line_9)) {
            lineName.setText(line_9);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `9호선` ", null);
            tname = "";

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
        else if (test.equals(line_kyeonggang)) {
            lineName.setText(line_kyeonggang);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `경강선` ", null);
            tname = "";

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
        else if (test.equals(line_kyongchun)) {
            lineName.setText(line_kyongchun);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `경춘선` ", null);
            tname = "";

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
        else if (test.equals(line_airport)) {
            lineName.setText(line_airport);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `공항철도` ", null);
            tname = "";

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
        else if (test.equals(line_bundang)) {
            lineName.setText(line_bundang);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `분당선` ", null);
            tname = "";

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
        else if (test.equals(line_sooin)) {
            lineName.setText(line_sooin);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `수인선` ", null);
            tname = "";

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
        else if (test.equals(line_newbundang)) {
            lineName.setText(line_newbundang);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `신분당` ", null);
            tname = "";

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
        else if (test.equals(line_ever)) {
            lineName.setText(line_ever);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `에버라인` ", null);
            tname = "";

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
        else if (test.equals(line_wooee)) {
            lineName.setText(line_wooee);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `우이신설` ", null);
            tname = "";

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
        else if (test.equals(line_euijungbu)) {
            lineName.setText(line_euijungbu);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `의정부` ", null);
            tname = "";

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
        else if (test.equals(line_incheon1)) {
            lineName.setText(line_incheon1);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `인천1호선` ", null);
            tname = "";

            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                tname += name;
                adapter.addItem(tname);
                list.add(tname);
                tname = "";
            }
            listview.setAdapter(adapter);
            cursor.close();

        }else if (test.equals(line_incheon2)) {
            lineName.setText(line_incheon2);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `인천2호선` ", null);
            tname = "";

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
        else if (test.equals(line_magnet)) {
            lineName.setText(line_magnet);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `자기부상철도` ", null);
            tname = "";

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
        else if (test.equals(line_center)) {
            lineName.setText(line_center);
            Cursor cursor;
            cursor = db.rawQuery("select distinct `역명` from `경의중앙선` ", null);
            tname = "";

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
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                //get item
                ListViewItem item = (ListViewItem)parent.getItemAtPosition(position);
                String product = item.getTitle().trim();
                Intent it = new Intent(getApplicationContext(), SudokwonInfo.class);   // 인텐트 처리
                it.putExtra("station", product); // 역 이름
                it.putExtra("location",location); // 지역 이름
                it.putExtra("hosun",test);
                startActivity(it);

            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = mHelper.getWritableDatabase();
                a = edtStation.getText().toString().trim();
                boolean flag = true;
                if (a.equals("")) {
                    Toast.makeText(getApplicationContext(), "검색 할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if (a.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "검색 할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (flag ) {
                        adapter.clearItem();
                        Cursor cursor = db.rawQuery("SELECT distinct `역명` from `"+test+"` where `역명` LIKE '%"+a+"%'",null);
                        tname = "";

                        while (cursor.moveToNext()) {
                            String name = cursor.getString(0);
                            tname += name;
                            adapter.addItem(tname);
                            tname = "";
                        }
                        listview.setAdapter(adapter);
                        Toast.makeText(getApplicationContext()," '"+a+"'(으)로 검색된 '"+test+"'내 역 검색 결과입니다.",Toast.LENGTH_SHORT).show();

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView parent, View v, int position, long id) {

                                //get item
                                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                                String product = item.getTitle();
                                Intent it = new Intent(getApplicationContext(), SudokwonInfo.class);   // 인텐트 처리
                                it.putExtra("station", product); // 역 이름
                                it.putExtra("location",location); // 지역 이름
                                it.putExtra("hosun",test);
                                startActivity(it);

                                edtStation.setText("");
                                adapter.clearItem();
                                Cursor cursor = db.rawQuery("SELECT distinct `역명` from `"+test+"`",null);
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
