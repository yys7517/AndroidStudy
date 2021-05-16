package com.example.smartvendingmachine.ui.board;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartvendingmachine.R;

public class BoardMainFragment extends Fragment {

    private ImageView backspace;

    private String snickname;
    private String scontents;
    private String sdate;
    private String stitle;
    private String sanswer;
    private String sanswerdate;
    private String suserid;

    //작성자 글
    private TextView nickname;
    private TextView contents;
    private TextView date;
    private TextView title;

    //관리자 글
    private TextView ManagerName;
    private TextView answer;
    private TextView answerdate;
    ImageView imgManagerProfile;

    private TextView BtnEdit, BtnDelete;

    //SharedPreferences
    private SharedPreferences appData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_boardmain, container, false);


        //작성자
        nickname = rootView.findViewById(R.id.txtCommentNick);
        contents = rootView.findViewById(R.id.txtCommentContents);
        date = rootView.findViewById(R.id.txtCommentDate);
        title = rootView.findViewById(R.id.txtCommentTitle);

        //관리자
        answer = rootView.findViewById(R.id.txtManagerComments);
        answerdate = rootView.findViewById(R.id.txtManagerCommentDate);
        imgManagerProfile = rootView.findViewById(R.id.imgManagerProfile);
        ManagerName = rootView.findViewById(R.id.ManagerName);

        //수정/삭제

        BtnDelete = rootView.findViewById(R.id.BtnDelete);
        BtnEdit = rootView.findViewById(R.id.BtnEdit);


        //SharedPreferences
        appData = getActivity().getSharedPreferences("appData", Context.MODE_PRIVATE);

        String userid = appData.getString("ID", ""); // App 사용자 ID

        if(getArguments() != null)
        {
            suserid = getArguments().getString("userid"); // 글 작성자 ID

            snickname = getArguments().getString("nickname"); // 작성자
            scontents = getArguments().getString("contents"); // 건의 내용
            sdate = getArguments().getString("date"); // 건의 작성 날짜
            stitle = getArguments().getString("title"); // 건의 글 제목

            sanswer = getArguments().getString("answer"); // 답변
            sanswerdate = getArguments().getString("answerdate"); // 답변 날짜

            nickname.setText(snickname);
            contents.setText(scontents);
            date.setText(sdate);
            title.setText(stitle);

            if( ! ( sanswer.equals("null") || sanswer.equals("") ) )
            {
                answer.setVisibility(View.VISIBLE);
                answerdate.setVisibility(View.VISIBLE);
                imgManagerProfile.setVisibility(View.VISIBLE);
                ManagerName.setVisibility(View.VISIBLE);

                answer.setText(sanswer);
                answerdate.setText(sanswerdate);
            }
            else {
                answer.setVisibility(View.VISIBLE);
                answer.setText("답변 내용이 없습니다. 빠른 시일내에 답변 드리겠습니다.");
                answer.setTypeface(null, Typeface.BOLD_ITALIC);
            }

        }

        //수정
        BtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(suserid.equals(userid)) {
                    // App 사용자 ID와 글 작성자 ID가 일치
                    Toast.makeText(getActivity().getApplicationContext(), "게시글을 수정합니다.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                    intent.putExtra("title",stitle);    // 제목
                    intent.putExtra("contents",scontents);
                    startActivity(intent);
                }
                else {
                    // App 사용자 ID와 글 작성자 ID가 일치 X
                    Toast.makeText(getActivity().getApplicationContext(), "이 게시물을 수정할 수 없습니다.",Toast.LENGTH_SHORT).show();
                }


            }
        });

        //삭제
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(suserid.equals(userid)) {
                    // App 사용자 ID와 글 작성자 ID가 일치

                }
                else {
                    // App 사용자 ID와 글 작성자 ID가 일치 X

                }
            }
        });


        /// 뒤로가기 버튼
        backspace = rootView.findViewById(R.id.backspace);

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                BoardFragment boardFragment = new BoardFragment();

                transaction.replace(R.id.nav_host_fragment,boardFragment).commit();
            }
        });

        return rootView;
    }

}