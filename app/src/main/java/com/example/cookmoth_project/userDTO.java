package com.example.cookmoth_project;

public class userDTO {

    private String userID;

    private String password;

    private String NickName;

    public userDTO() {
        this.userID = "";
        this.password = "";
        this.NickName = "";
    }

    public userDTO(String userID, String password, String nickName) {
        this.userID = userID;
        this.password = password;
        NickName = nickName;
    }


    public void signUp(String id, String pwd, String nickName){
        setUserID(id);
        setPassword(pwd);
        setNickName(nickName);
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }
}
