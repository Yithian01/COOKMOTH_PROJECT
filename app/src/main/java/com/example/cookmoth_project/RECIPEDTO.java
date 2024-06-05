package com.example.cookmoth_project;

public class RECIPEDTO {

    private Integer img;
    private String title;
    private String vCnt;
    private String tCnt;
    private boolean isLike;


    public RECIPEDTO(Integer img, String title, String vCnt, String tCnt, boolean isLike){
        this.img = img;
        this.title = title;
        this.vCnt = vCnt;
        this.tCnt = tCnt;
        this.isLike = isLike;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getvCnt() {
        return vCnt;
    }

    public void setvCnt(String vCnt) {
        this.vCnt = vCnt;
    }

    public String gettCnt() {
        return tCnt;
    }

    public void settCnt(String tCnt) {
        this.tCnt = tCnt;
    }

    public boolean isIsLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }


}
