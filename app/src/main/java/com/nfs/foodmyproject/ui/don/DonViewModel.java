package com.nfs.foodmyproject.ui.don;

import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DonViewModel extends ViewModel {
    private final MutableLiveData<TextView> ammountTextView;

    public DonViewModel() {
        ammountTextView = new MutableLiveData<>();
    }

    public MutableLiveData<TextView> getAmmountTextView() {
        return ammountTextView;
    }
}
