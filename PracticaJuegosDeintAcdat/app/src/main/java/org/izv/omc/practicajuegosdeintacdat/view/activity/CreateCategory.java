package org.izv.omc.practicajuegosdeintacdat.view.activity;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.izv.omc.practicajuegosdeintacdat.R;
import org.izv.omc.practicajuegosdeintacdat.databinding.FragmentCreateCategoryBinding;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.viewmodel.CategoryViewModel;
import org.izv.omc.practicajuegosdeintacdat.viewmodel.GameViewModel;

public class CreateCategory extends Fragment {
    private FragmentCreateCategoryBinding binding;
    private Category category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btCancelCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CreateCategory.this)
                        .navigate(R.id.action_createCategory_to_main);
            }
        });

        cargarImagen();
        if(MainActivity.edit){
            MainActivity.edit=false;
            initializeEdit();
        }else{
            initialize();
        }
    }

    private void initializeEdit() {
        binding.btAddCategory.setText("Update");
        binding.btDeleteC.setVisibility(View.VISIBLE);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        category = bundle.getParcelable("category");
        binding.tietNameC.setText(category.name);
        binding.tietUrlC.setText(category.url);
        Glide.with(this).load(category.url).into(binding.ivImageCategory);

        CategoryViewModel cvm = new ViewModelProvider(this).get(CategoryViewModel.class);
        binding.btAddCategory.setOnClickListener(view -> {
            if (binding.tietNameC.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Nombre vacio", Toast.LENGTH_LONG).show();
            }else{
                Category c = getCategoryEdit(category.id);
                if (c.isValid()){
                    cvm.updateCategory(c);
                    NavHostFragment.findNavController(this).popBackStack();
                }
            }
        });

        binding.btDeleteC.setOnClickListener(view -> {
            showDialog("Eliminar", "Desea eliminar");
        });

    }

    private Category getCategoryEdit(Long id) {
        String name = binding.tietNameC.getText().toString().trim();
        String url = binding.tietUrlC.getText().toString();

        Category c = new Category();
        c.id= id;
        c.name = name;
        c.url = url;

        return c;
    }

    private void initialize() {
        CategoryViewModel cvm = new ViewModelProvider(this).get(CategoryViewModel.class);

        binding.btAddCategory.setOnClickListener(view -> {
            if (binding.tietNameC.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Nombre vacio", Toast.LENGTH_LONG).show();
            }else{
                Category c = getCategoryCreate();
                if (c.isValid()){
                    cvm.insertCategory(c);
                    NavHostFragment.findNavController(this).popBackStack();
                }
            }
        });
    }


    //Confirmacio
    private void showDialog(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        CategoryViewModel cvm = new ViewModelProvider(this).get(CategoryViewModel.class);
        builder.setTitle(title)
                .setMessage(message)
                .setNegativeButton("cancel", (dialogInterface, wich) -> {
                    //Si dice que no, no hago nada
                    Toast.makeText(getContext(),"No se ha eliminado", Toast.LENGTH_LONG).show();
                })
                .setPositiveButton("ok", (dialogInterface, wich) -> {
                    Toast.makeText(getContext(),"Eliminado", Toast.LENGTH_LONG).show();
                    cvm.deleteCategory(category);
                    NavHostFragment.findNavController(this).popBackStack();
                });

        builder.create().show();

    }

    private Category getCategoryCreate() {
        String name = binding.tietNameC.getText().toString().trim();
        String url = binding.tietUrlC.getText().toString();

        Category c = new Category();
        c.name = name;
        c.url = url;

        return c;
    }

    private void cargarImagen() {
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    Glide.with(getActivity()).load(uri).into(binding.ivImageCategory);
                    binding.tietUrlC.setText(String.valueOf(uri));
                });

        binding.ivImageCategory.setOnClickListener(view1 -> mGetContent.launch("image/*"));

        binding.btAddImage.setOnClickListener(view12 -> mGetContent.launch("image/*"));
    }
}