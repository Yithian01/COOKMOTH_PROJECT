package com.example.cookmoth_project;

import java.util.ArrayList;
import java.util.List;

public class RecipeData {

    public static List<RECIPEDTO> my_db = new ArrayList<>();
    private static List<Integer> db_IMAGES = new ArrayList<>();
    private static List<String> db_TITLES = new ArrayList<>();
    private static List<String> db_VIEW_COUNTERS = new ArrayList<>();
    private static List<String> db_THUMB_COUNTERS = new ArrayList<>();
    private static List<Boolean> db_IS_LIKES = new ArrayList<>();
    static {
        db_IMAGES.add(R.drawable.pic01);
        db_IMAGES.add(R.drawable.pic02);
        db_IMAGES.add(R.drawable.pic03);
        db_IMAGES.add(R.drawable.pic04);
        db_IMAGES.add(R.drawable.pic05);
        db_IMAGES.add(R.drawable.pic06);
        db_IMAGES.add(R.drawable.pic07);
        db_IMAGES.add(R.drawable.pic08);
        db_IMAGES.add(R.drawable.pic09);
        db_IMAGES.add(R.drawable.pic10);

        db_TITLES.add("mom엄마손맛 계란말이");
        db_TITLES.add("환상의 닭날개구이 레시피");
        db_TITLES.add("금요일은 매콤한 닭볽음탕!!");
        db_TITLES.add("술한잔에는 핵붉닭볶음");
        db_TITLES.add("비빔국수 레시피");
        db_TITLES.add("파닭파닭!!");
        db_TITLES.add("일본식 오꼬노미야끼@@!");
        db_TITLES.add("독일 가정식 햄 만들기");
        db_TITLES.add("가정식 후라이드 치킨 비법");
        db_TITLES.add("아이들이 좋아하는 치킨 만들기");

        db_VIEW_COUNTERS.add("13");
        db_VIEW_COUNTERS.add("45");
        db_VIEW_COUNTERS.add("11");
        db_VIEW_COUNTERS.add("53");
        db_VIEW_COUNTERS.add("78");
        db_VIEW_COUNTERS.add("22");
        db_VIEW_COUNTERS.add("21");
        db_VIEW_COUNTERS.add("77");
        db_VIEW_COUNTERS.add("67");
        db_VIEW_COUNTERS.add("82");

        db_THUMB_COUNTERS.add("9");
        db_THUMB_COUNTERS.add("19");
        db_THUMB_COUNTERS.add("2");
        db_THUMB_COUNTERS.add("40");
        db_THUMB_COUNTERS.add("22");
        db_THUMB_COUNTERS.add("11");
        db_THUMB_COUNTERS.add("3");
        db_THUMB_COUNTERS.add("54");
        db_THUMB_COUNTERS.add("32");
        db_THUMB_COUNTERS.add("66");

        db_IS_LIKES.add(false);
        db_IS_LIKES.add(false);
        db_IS_LIKES.add(true);
        db_IS_LIKES.add(false);
        db_IS_LIKES.add(false);
        db_IS_LIKES.add(true);
        db_IS_LIKES.add(false);
        db_IS_LIKES.add(false);
        db_IS_LIKES.add(false);
        db_IS_LIKES.add(false);

        for(int i = 0 ; i < db_IMAGES.size(); i++){
            my_db.add(new RECIPEDTO(db_IMAGES.get(i), db_TITLES.get(i), db_VIEW_COUNTERS.get(i),
                    db_THUMB_COUNTERS.get(i), db_IS_LIKES.get(i)));
        }

    }

    // 데이터 추가 메서드
    public static void addRecipe(Integer image, String title, String viewCounter, String thumbCounter, boolean isLike) {
        my_db.add(new RECIPEDTO(image, title, viewCounter, thumbCounter, isLike));
    }

    // 데이터 삭제 메서드
    public static void removeRecipe(String title) {
        for( RECIPEDTO i : my_db){
            if(i.getTitle().equals(title)){
                my_db.remove(i);
                return ;
            }
        }
    }

    // 좋아요 제거 메서드
    public static void unlikeRecipe(String title) {
        for (RECIPEDTO i : my_db){
            if (i.getTitle().equals(title)){
                i.setLike(false);
                return ;
            }
        }
    }

    public static void likeRecipe(Integer image, String title, String viewCounter, String thumbCounter, boolean isLike) {
        for (RECIPEDTO i : my_db){
            if (i.getTitle().equals(title)){
                i.setLike(true);
                return ;
            }
        }
    }



}
