package com.example.smartvendingmachine.ui.board;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartvendingmachine.R;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    final private String TAG1 = "테스트중이에요옹";

    private ArrayList<BoardData> iData;
    //리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    //OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메소드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView profile;
        public TextView nickname;
        public TextView contents;
        public TextView date;
        public TextView time;
        public TextView manager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조.(Hold Strong reference)
            //profile = itemView.findViewById(R.id.imgCommentProfile);
            nickname = itemView.findViewById(R.id.txtCommentNick);
            contents = itemView.findViewById(R.id.txtCommentContents);
            date = itemView.findViewById(R.id.txtCommentDate);
            time = itemView.findViewById(R.id.txtCommentTime);
            manager = itemView.findViewById(R.id.txtCommentmanager);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 참조.
                        if (mListener != null) {
                            mListener.onItemClick(view, pos);
                            Log.d(TAG1, "onClick: "+pos+"번째");

                        }
                    }
                }
            });
        }
    }

    //생성자에서 데이터 리스트 객체를 전달받음.
    public BoardAdapter(ArrayList<BoardData> mSearchData) {
        this.iData = mSearchData;
    }

    @NonNull
    //아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_notice_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.nickname.setText(iData.get(position).getNickname());
        holder.contents.setText(iData.get(position).getContents());
        holder.date.setText(iData.get(position).getDate());
        holder.time.setText(iData.get(position).getTime());
        holder.manager.setText(iData.get(position).getManager());


    }

    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return iData.size();
    }
}
