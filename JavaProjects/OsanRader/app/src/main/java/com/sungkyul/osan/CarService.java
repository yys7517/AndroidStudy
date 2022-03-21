package com.sungkyul.osan;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sungkyul.osan.listview.ListViewAdapter;
import com.sungkyul.osan.listview.ListViewItem;

import java.util.ArrayList;
import java.util.List;

public class CarService extends AppCompatActivity {

    static String tname;
    String a;
    private MyDBHelper mHelper;

    private final static int DATABASE_VERSION = 1;
    private Button btnSearch;
    private AutoCompleteTextView autocompfacil;
    private TextView categoryname;
    private ImageView icon1,icon2;
    private String target;

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_service);

        mHelper = new MyDBHelper(this, DATABASE_VERSION);
        db = mHelper.getWritableDatabase();

        btnSearch = findViewById(R.id.btnSearch);
        autocompfacil = findViewById(R.id.autocompfacil);
        categoryname = findViewById(R.id.categoryname);
        icon1 = findViewById(R.id.icon1);
        icon2 = findViewById(R.id.icon2);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");


        final ListView listview ;
        final ListViewAdapter adapter;
        final List<String> list = new ArrayList<>();

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.facil_listview);
        listview.setAdapter(adapter);

        if(category.equals("차량지원 서비스")) {
            icon1.setImageResource(R.drawable.carservice);
            icon2.setImageResource(R.drawable.carservice);

            Cursor cursor = db.rawQuery("SELECT DISTINCT `institutution` from `vehicle`",null);
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




//        final ListView listview = (ListView) findViewById(R.id.stationlistview);
//
//        final List<String> list = new ArrayList<>();
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, list);



        //////////-----------------------------------------------------------------------------------
        autocompfacil = findViewById(R.id.autocompfacil);
        ArrayAdapter<String> searchList = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        autocompfacil.setAdapter(searchList);


        autocompfacil.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    a = autocompfacil.getText().toString().trim().replace(" ", "");
                    autocompfacil.setText("");

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
                Intent it = new Intent(getApplicationContext(), CarServiceInfo.class);   // 인텐트 처리
                it.putExtra("product", product); // 시설 이름, 차량의 경우 지원 대상.
                startActivity(it);

            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = mHelper.getWritableDatabase();
                a = autocompfacil.getText().toString();
                boolean flag = true;
                if (a.equals("")) {
                    Toast.makeText(getApplicationContext(), "검색 할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if (a.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "검색 할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (flag ) {
                        adapter.clearItem();

                            Cursor cursor = db.rawQuery("SELECT distinct `institutution` from `vehicle` where `target` LIKE '%"+a+"%' ",null);
                            tname = "";

                            while (cursor.moveToNext()) {
                                String name = cursor.getString(0);
                                tname += name;
                                adapter.addItem(tname);
                                tname = "";
                            }
                            listview.setAdapter(adapter);
                            Toast.makeText(getApplicationContext()," '"+a+"'(으)로 검색된 결과입니다.",Toast.LENGTH_SHORT).show();

                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                                    String product = item.getTitle();
                                    Intent it = new Intent(getApplicationContext(), CarServiceInfo.class);   // 인텐트 처리
                                    it.putExtra("product", product); // 시설 이름, 차량의 경우 지원 대상.
                                    startActivity(it);

                                    autocompfacil.setText("");
                                    adapter.clearItem();
                                    Cursor cursor = db.rawQuery("SELECT distinct `institutution` from `vehicle` ",null);
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
