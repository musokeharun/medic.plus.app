package com.bms.medicplus.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bms.medicplus.models.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Category>> liveDataCategories;

    public LiveData<List<Category>> getCategories() {
        if (liveDataCategories == null) {
            liveDataCategories = new MutableLiveData<>();
            loadCategories();
        }
        return liveDataCategories;
    }

    private void loadCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Cardiologist", "https://image.flaticon.com/icons/png/512/508/508786.png"));
        // Do an asynchronous operation to fetch users.
        liveDataCategories.postValue(categories);
    }

}