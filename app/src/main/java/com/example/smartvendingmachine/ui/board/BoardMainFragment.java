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

    TextView username;
    String TAG = "유저 이름 테스트";

    // Create new fragment and transaction
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);


        //유저 아이디 넘겨주는 코드
        username = rootView.findViewById(R.id.txtCommentNick);
        Intent intent = getIntent();
        String test = intent.getStringExtra("name");
        Log.d(TAG, test);
        username.setText(test);


        /// 뒤로가기 버튼
        backspace = rootView.findViewById(R.id.backspace);

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id., newFragment);
                transaction.addToBackStack(null);
            }
        });

        return rootView;
    }
}