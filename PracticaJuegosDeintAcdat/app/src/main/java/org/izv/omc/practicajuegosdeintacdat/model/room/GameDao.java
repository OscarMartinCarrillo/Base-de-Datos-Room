package org.izv.omc.practicajuegosdeintacdat.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.izv.omc.practicajuegosdeintacdat.model.entity.Category;
import org.izv.omc.practicajuegosdeintacdat.model.entity.Game;
import org.izv.omc.practicajuegosdeintacdat.model.entity.GameCategory;

import java.util.List;

@Dao
public interface GameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertGame(Game... games);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Game game);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertCategory(Category... categories);

    @Update
    int updateGame(Game... games);

    @Update
    int updateCategory(Category... categories);

    @Delete
    int deleteGames(Game... games);

    @Delete
    int deleteCategory(Category... categories);

    @Query("delete from category")
    int deleteAllCategories();

    @Query("delete from game")
    int deleteAllGame();

    @Query("select * from game order by name asc")
    LiveData<List<Game>> getGames();

    @Query("select g.*, c.id category_id, c.name category_name from game g join category c on g.idcategory = c.id order by name asc")
    LiveData<List<GameCategory>> getAllGame();

    @Query("select * from category order by name asc")
    LiveData<List<Category>> getCategories();

    @Query("select * from game where id = :id")
    LiveData<Game> getGame(long id);

    @Query("select * from category where id = :id")
    LiveData<Category> getCategory(long id);

    @Query("select id from category where name = :name")
    long getIdCategory(String name);

    @Query("select * from category where name = :name")
    Category getCategory(String name);
}
