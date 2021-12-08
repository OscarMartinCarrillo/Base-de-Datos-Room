package org.izv.omc.practicajuegosdeintacdat.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.repository.GameRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private GameRepository repository;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
    }

    public void insertCategory(Category... categories) {
        repository.insertCategory(categories);
    }

    public void updateCategory(Category... categories) {
        repository.updateCategory(categories);
    }

    public void deleteCategory(Category... categories) {
        repository.deleteCategory(categories);
    }

    public LiveData<List<Category>> getCategories() {
        return repository.getCategories();
    }

    public LiveData<Category> getCategory(long id) {
        return repository.getCategory(id);
    }
}
