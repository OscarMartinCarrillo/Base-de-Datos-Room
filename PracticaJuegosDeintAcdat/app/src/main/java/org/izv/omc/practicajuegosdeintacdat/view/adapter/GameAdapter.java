package org.izv.omc.practicajuegosdeintacdat.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.izv.omc.practicajuegosdeintacdat.R;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.model.entity.GameCategory;
import org.izv.omc.practicajuegosdeintacdat.view.adapter.viewholder.GameViewHolder;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    private List<GameCategory> gameList;
    private Context context;

    public GameAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GameCategory gameCategory = gameList.get(position);
        Game game = gameCategory.game;
        holder.game = game;
        Category category = gameCategory.category;
        holder.tvName.setText(game.name);
        holder.tvCategory.setText(category.name + "("+category.id + ")");
        holder.tvDifficulty.setText("Dificultad: " + game.difficulty);
        holder.tvDuration.setText("Duracion: " +game.duration);
        holder.tvPlayers.setText("Jugadores: " +game.players);
        Glide.with(context).load(game.url).into(holder.ivGame);
    }

    @Override
    public int getItemCount() {
        if(gameList == null) {
            return 0;
        }
        return gameList.size();
    }

    public void setGameList(List<GameCategory> gameList){
        this.gameList = gameList;
        notifyDataSetChanged();
    }
}
