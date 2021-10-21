package com.example.smartvendingmachine.ui.board;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
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

public class BoardEditActivity extends AppCompatActivity {

    private String mTitle,mContents;                     // 기존 게시글의 제목과 내용 Intent로 가져오기.
    private String POST_CODE, POST_TITLE ,POST_CONTENTS;   // 게시글 코드, 게시글 제목, 게시글 내용 PHP 파라미터 값.

    private static String IP_ADDRESS = "59.14.35.61";
    private static String TAG = "SmartVendingMachine";


    EditText mEditTextTitle, mEditTextContents;
    TextView mTextViewPostResult;
    Button mButtonSubmit;
    ImageView backspace;

    //SharedPreferences
    private SharedPreferences appData;      // 앱 설정 값 공유하기 위한 변수

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_write);


        mEditTextTitle = (EditText) findViewById(R.id.mEditTextTitle);
        mEditTextContents = (EditText) findViewById(R.id.mEditTextContents);

        mTextViewPostResult = (TextView) findViewById(R.id.mTextViewPostResult);

        mButtonSubmit = (Button) findViewById(R.id.mButtonSubmit);

        backspace = findViewById(R.id.backspace);

        // 수정 시 기존 값이 있다면 받아오는 것 ( TextUtils.isempty )
        Intent intent = getIntent();
        if ( ! ( TextUtils.isEmpty(intent.getStringExtra("title")) &&
                TextUtils.isEmpty(intent.getStringExtra("contents")) && TextUtils.isEmpty(intent.getStringExtra("post_code"))) ) {

            mTitle = intent.getStringExtra("title");        //기존 게시글 제목
            mContents =intent.getStringExtra("contents");   //기존 게시글 내용
            POST_CODE = intent.getStringExtra("post_code"); //수정 하는 게시글 코드.

        }

        mEditTextTitle.setText(mTitle);     // 기존 게시글 제목 가져오기
        mEditTextContents.setText(mContents);   // 기존 게시글 내용 가져오기


        //SharedPreferences
        appData = getSharedPreferences("appData", MODE_PRIVATE);

        String userid = appData.getString("ID", ""); // App 사용자 ID


        //건의사항 작성 완료 버튼.
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                POST_TITLE = mEditTextTitle.getText().toString();
                POST_CONTENTS = mEditTextContents.getText().toString();
                EditData task = new EditData();
                //게시글 수정하는 php파일에 인자 값 전달.
                task.execute("http://" + IP_ADDRESS + "/yongrun/svm/POST_MODIFY_ANDRIOD.php", POST_CODE, POST_TITLE , POST_CONTENTS);
                Toast.makeText(getApplicationContext(), "건의사항이 수정되었습니다.", Toast.LENGTH_SHORT).show();

                finish(); // 게시글 작성 창(수정 창) 종료.
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

    class EditData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(BoardEditActivity.this, "Please Wait", null, true, true);
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
            String POST_CODE = (String) params[1];
            String POST_TITLE = (String) params[2];
            String POST_CONTENTS = (String) params[3];
            String serverURL = (String) params[0];

            String postParameters = "POST_CODE=" + POST_CODE + "&POST_TITLE=" + POST_TITLE + "&POST_CONTENTS=" + POST_CONTENTS;

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