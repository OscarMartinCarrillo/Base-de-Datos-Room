package org.izv.omc.practicajuegosdeintacdat.model.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.model.entity.GameCategory;
import org.izv.omc.practicajuegosdeintacdat.model.room.GameDao;
import org.izv.omc.practicajuegosdeintacdat.model.room.GameDatabase;

import java.util.HashMap;
import java.util.List;

public class GameRepository {
    private static final String INIT = "init";

    private HashMap<String, String> gameMap;
    private GameDao dao;
    private LiveData<List<GameCategory>> allGame;
    private LiveData<List<Game>> liveGames;
    private LiveData<List<Category>> liveCategories;
    private LiveData<Game> liveGame;
    private LiveData<Category> liveCategory;
    private MutableLiveData<Long> liveInsertResult;
    private MutableLiveData<List<Long>> liveInsertResults;
    private SharedPreferences preferences;

    public GameRepository(Context context) {
        GameDatabase db = GameDatabase.getDatabase(context);
        gameMap = new HashMap<>();
        dao = db.getDao();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        liveInsertResult = new MutableLiveData<>();
        liveInsertResults = new MutableLiveData<>();
        if (!getInit()) {
            categoriesPreload();
            setInit();
        }
    }

    public void insertGame(Game game, Category category) {
        Runnable r = () -> {
            game.idcategory = insertCategoryGetId(category);
            if (game.idcategory > 0) {
                dao.insertGame(game);
            }
        };
        new Thread(r).start();
    }

    public MutableLiveData<Long> getInsertResult() {
        return liveInsertResult;
    }
/*
    public MutableLiveData<String> getKalosResult() {
        return liveGetKalosResult;
    }

 */

    public MutableLiveData<List<Long>> getInsertResults() {
        return liveInsertResults;
    }

    private long insertCategoryGetId(Category category) {
        List<Long> ids = dao.insertCategory(category);
        if (ids.get(0) < 1) {
            return dao.getIdCategory(category.name);
        } else {
            return ids.get(0);
        }
    }

    public void insertGame(Game... games) {
        Runnable r = () -> {
            List<Long> resultList = dao.insertGame(games);
            liveInsertResult.postValue(resultList.get(0));
            liveInsertResults.postValue(resultList);
        };
        new Thread(r).start();
    }

    public void insertCategory(Category... categories) {
        Runnable r = () -> {
            dao.insertCategory(categories);
        };
        new Thread(r).start();
    }

    public void updateGame(Game... games) {
        Runnable r = () -> {
            dao.updateGame(games);
        };
        new Thread(r).start();
    }

    public void updateCategory(Category... categories) {
        Runnable r = () -> {
            dao.updateCategory(categories);
        };
        new Thread(r).start();
    }

    public void deleteGames(Game... games) {
        Runnable r = () -> {
            dao.deleteGames(games);
        };
        new Thread(r).start();
    }

    public void deleteCategory(Category... categories) {
        Runnable r = () -> {
            dao.deleteCategory(categories);
        };
        new Thread(r).start();
    }

    public void deleteAllCategories() {
        Runnable r = () -> {
            dao.deleteAllCategories();
        };
        new Thread(r).start();
    }

    public void deleteAllGames() {
        Runnable r = () -> {
            dao.deleteAllGame();
        };
        new Thread(r).start();
    }

    public LiveData<List<Game>> getGames() {
        if (liveGames == null) {
            liveGames = dao.getGames();
        }
        return liveGames;
    }

    public LiveData<List<Category>> getCategories() {
        if (liveCategories == null) {
            liveCategories = dao.getCategories();
        }
        return liveCategories;
    }

    public LiveData<Game> getGame(long id) {
        if (liveGame == null) {
            liveGame = dao.getGame(id);
        }
        return liveGame;
    }

    public LiveData<Category> getCategory(long id) {
        if (liveCategory == null) {
            liveCategory = dao.getCategory(id);
        }
        return liveCategory;
    }

    public LiveData<List<GameCategory>> getAllGame() {
        if (allGame == null) {
            allGame = dao.getAllGame();
        }
        return allGame;
    }

    public void categoriesPreload() {
        String[] categoryName = new String[]{"Normal", "Eurogame", "Ameritrash", "Fillers", "Cooperativos", "Miniaturas", "Roles ocultos", "Para dos", "Solitario", "WarGames"};
        Category[] categories = new Category[categoryName.length];
        Category category;
        int cont = 0;
        for (String s : categoryName) {
            category = new Category();
            category.name = s;
            categories[cont] = category;
            cont++;
        }
        insertCategory(categories);
        gamesPreload();
    }

    public void gamesPreload(){
        Game g = new Game();
        g.name="Monopoli";
        g.releaseDate="Dec 16, 2021";
        g.players=1;
        g.duration=1;
        g.difficulty=1;
        g.idcategory=2;
        insertGame(g);
        g = new Game();
        g.name="Parchis";
        g.releaseDate="Dec 26, 2020";
        g.players=2;
        g.duration=2;
        g.difficulty=2;
        g.idcategory=3;
        insertGame(g);
    }

    public void setInit() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(INIT, true);
        editor.apply();
    }

    public boolean getInit() {
        return preferences.getBoolean(INIT, false);
    }

}
