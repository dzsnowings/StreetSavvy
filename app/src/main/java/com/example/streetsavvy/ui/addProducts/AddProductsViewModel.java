package com.example.streetsavvy.ui.addProducts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddProductsViewModel extends ViewModel {

    public MutableLiveData<String> mText;

    public AddProductsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add Products");
    }

    public LiveData<String> getText() {
        return mText;
    }
}