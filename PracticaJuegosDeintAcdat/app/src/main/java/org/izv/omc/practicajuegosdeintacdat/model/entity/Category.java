package org.izv.omc.practicajuegosdeintacdat.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "category",
        indices = {@Index(value = "name", unique = true)})
public class Category {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "url")
    public String url;

    @Override
    public String toString() {
        return name;
    }
}