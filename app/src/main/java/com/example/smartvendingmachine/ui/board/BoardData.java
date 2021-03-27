package com.example.smartvendingmachine.ui.board;

import android.widget.ImageView;

public class BoardData {
    //ImageView profile;
    //게시물 ID
    private String code; //기본키
    private String title;
    private String nickname;
    private String contents; //내용
    private String date;
    private String answercontents;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getAnswercontents() {
        return answercontents;
    }

    public void setAnswercontents(String answercontents) {
        this.answercontents = answercontents;
    }

    //프로필 이미지는 넣지 않았음
    public BoardData(String code, String title, String nickname, String contents, String date, String answercontents) {
        this.code = code;
        this.title = title;
        this.nickname = nickname;
        this.contents = contents;
        this.date = date;
        this.answercontents = answercontents;
    }
}


















//





























