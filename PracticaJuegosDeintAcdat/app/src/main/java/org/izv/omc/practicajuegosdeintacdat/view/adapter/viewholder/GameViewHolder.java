package org.izv.omc.practicajuegosdeintacdat.view.adapter.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.omc.practicajuegosdeintacdat.R;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.view.activity.EditGame;

public class GameViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName, tvCategory, tvDuration, tvPlayers, tvDifficulty;
    public ImageView ivGame;
    public CardView cardView;
    public Game game;

    public GameViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvCategory = itemView.findViewById(R.id.tvCategory);
        ivGame = itemView.findViewById(R.id.ivGame);
        cardView = itemView.findViewById(R.id.cardView);
        tvDuration = itemView.findViewById(R.id.tvDuration);
        tvPlayers = itemView.findViewById(R.id.tvPlayers);
        tvDifficulty = itemView.findViewById(R.id.tvDifficulty);

        //Le asignamos el onclick a la card
        cardView.setOnClickListener(view -> {
            Intent intent = new Intent(itemView.getContext(), EditGame.class);
            intent.putExtra("game", game);
            itemView.getContext().startActivity(intent);
        });

    }
}
