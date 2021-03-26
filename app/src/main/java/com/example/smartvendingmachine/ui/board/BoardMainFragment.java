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

    private String nickname;
    private TextView username;
    String TAG = "유저 이름 테스트";

    // Create new fragment and transaction
    FragmentManager fragmentManager = getFragmentManager();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_boardmain, container, false);

        username = rootView.findViewById(R.id.txtCommentNick);

        if(getArguments() != null)
        {
            nickname = getArguments().getString("name");
            username.setText(nickname);
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