package com.luismolina.acromine.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DictionaryViewModel extends AndroidViewModel {

    private MutableLiveData<List<Dictionary.Word>> mDictionary;

    public DictionaryViewModel(Application application) {
        super(application);
        mDictionary = new MutableLiveData<>();
    }

    public LiveData<List<Dictionary.Word>> getDictionaryLiveData() {
        return mDictionary;
    }

    public void loadDictionary(List<Dictionary> newDictionary) {
        mDictionary.setValue(newDictionary.get(0).lfs);
    }

    public void cleanDictionary() {
        mDictionary.setValue(new ArrayList<>());
    }
}
