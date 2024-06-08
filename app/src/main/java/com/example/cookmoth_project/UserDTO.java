package com.example.cookmoth_project;

public class UserDTO {
    private String userID, password, NickName;

    public UserDTO() {
        this.userID = "";
        this.password = "";
        this.NickName = "";
    }

    public void signUp(String id, String pwd, String nickName){
        setUserID(id);
        setPassword(pwd);
        setNickName(nickName);
    }
    public String getUserID() { return userID; }

    public void setUserID(String userID) { this.userID = userID; }

    public String getPassword() {return password; }

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
