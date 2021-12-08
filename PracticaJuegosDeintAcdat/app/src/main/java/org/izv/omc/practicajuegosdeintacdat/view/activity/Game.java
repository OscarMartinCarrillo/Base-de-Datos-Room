package org.izv.omc.practicajuegosdeintacdat.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.omc.practicajuegosdeintacdat.R;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.GameCategory;
import org.izv.omc.practicajuegosdeintacdat.view.adapter.GameAdapter;
import org.izv.omc.practicajuegosdeintacdat.viewmodel.CategoryViewModel;
import org.izv.omc.practicajuegosdeintacdat.viewmodel.GameViewModel;

import java.util.List;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        GameViewModel gvm = new ViewModelProvider(this).get(GameViewModel.class);
        org.izv.omc.practicajuegosdeintacdat.model.entity.Game game = new org.izv.omc.practicajuegosdeintacdat.model.entity.Game();
        game.name = "Juego de mesa 1";
        game.idcategory = 2;
        game.difficulty = 1;
        game.duration = 90;
        game.players = 2;
        gvm.insertGame2(game);

        CategoryViewModel cvm = new ViewModelProvider(this).get(CategoryViewModel.class);
        Category c = new Category();
        c.name="Prueba";
        c.url = "cualquiera";

        //gvm.insertGame(game, c);
        //lista de jeugos
        RecyclerView rvGame = findViewById(R.id.rvGame);
        rvGame.setLayoutManager(new LinearLayoutManager(this));


        GameAdapter gameAdapter = new GameAdapter(this);



        rvGame.setAdapter(gameAdapter);

        //LiveData<List<Game>> listaGame = pvm.getGames();
        LiveData<List<GameCategory>> listaGameCategory = gvm.getAllGame();
        listaGameCategory.observe(this, games -> {
            gameAdapter.setGameList(games);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddGame);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateGame.class);
            startActivity(intent);
        });
    }
}