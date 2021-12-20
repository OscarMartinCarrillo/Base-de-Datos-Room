package org.izv.omc.practicajuegosdeintacdat.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.izv.omc.practicajuegosdeintacdat.R;
import org.izv.omc.practicajuegosdeintacdat.databinding.FragmentMainBinding;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.model.entity.GameCategory;
import org.izv.omc.practicajuegosdeintacdat.view.adapter.CategoryAdapter;
import org.izv.omc.practicajuegosdeintacdat.view.adapter.GameAdapter;
import org.izv.omc.practicajuegosdeintacdat.viewmodel.CategoryViewModel;
import org.izv.omc.practicajuegosdeintacdat.viewmodel.GameViewModel;

import java.util.List;

public class Main extends Fragment {
    private FragmentMainBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createRVGames();
        createRVCategories();

        binding.categoryCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Main.this)
                        .navigate(R.id.action_main_to_createCategory);
            }
        });


        binding.cvGameCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Main.this)
                        .navigate(R.id.action_main_to_createGame);
            }
        });

    }

    private void test() {
        GameViewModel gvm = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        LiveData<List<GameCategory>> lista = gvm.getAllGame();
        lista.observe(getViewLifecycleOwner(), games -> {
            System.out.println(games);
        });
        CategoryViewModel cvm = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        LiveData<List<Category>> lista2 = cvm.getCategories();
        lista2.observe(getViewLifecycleOwner(), c -> {
            System.out.println(c);
        });
    }


    private void createRVCategories() {
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        CategoryViewModel cvm = new ViewModelProvider(this).get(CategoryViewModel.class);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext());

        binding.rvCategory.setAdapter(categoryAdapter);

        LiveData<List<Category>> listaCategory = cvm.getCategories();
        /*
        if(listaCategory.getValue().isEmpty()){
            String img_add = "https://pic.onlinewebfonts.com/svg/img_511045.png";

            Category c = new Category();
            c.name="Crear";
            c.id=0;
            c.url=img_add;
            cvm.insertCategory(c);
            listaCategory = cvm.getCategories();
        }

         */
        listaCategory.observe(getViewLifecycleOwner(), categories -> {
            categoryAdapter.setCategoryList(categories);
        });
    }

    private void createRVGames() {
        binding.rvGame.setLayoutManager(new LinearLayoutManager(getContext()));

        GameViewModel gvm = new ViewModelProvider(this).get(GameViewModel.class);
        GameAdapter gameAdapter = new GameAdapter(getContext());

        binding.rvGame.setAdapter(gameAdapter);

        LiveData<List<GameCategory>> listaGameCategory = gvm.getAllGame();

        /*
        if(listaGameCategory.getValue().isEmpty()){
            String img_add = "https://pic.onlinewebfonts.com/svg/img_511045.png";

            Game g = new Game();
            g.name="Crear";
            g.id=0;
            g.releaseDate="null";
            g.players=1;
            g.duration=1;
            g.difficulty=1;
            g.idcategory=0;
            g.url=img_add;
            gvm.insertGame(g);
        }

         */

        listaGameCategory.observe(getViewLifecycleOwner(), games -> {
            gameAdapter.setGameList(games);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
