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

public class Daegu extends AppCompatActivity {

    static String tname;
    Cursor cursor;
    SQLiteDatabase db;
    private MyDBHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daegu);

        mHelper = new MyDBHelper(this, 1);
        db = mHelper.getWritableDatabase();

        final TextView line_1 = (TextView) findViewById(R.id.line_1);
        final TextView line_2= (TextView) findViewById(R.id.line_2);
        final TextView line_3 = (TextView) findViewById(R.id.line_3);

        final TextView location = (TextView)findViewById(R.id.locationname);


        final TextView test = (TextView) findViewById(R.id.test);


        final ListView listview = (ListView) findViewById(R.id.stationlistview);

        final ArrayList<String> list = new ArrayList<>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        line_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Daegulist.class);
                test.setText("대구1호선");
                intent.putExtra("line_1", "대구1호선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Daegulist.class);
                test.setText("대구2호선");
                intent.putExtra("line_2", "대구2호선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Daegulist.class);
                test.setText("대구3호선");
                intent.putExtra("line_3", "대구3호선");
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });


    }
}
