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
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.view.activity.MainActivity;

public class GameViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName, tvCategory, tvDuration, tvPlayers, tvDifficulty, tvReleaseDate;
    public ImageView ivGame;
    public CardView cardView;
    public Game game;
    public Bundle bundle;

    public GameViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvCategory = itemView.findViewById(R.id.tvCategory);
        ivGame = itemView.findViewById(R.id.ivGame);
        cardView = itemView.findViewById(R.id.cvGameCreate);
        tvDuration = itemView.findViewById(R.id.tvDuration);
        tvPlayers = itemView.findViewById(R.id.tvPlayers);
        tvDifficulty = itemView.findViewById(R.id.tvDifficulty);
        tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);

        //Le asignamos el onclick a la card
        cardView.setOnClickListener(view -> {
            bundle = new Bundle();
            bundle.putParcelable("game",game);
            Navigation.findNavController(itemView).navigate(R.id.action_main_to_createGame,bundle);
            MainActivity.edit=true;
        });

    }
}
