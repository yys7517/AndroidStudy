package com.example.smartvendingmachine.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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


    private static String IP_ADDRESS = "211.211.158.42/yongrun/svm";
    private static String TAG = "phptest";

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

        //커스텀 리스터 객체 생성 및 전달.
        adapter.setOnItemClickListener(new BoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // TODO : 아이템 클릭 이벤트를 플레그먼트에서 처뤼
                Bundle arguments = new Bundle();
                arguments.putString("nickname", mSearchData.get(position).getNickname());
                arguments.putString("contents", mSearchData.get(position).getContents());
                arguments.putString("date", mSearchData.get(position).getDate());
                arguments.putString("title", mSearchData.get(position).getTitle());
                arguments.putString("answer", mSearchData.get(position).getAnswercontents());
                arguments.putString("answerdate", mSearchData.get(position).getAnswerdate());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                BoardMainFragment boardMainFragment = new BoardMainFragment();
                boardMainFragment.setArguments(arguments);
                transaction.replace(R.id.nav_host_fragment, boardMainFragment).commit();
            }
        });

        swipeRefreshLayout = rootview.findViewById(R.id.refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //데이터 새로고침 코드 넣어야 함.
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

    private void initDataset() {
        //테스트
        mSearchData = new ArrayList<>();
        mSearchData.add(new BoardData("1", "우유가 안나와요", "용현1", "우유가 안나와요1", "2021-03-15 14:46:16", "확인함", "킹송합니다1", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("2", "콜라가 뜨거워요", "용현2", "우유가 안나와요2", "2021-03-15 14:46:16", "확인함", "킹송합니다2", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("3", "아침햇살 추가해 주세요1.", "용현3", "우유가 안나와요3", "2021-03-15 14:46:16", "확인함", "킹송합니다3", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("4", "아침햇살 추가해 주세요2.", "용현4", "우유가 안나와요4", "2021-03-15 14:46:16", "확인함", "킹송합니다4", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("5", "아침햇살 추가해 주세요3.", "용현5", "우유가 안나와요5", "2021-03-15 14:46:16", "확인함", "킹송합니다5", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("6", "아침햇살 추가해 주세요4.", "용현6", "우유가 안나와요6", "2021-03-15 14:46:16", "확인함", "킹송합니다6", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("7", "아침햇살 추가해 주세요5.", "용현7", "우유가 안나와요7", "2021-03-15 14:46:16", "확인함", "킹송합니다7", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("8", "아침햇살 추가해 주세요6.", "용현8", "우유가 안나와요8", "2021-03-15 14:46:16", "확인함", "킹송합니다8", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("9", "아침햇살 추가해 주세요7.", "용현9", "우유가 안나와요9", "2021-03-15 14:46:16", "확인함", "킹송합니다9", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("10", "아침햇살 추가해 주세요8.", "용현10", "우유가 안나와요10", "2021-03-15 14:46:16", "확인함", "킹송합니다10", "2021-03-15 14:46:16"));
        mSearchData.add(new BoardData("11", "아침햇살 추가해 주세요9.", "용현11", "우유가 안나와요11", "2021-03-15 14:46:16", "확인함", "킹송합니다11", "2021-03-15 14:46:16"));

    }
}