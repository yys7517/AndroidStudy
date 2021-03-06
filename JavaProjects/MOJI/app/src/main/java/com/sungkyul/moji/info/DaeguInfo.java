package com.sungkyul.moji.info;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sungkyul.moji.FacilDBHelper;
import com.sungkyul.moji.KricHelp;
import com.sungkyul.moji.R;

public class DaeguInfo extends AppCompatActivity {

    SQLiteDatabase db;
    private FacilDBHelper fHelper;
    private final static int DATABASE_VERSION = 1;

    private TextView location_name,station_name,line_name,escalator, elevator, movingwalk, toilet, disabledtoilet, wheellift, charge, center,center_phone;

    private static int REQUEST_ACCESS_CALL_PHONE= 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stationinfo);

        Intent intent = getIntent();

        final String location = intent.getStringExtra("location");
        final String station = intent.getStringExtra("station");
        final String line = intent.getStringExtra("hosun");

        fHelper = new FacilDBHelper(this,DATABASE_VERSION);
        db=fHelper.getWritableDatabase();

        location_name = (TextView)findViewById(R.id.location_name);
        station_name = (TextView)findViewById(R.id.station_name);
        line_name = (TextView) findViewById(R.id.line_name);

        escalator = (TextView)findViewById(R.id.escalator);
        elevator = (TextView)findViewById(R.id.elevator);
        movingwalk = (TextView)findViewById(R.id.movingwalk);
        toilet = (TextView)findViewById(R.id.toilet);
        disabledtoilet = (TextView)findViewById(R.id.disabledtoilet);
        wheellift = (TextView)findViewById(R.id.wheellift);
        charge = (TextView)findViewById(R.id.charge);
        center = (TextView)findViewById(R.id.center);
        center_phone = findViewById(R.id.center_phone);


        location_name.setText(location);
        station_name.setText(station);
        line_name.setText("??????"+line);

// ----------- ????????? ????????? ?????? ?????? ---------------------------------------------//
        TextView webViewInfo = (TextView)findViewById(R.id.webViewInfo);
        webViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KricHelp.class);
                startActivity(intent);
            }
        });



// ----------- ?????????????????? ---------------------------------------------//
        while(escalator.getText().toString().equals("")) {
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM `?????? ??????????????????` where `??????` = '"+station+"' ",null);

            if( cursor.getCount() != 0) {
                escalator.setText("O");
            }
            else
                escalator.setText("X");
        }
// ----------- ??????????????? ---------------------------------------------//
        while(elevator.getText().toString().equals("")) {
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM `?????? ???????????????` where `??????` = '"+station+"' ",null);

            if( cursor.getCount() != 0) {
                elevator.setText("O");
            }
            else
                elevator.setText("X");
        }
// ----------- ???????????? ---------------------------------------------//
        movingwalk.setText("X");
// ----------- ????????? ---------------------------------------------//
        while(toilet.getText().toString().equals("")) {
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM `?????? ?????????` where `??????` = '"+station+"' ",null);

            if( cursor.getCount() != 0) {
                toilet.setText("O");
            }
            else
                toilet.setText("X");
        }
// ----------- ?????????????????? ---------------------------------------------//
        while(disabledtoilet.getText().toString().equals("")) {
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM `?????? ??????????????????` where `??????` = '"+station+"' ",null);

            if( cursor.getCount() != 0) {
                disabledtoilet.setText("O");
            }
            else
                disabledtoilet.setText("X");
        }
// ----------- ????????? ????????? ---------------------------------------------//
        while(wheellift.getText().toString().equals("")) {
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM `?????? ??????????????????` where `??????` = '"+station+"' ",null);

            if( cursor.getCount() != 0) {
                wheellift.setText("O");
            }
            else
                wheellift.setText("X");
        }
// ----------- ????????? ???????????? ---------------------------------------------//
        while(charge.getText().toString().equals("")) {
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM `?????? ??????????????? ????????????` where `??????` = '"+station+"' ",null);

            if( cursor.getCount() != 0) {
                charge.setText("O");
            }
            else
                charge.setText("X");
        }
// ----------- ???????????? ---------------------------------------------//
        while(center.getText().toString().equals("")) {
            String rphone="";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `????????????` FROM `?????? ????????????` where `??????` = '"+station+"' and `??????` = '"+line+"' ",null);
            while(cursor.moveToNext()) {
                String phone = cursor.getString(0);
                if (phone == null)
                {
                    rphone += "";
                }
                else
                    rphone += phone;
            }
            SpannableString content = new SpannableString(rphone);
            content.setSpan(new UnderlineSpan(), 0, rphone.length(), 0);
            if( cursor.getCount() != 0) {
                center.setText("O / ");

                if(rphone.equals("")){
                    center_phone.setText("????????????");
                }
                else
                    center_phone.setText(content);
            }
            else {
                center.setText("X / ");
                center_phone.setText("????????????");
            }
        }
//---------------------------????????????????????????-----------------------------//
        center_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionCheck = ContextCompat.checkSelfPermission(DaeguInfo.this, Manifest.permission.CALL_PHONE);
                    if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                        // ?????? ??????
                        ActivityCompat.requestPermissions(DaeguInfo.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                REQUEST_ACCESS_CALL_PHONE);
                    } else {
                        // ACCESS_FINE_LOCATION ??? ?????? ????????? ?????? ??????.
                        Intent tt = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + center_phone.getText().toString().trim()));
                        startActivity(tt);
                    }
                }
// OS??? Marshmallow ????????? ?????? ??????????????? ?????? ?????????.
                else {

                }
            }
        });





    }

    //???????????? ?????? ????????????
    public void check() {
        // OS??? Marshmallow ????????? ?????? ??????????????? ?????? ?????????.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            if (permissionCheck == PackageManager.PERMISSION_DENIED) {

                // ?????? ??????
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_ACCESS_CALL_PHONE);


            } else {

                // ACCESS_FINE_LOCATION ??? ?????? ????????? ?????? ??????.

            }


        }

// OS??? Marshmallow ????????? ?????? ??????????????? ?????? ?????????.
        else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // grantResults[0] ?????? -> -1
        // grantResults[0] ?????? -> 0 (PackageManager.PERMISSION_GRANTED)

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // ACCESS_FINE_LOCATION ??? ?????? ?????? ??????.

        } else {
            // ACCESS_FINE_LOCATION ??? ?????? ?????? ??????.

        }
    }
}
