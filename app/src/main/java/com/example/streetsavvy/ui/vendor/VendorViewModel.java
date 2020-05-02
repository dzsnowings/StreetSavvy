package com.example.streetsavvy.ui.vendor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VendorViewModel extends ViewModel {

    public MutableLiveData<String> mText;

    public VendorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Vendor View");
    }

    public LiveData<String> getText() {
        return mText;
    }
}