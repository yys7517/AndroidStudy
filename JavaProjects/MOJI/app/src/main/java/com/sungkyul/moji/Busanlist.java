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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sungkyul.moji.info.BusanInfo;
import com.sungkyul.moji.listview.ListViewAdapter;
import com.sungkyul.moji.listview.ListViewItem;

import java.util.ArrayList;
import java.util.List;

public class Busanlist extends AppCompatActivity {

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
        String line_2 = intent.getStringExtra("line_2");
        String line_3 = intent.getStringExtra("line_3");

        String line_4 = intent.getStringExtra("line_4");
        String line_kim = intent.getStringExtra("line_kim");
        String line_dong = intent.getStringExtra("line_dong");

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

        // Adapter ??????
        adapter = new ListViewAdapter() ;

        // ???????????? ?????? ??? Adapter??????
        listview = (ListView) findViewById(R.id.stationlistview);
        listview.setAdapter(adapter);

        if (test.equals(line_1)) {
            lineName.setText(line_1);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `??????1??????` ", null);
            tname = "";
            hosun = "1??????";
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
            cursor = db.rawQuery("select distinct * from `??????2??????` ", null);
            tname = "";
            hosun = "2??????";
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
            cursor = db.rawQuery("select distinct * from `??????3??????` ", null);
            tname = "";
            hosun = "3??????";
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
            cursor = db.rawQuery("select distinct * from `??????4??????` ", null);
            tname = "";
            hosun = "4??????";
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
        else if (test.equals(line_kim)) {
            lineName.setText(line_kim);
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `???????????????` ", null);
            tname = "";
            hosun = "?????????????????????";
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
        else if (test.equals(line_dong)) {
            lineName.setText(line_dong );
            Cursor cursor;
            cursor = db.rawQuery("select distinct * from `???????????????` ", null);
            tname = "";
            hosun = "???????????????";
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
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                String product = item.getTitle();
                Intent it = new Intent(getApplicationContext(), BusanInfo.class);   // ????????? ??????
                it.putExtra("station", product); // ??? ??????
                it.putExtra("location",location); // ?????? ??????
                it.putExtra("hosun",hosun); // ?????? ???
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
                    Toast.makeText(getApplicationContext(), "?????? ??? ????????? ???????????????.", Toast.LENGTH_SHORT).show();
                } else if (a.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "?????? ??? ????????? ???????????????.", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (flag ) {
                        adapter.clearItem();
                        Cursor cursor = db.rawQuery("SELECT distinct `??????` from `"+test+"` where `??????` LIKE '%"+a+"%'",null);
                        tname = "";

                        while (cursor.moveToNext()) {
                            String name = cursor.getString(0);
                            tname += name;
                            adapter.addItem(tname);
                            tname = "";
                        }
                        listview.setAdapter(adapter);
                        Toast.makeText(getApplicationContext()," '"+a+"'(???)??? ????????? '"+test+"'??? ??? ?????? ???????????????.",Toast.LENGTH_SHORT).show();

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                                String product = item.getTitle();
                                Intent it = new Intent(getApplicationContext(), BusanInfo.class);   // ????????? ??????
                                it.putExtra("station", product); // ??? ??????
                                it.putExtra("location",location); // ?????? ??????
                                it.putExtra("hosun",hosun); // ?????? ???
                                startActivity(it);

                                edtStation.setText("");
                                adapter.clearItem();
                                Cursor cursor = db.rawQuery("SELECT distinct `??????` from `"+test+"`",null);
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
