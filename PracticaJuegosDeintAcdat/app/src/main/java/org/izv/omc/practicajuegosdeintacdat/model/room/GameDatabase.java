package org.izv.omc.practicajuegosdeintacdat.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;

@Database(entities = {Game.class, Category.class}, version = 1, exportSchema = false)
public abstract class GameDatabase extends RoomDatabase {

    public abstract GameDao getDao();

    private static volatile GameDatabase INSTANCE;

    /* versión simplificada */
    public static GameDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GameDatabase.class, "game").build();
        }
        return INSTANCE;
    }
}
