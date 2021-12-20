package org.izv.omc.practicajuegosdeintacdat.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import org.izv.omc.practicajuegosdeintacdat.model.repository.GameRepository;

public class CommonViewModel extends ViewModel {

    private Context context;
    private GameRepository repository;

    public CommonViewModel(){
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
