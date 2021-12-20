package org.izv.omc.practicajuegosdeintacdat.view.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.datepicker.MaterialDatePicker;

import org.izv.omc.practicajuegosdeintacdat.R;
import org.izv.omc.practicajuegosdeintacdat.databinding.FragmentCreateGameBinding;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.viewmodel.CategoryViewModel;
import org.izv.omc.practicajuegosdeintacdat.viewmodel.GameViewModel;

public class createGame extends Fragment {
    private FragmentCreateGameBinding binding;
    private Game game;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(createGame.this)
                        .navigate(R.id.action_createGame_to_main);
            }
        });
        cargarImagen();
        dataPicker();

        if(MainActivity.edit){
            MainActivity.edit=false;
            initializeEdit();
        }else{
            initialize();
        }

    }

    private void dataPicker() {
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Selecicone la fecha");
        final MaterialDatePicker materialDatePicker = builder.build();

        FragmentManager fragManager = getFragmentManager() ;

        binding.btDataPicker.setOnClickListener(view -> materialDatePicker.show(fragManager, "DATE_PICKER"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> binding.tietReleaseEdit.setText(materialDatePicker.getHeaderText()));
    }

    private void cargarImagen() {
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    Glide.with(getActivity()).load(uri).into(binding.ivImage);
                    binding.tietUrl.setText(String.valueOf(uri));
                });

        binding.ivImage.setOnClickListener(view1 -> mGetContent.launch("image/*"));

        binding.btSubirFoto.setOnClickListener(view12 -> mGetContent.launch("image/*"));

    }

    //Confirmacion
    private void showDialog(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        GameViewModel gvm = new ViewModelProvider(this).get(GameViewModel.class);
        builder.setTitle(title)
                .setMessage(message)
                .setNegativeButton("cancel", (dialogInterface, wich) -> {
                    //Si dice que no, no hago nada
                    Toast.makeText(getContext(),"No se ha eliminado", Toast.LENGTH_LONG).show();
                })
                .setPositiveButton("ok", (dialogInterface, wich) -> {
                    Toast.makeText(getContext(),"Eliminado", Toast.LENGTH_LONG).show();
                    gvm.deleteGames(game);
                    NavHostFragment.findNavController(this).popBackStack();
                });

        builder.create().show();

    }

    private void initialize() {
        CategoryViewModel cvm = new ViewModelProvider(this).get(CategoryViewModel.class);
        cvm.getCategories().observe(getViewLifecycleOwner(), categories -> {
            Category category = new Category();
            category.id = 0;
            category.name = getString(R.string.default_category);
            categories.add(0, category);
            ArrayAdapter<Category> adapter =
                    new ArrayAdapter<Category>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);
            binding.spCategories.setAdapter(adapter);
        });

        GameViewModel gvm = new ViewModelProvider(this).get(GameViewModel.class);

        binding.btAdd.setOnClickListener(view -> {
            if (binding.tietName.getText().toString().isEmpty() || binding.tietPlayer.getText().toString().isEmpty() ||
                    binding.tietPlayer.getText().toString().isEmpty() || binding.tietDuration.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Campos vacios", Toast.LENGTH_LONG).show();
            }else {
                if (binding.spCategories.getSelectedItemPosition()!=0){
                    Game g = getGameCreate();
                    if (g.isValid()) {
                        gvm.insertGame(g);
                        NavHostFragment.findNavController(this).popBackStack();
                    }else{
                        Toast.makeText(getContext(), "No es valido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Selecciona una categoria", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initializeEdit() {
        binding.btAdd.setText("Update");
        binding.btDelete.setVisibility(View.VISIBLE);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        game = bundle.getParcelable("game");
        binding.tietName.setText(game.name);
        binding.spDifficulty.setSelection(game.difficulty);
        binding.tietDuration.setText(String.valueOf(game.duration));
        binding.tietPlayer.setText(String.valueOf(game.players));
        binding.tietReleaseEdit.setText(game.releaseDate);
        binding.tietUrl.setText(game.url);
        Glide.with(this).load(game.url).into(binding.ivImage);

        GameViewModel gvm = new ViewModelProvider(this).get(GameViewModel.class);

        CategoryViewModel cvm = new ViewModelProvider(this).get(CategoryViewModel.class);
        cvm.getCategories().observe(getViewLifecycleOwner(), categories -> {
            ArrayAdapter<Category> adapter =
                    new ArrayAdapter<Category>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);
            binding.spCategories.setAdapter(adapter);
            categories.forEach((c)->{
                if(c.id == game.idcategory){
                    int pos = categories.indexOf(c);
                    binding.spCategories.setSelection(pos);
                }
            });
        });

        binding.btAdd.setOnClickListener(view -> {
            if (binding.tietName.getText().toString().isEmpty() || binding.tietPlayer.getText().toString().isEmpty() ||
                    binding.tietPlayer.getText().toString().isEmpty() || binding.tietDuration.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Campos vacios", Toast.LENGTH_LONG).show();
            }else {
                if (binding.spCategories.getSelectedItemPosition()!=0){
                    Game g = getGameEdit(game.id);
                    if (g.isValid()) {
                        gvm.updateGame(g);
                        NavHostFragment.findNavController(this).popBackStack();
                    }else{
                        Toast.makeText(getContext(), "No es valido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Selecciona una categoria", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.btDelete.setOnClickListener(view -> {
            showDialog("Eliminar", "Desea eliminar");
        });
    }

    private Game getGameEdit(Long id) {
        String name = binding.tietName.getText().toString().trim();
        int players = Integer.parseInt(binding.tietPlayer.getText().toString().trim());
        int difficulty = (int) binding.spDifficulty.getSelectedItemId();
        int duration = Integer.parseInt(binding.tietDuration.getText().toString().trim());
        String releaseDate = binding.tietReleaseEdit.getText().toString().trim();
        String url = binding.tietUrl.getText().toString();

        Category category = (Category) binding.spCategories.getSelectedItem();
        Game game = new Game();
        game.id = id;
        game.idcategory = category.id;
        game.name = name;
        game.difficulty = difficulty;
        game.url = url;
        game.duration = duration;
        game.players = players;
        game.releaseDate=releaseDate;



        return game;
    }

    private Game getGameCreate() {
        String name = binding.tietName.getText().toString().trim();
        int players = Integer.parseInt(binding.tietPlayer.getText().toString().trim());
        int difficulty = (int) binding.spDifficulty.getSelectedItemId();
        int duration = Integer.parseInt(binding.tietDuration.getText().toString().trim());
        String releaseDate = binding.tietReleaseEdit.getText().toString().trim();
        String url = binding.tietUrl.getText().toString();

        Category category = (Category) binding.spCategories.getSelectedItem();
        Game game = new Game();
        game.idcategory = category.id;
        game.name = name;
        game.difficulty = difficulty;
        game.url = url;
        game.duration = duration;
        game.players = players;
        game.releaseDate=releaseDate;

        return game;
    }
}