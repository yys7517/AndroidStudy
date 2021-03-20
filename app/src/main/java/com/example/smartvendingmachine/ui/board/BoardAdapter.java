package com.example.smartvendingmachine.ui.board;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartvendingmachine.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<BoardData> iData;
    private Activity context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView profile;
        protected TextView nickname;
        protected TextView contents;
        protected TextView date;
        protected TextView time;
        protected TextView manager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조.(Hold Strong reference)
            //profile = itemView.findViewById(R.id.imgCommentProfile);
            this.nickname = itemView.findViewById(R.id.txtCommentNick);
            this.contents = itemView.findViewById(R.id.txtCommentContents);
            this.date = itemView.findViewById(R.id.txtCommentDate);
            this.time = itemView.findViewById(R.id.txtCommentTime);
            this.manager = itemView.findViewById(R.id.txtCommentmanager);
        }
    }

    //생성자에서 데이터 리스트 객체를 전달받음.
    public BoardAdapter(Activity context, ArrayList<BoardData> list) {
        this.context = context;
        this.iData = list;

    }

    @NonNull
    //아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_notice_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemposition = position;
    }

    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return iData.size();
    }
}
