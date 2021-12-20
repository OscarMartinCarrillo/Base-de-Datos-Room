package org.izv.omc.practicajuegosdeintacdat.view.adapter.viewholder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.omc.practicajuegosdeintacdat.R;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.view.activity.MainActivity;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameCategory;
    public ImageView ivCategory;
    public CardView cvrvCategory;
    public Bundle bundle;
    public Category category;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
        ivCategory = itemView.findViewById(R.id.ivCategory);
        cvrvCategory = itemView.findViewById(R.id.categoryCreate);
            cvrvCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bundle = new Bundle();
                    bundle.putParcelable("category", category);
                    Navigation.findNavController(itemView).navigate(R.id.action_main_to_createCategory, bundle);
                    MainActivity.edit=true;
                }
            });

    }
}
