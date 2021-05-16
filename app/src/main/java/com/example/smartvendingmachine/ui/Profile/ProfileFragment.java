package com.example.smartvendingmachine.ui.Profile;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.smartvendingmachine.R;
import com.example.smartvendingmachine.ui.board.BoardAdapter;
import com.example.smartvendingmachine.ui.board.BoardData;
import com.example.smartvendingmachine.ui.board.BoardFragment;
import com.example.smartvendingmachine.ui.board.BoardMainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private SharedPreferences appData;

    private String sharedNickname;
    private String user_id;
    private static int BoardCount = 0;

    private TextView txt_profile_name, txt_profile_board_number;

    private RecyclerView recyclerView;
    private BoardAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<BoardData> list = new ArrayList<>();
    private ArrayList<BoardData> mSearchData = new ArrayList<>();

    private static String IP_ADDRESS = "211.211.158.42/yongrun/svm";
    private static String TAG = "프로필 게시물";
    private String mJsonString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        appData = getActivity().getSharedPreferences("appData", MODE_PRIVATE);

        getSharedLoad();    // user_id, sharedNickname

        txt_profile_name = rootView.findViewById(R.id.txt_profile_name);    // 닉네임
        txt_profile_board_number = rootView.findViewById(R.id.txt_profile_board_number);    //게시글 수

        txt_profile_name.setText(sharedNickname);   // 이거 Shared값 말고 USER NICKNAME값을 가져와야함. 일단 보류.



        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_profile);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.scrollToPosition(0);
        adapter = new BoardAdapter(mSearchData);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //커스텀 리스터 객체 생성 및 전달.
        adapter.setOnItemClickListener(new BoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // TODO : 아이템 클릭 이벤트를 플레그먼트에서 처뤼
                Bundle arguments = new Bundle();

                arguments.putString("userid",mSearchData.get(position).getUserid());      // 글 작성자 ID
                arguments.putString("post_code",mSearchData.get(position).getCode());      // 게시글 코드

                arguments.putString("nickname", mSearchData.get(position).getNickname());  //작성자 닉네임
                arguments.putString("contents", mSearchData.get(position).getContents());  //작성자 글 내용
                arguments.putString("date", mSearchData.get(position).getDate());          //작성자 글 작성 날짜
                arguments.putString("title", mSearchData.get(position).getTitle());        //작성자 글 제목

                arguments.putString("answer", mSearchData.get(position).getAnswercontents()); // 관리자 답변
                arguments.putString("answerdate", mSearchData.get(position).getAnswerdate()); // 관리자 답변 작성 날짜

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                BoardMainFragment boardMainFragment = new BoardMainFragment();
                boardMainFragment.setArguments(arguments);

                transaction.replace(R.id.nav_host_fragment, boardMainFragment).addToBackStack(null).commit();
            }
        });


        return rootView;
    }

    private void getSharedLoad() {
        user_id = appData.getString("ID", "");
        sharedNickname = appData.getString("NICKNAME", "");
        Log.i("Id 가져오나 확인", "onCreateView : "+ user_id);
        Log.i("Nickname 가져오나 확인", "onCreateView : "+ sharedNickname);
    }



    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(), "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d(TAG, "response - " + result);

            if (result == null) {

            } else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = params[1];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "GetData : Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    private void showResult() {

        String TAG_JSON = "POST_DATA";
        String TAG_CODE = "POST_CODE";
        String TAG_TITLE = "POST_TITLE";
        String TAG_NICKNAME = "POST_NICKNAME";
        String TAG_DATE = "POST_DATE";
        String TAG_MANAGER_COMMENT = "POST_MANAGER_COMMENT";
        String TAG_CONTENTS = "POST_CONTENTS";
        String TAG_ANSWER_CONTENTS = "POST_ANSWER_CONTENTS";
        String TAG_ANSWER_DATE = "POST_ANSWER_DATE";
        String TAG_POST_ID = "POST_ID";


        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = jsonArray.length()-1; i>=0; i--) {

                JSONObject item = jsonArray.getJSONObject(i);

                String POST_ID = item.getString(TAG_POST_ID);
                if( POST_ID.equals( user_id ) ){
                    BoardCount++;
                    String POST_CODE = item.getString(TAG_CODE);
                    String POST_TITLE = item.getString(TAG_TITLE);
                    String POST_NICKNAME = item.getString(TAG_NICKNAME);
                    String POST_DATE = item.getString(TAG_DATE);
                    String POST_MANAGER_COMMENT = item.getString(TAG_MANAGER_COMMENT);
                    String POST_CONTENTS = item.getString(TAG_CONTENTS);
                    String POST_ANSWER_CONTENTS = item.getString(TAG_ANSWER_CONTENTS);
                    String POST_ANSWER_DATE = item.getString(TAG_ANSWER_DATE);

                    BoardData boardData = new BoardData();

                    if(POST_MANAGER_COMMENT.equals("0")){
                        POST_MANAGER_COMMENT = "확인안함";
                    }
                    else {
                        POST_MANAGER_COMMENT = "확인됨";
                    }

                    boardData.setCode(POST_CODE); // 게시글 코드
                    boardData.setTitle(POST_TITLE); // 게시글 제목
                    boardData.setNickname(POST_NICKNAME); // 게시글 작성자
                    boardData.setDate(POST_DATE); // 게시글 작성 날짜
                    boardData.setContents(POST_CONTENTS); // 게시글 내용
                    boardData.setUserid(POST_ID);

                    boardData.setManagercomment(POST_MANAGER_COMMENT); // 건의 확인 여부
                    boardData.setAnswercontents(POST_ANSWER_CONTENTS); // 답변 내용
                    boardData.setAnswerdate(POST_ANSWER_DATE); // 답변 작성 날짜

                    mSearchData.add(boardData);

                    adapter.notifyDataSetChanged();

                }
                else {}
            }
            txt_profile_board_number.setText( String.valueOf(BoardCount) );

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    public void PostUpdate() {
        mSearchData.clear();
        adapter.notifyDataSetChanged();
        GetData task = new GetData();
        task.execute("http://" + IP_ADDRESS + "/POST.php", "");
    }

    @Override
    public void onResume() {
        super.onResume();
        BoardCount = 0;
        PostUpdate();
    }



}