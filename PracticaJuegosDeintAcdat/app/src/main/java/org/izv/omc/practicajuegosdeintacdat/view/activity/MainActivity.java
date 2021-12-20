package org.izv.omc.practicajuegosdeintacdat.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.izv.omc.practicajuegosdeintacdat.R;
import org.izv.omc.practicajuegosdeintacdat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static boolean edit = false;
    public static String[] difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        difficulty = getResources().getStringArray(R.array.difficulty);

        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Juegos de Mesa");

    }
}
