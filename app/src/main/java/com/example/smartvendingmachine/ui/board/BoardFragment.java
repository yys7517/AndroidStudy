package com.example.smartvendingmachine.ui.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.smartvendingmachine.R;
import com.example.smartvendingmachine.ui.Home.HomeFragment;

import java.util.ArrayList;

public class BoardFragment extends Fragment {

    private RecyclerView recyclerView;
    private BoardAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<BoardData> list = new ArrayList<>();
    private ArrayList<BoardData> mSearchData = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);

        recyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.scrollToPosition(0);
        adapter = new BoardAdapter(mSearchData);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        swipeRefreshLayout = rootview.findViewById(R.id.refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(false); //새로고침표시 없애기
            }
        });
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    private void initDataset(){
        //테스트
        mSearchData = new ArrayList<>();
        mSearchData.add(new BoardData("용현1", "우유가 안나와요","12월","12시 30분", "확인"));
        mSearchData.add(new BoardData("용현2", "콜라가 뜨거워요", "1월","11시 20분","확인안함"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        mSearchData.add(new BoardData("용현3", "아침햇살 추가해 주세요.", "7월","11시 28분", "확인"));
        
    }
}