package com.example.codinglayout_twonumbers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> arrayList;
    public LiveData<ArrayList<String>> getArrayList() {
        if (arrayList == null) {
            arrayList = new MutableLiveData<ArrayList<String>>();
            arrayList.setValue(new ArrayList<String>());
        }
        return arrayList;
    }
    public void addListItems(String string) {
        arrayList.getValue().add(string);
    }
    public void removeListItems(int position) {
        arrayList.getValue().remove(position);
    }
    public void editListItems(int position, String s) {
        arrayList.getValue().set(position, s);
    }
}
