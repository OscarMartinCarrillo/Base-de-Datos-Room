package org.izv.omc.practicajuegosdeintacdat.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.model.entity.GameCategory;
import org.izv.omc.practicajuegosdeintacdat.model.repository.GameRepository;

import java.util.List;

public class GameViewModel extends AndroidViewModel {

    private GameRepository repository;

    public GameViewModel(@NonNull Application application) {
        super(application);
    }

    public void updateGame(Game... games) {
        repository.updateGame(games);
    }

    public void deleteGames(Game... games) {
        repository.deleteGames(games);
    }

    public LiveData<List<Game>> getGames() {
        return repository.getGames();
    }

    public LiveData<Game> getGame(long id) {
        return repository.getGame(id);
    }

    public void insertGame(Game game, Category category) {
        repository.insertGame(game, category);
    }

    public void insertGame(Game... games){
        repository.insertGame(games);
    }

    public void insertGame2(Game game){
        repository.insertGame2(game);
    }

    public LiveData<List<GameCategory>> getAllGame() {
        return repository.getAllGame();
    }

    public MutableLiveData<Long> getInsertResult() {
        return repository.getInsertResult();
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return repository.getInsertResults();
    }

    public String getUrl(String gameName) {
        return repository.getUrl(gameName);
    }
}
