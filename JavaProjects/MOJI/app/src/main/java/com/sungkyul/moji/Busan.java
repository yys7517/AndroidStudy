package com.sungkyul.moji;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Busan extends AppCompatActivity {

    static String tname;
    Cursor cursor;
    SQLiteDatabase db;
    private MyDBHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busan);

        mHelper = new MyDBHelper(this, 1);
        db = mHelper.getWritableDatabase();

        final TextView line_1 = (TextView) findViewById(R.id.line_1);
        final TextView line_2= (TextView) findViewById(R.id.line_2);
        final TextView line_3 = (TextView) findViewById(R.id.line_3);
        final TextView line_4 = (TextView) findViewById(R.id.line_4);
        final TextView line_kim = (TextView) findViewById(R.id.line_kim);
        final TextView line_dong = (TextView) findViewById(R.id.line_dong);

        final TextView location = (TextView)findViewById(R.id.locationname);


        final TextView test = (TextView) findViewById(R.id.test);


        final ListView listview = (ListView) findViewById(R.id.stationlistview);

        final ArrayList<String> list = new ArrayList<>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        line_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Busanlist.class);
                test.setText("부산1호선");
                intent.putExtra("line_1", "부산1호선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Busanlist.class);
                test.setText("부산2호선");
                intent.putExtra("line_2", "부산2호선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Busanlist.class);
                test.setText("부산3호선");
                intent.putExtra("line_3", "부산3호선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////
        line_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Busanlist.class);
                test.setText("부산4호선");
                intent.putExtra("line_4", "부산4호선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_kim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Busanlist.class);
                test.setText("부산김해선");
                intent.putExtra("line_kim", "부산김해선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Busanlist.class);
                test.setText("부산동해선");
                intent.putExtra("line_dong", "부산동해선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////


    }
}
