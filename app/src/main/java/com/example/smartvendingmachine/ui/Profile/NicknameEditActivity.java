package com.example.smartvendingmachine.ui.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartvendingmachine.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NicknameEditActivity extends AppCompatActivity {

    private String USER_NICKNAME, USER_ID;

    private static String IP_ADDRESS = "211.211.158.42";
    private static String TAG = "SmartVendingMachine";


    EditText mEditNickname;
    TextView mTextViewPostResult;
    Button mButtonSubmit;
    ImageView backspace;

    //SharedPreferences
    private SharedPreferences appData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_nickname);

        mTextViewPostResult = findViewById(R.id.mTextViewPostResult);

        Intent intent = getIntent();
        String Default_nickname = intent.getStringExtra("nickname");

        mEditNickname = findViewById(R.id.mEditNickname); // 닉네임 입력 창

        mEditNickname.setText(Default_nickname);        // 기존 닉네임으로 닉네임 창에 입력



        mButtonSubmit = (Button) findViewById(R.id.mButtonSubmit); // 닉네임 설정 버튼

        backspace = findViewById(R.id.backspace);



        //SharedPreferences
        appData = getSharedPreferences("appData", MODE_PRIVATE);

        String userid = appData.getString("ID", ""); // App 사용자 ID

        //건의사항 작성 완료 버튼.
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                USER_NICKNAME = mEditNickname.getText().toString();
                USER_ID = userid;

                if ( USER_NICKNAME.equals(Default_nickname) )
                {
                    Toast.makeText(getApplicationContext(), "기존 닉네임과 동일합니다.", Toast.LENGTH_SHORT).show();
                }
                else if( USER_NICKNAME.length() < 2) {
                    Toast.makeText(getApplicationContext(), "닉네임을 더 길게 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else{
                    EditData task = new EditData();
                    task.execute("http://" + IP_ADDRESS + "/yongrun/svm/USER_MODIFY_ANDROID.php", USER_NICKNAME, USER_ID);
                    Toast.makeText(getApplicationContext(), "닉네임이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                    save(USER_NICKNAME);
                    finish();
                }


            }
        });

        //뒤로가기 버튼
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void save(String nickname) {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        editor.putString("NICKNAME", nickname);

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }

    class EditData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(NicknameEditActivity.this, "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewPostResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }

        @Override
        protected String doInBackground(String... params) {

            String USER_NICKNAME = (String) params[1];
            String USER_ID = (String) params[2];
            String serverURL = (String) params[0];

            String postParameters = "USER_NICKNAME=" + USER_NICKNAME + "&USER_ID=" + USER_ID;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString();
                
            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }

}