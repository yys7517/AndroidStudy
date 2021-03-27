package com.example.smartvendingmachine.ui.board;

import android.content.Intent;
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

import com.example.smartvendingmachine.R;

public class BoardMainFragment extends Fragment {

    private ImageView backspace;

    private String snickname;
    private String scontents;
    private String sdate;
    private String stitle;
    private String sanswer;
    private String sanswerdate;

    private TextView nickname;
    private TextView contents;
    private TextView date;
    private TextView title;
    private TextView answer;
    private TextView answerdate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_boardmain, container, false);

        nickname = rootView.findViewById(R.id.txtCommentNick);
        contents = rootView.findViewById(R.id.txtCommentContents);
        date = rootView.findViewById(R.id.txtCommentDate);
        title = rootView.findViewById(R.id.txtCommentTitle);
        answer = rootView.findViewById(R.id.txtManagerComments);
        answerdate = rootView.findViewById(R.id.txtManagerCommentDate);

        if(getArguments() != null)
        {
            snickname = getArguments().getString("nickname");
            scontents = getArguments().getString("contents");
            sdate = getArguments().getString("date");
            stitle = getArguments().getString("title");
            sanswer = getArguments().getString("answer");
            sanswerdate = getArguments().getString("answerdate");

            nickname.setText(snickname);
            contents.setText(scontents);
            date.setText(sdate);
            title.setText(stitle);
            answer.setText(sanswer);
            answerdate.setText(sanswerdate);
        }


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