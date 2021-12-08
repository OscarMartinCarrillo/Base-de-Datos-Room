package org.izv.omc.practicajuegosdeintacdat.model.entity;

import androidx.room.Embedded;

public class GameCategory {
    @Embedded
    public Game game;

    @Embedded(prefix="category_")
    public Category category;
}
