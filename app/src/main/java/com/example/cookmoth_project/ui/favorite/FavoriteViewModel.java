package com.example.cookmoth_project.ui.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoriteViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FavoriteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("즐겨찾기 화면입니다.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}