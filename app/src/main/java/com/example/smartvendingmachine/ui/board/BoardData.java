package com.example.smartvendingmachine.ui.board;

import android.widget.ImageView;

public class BoardData {
    //ImageView profile;
    private String nickname;
    private String contents;
    private String date;
    private String time;
    private String manager;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    //프로필 이미지는 넣지 않았음
    public BoardData(String nickname, String contents,String date,String time, String manager){
        this.nickname = nickname;
        this.contents = contents;
        this.date = date;
        this.time = time;
        this.manager = manager;
    }
}
