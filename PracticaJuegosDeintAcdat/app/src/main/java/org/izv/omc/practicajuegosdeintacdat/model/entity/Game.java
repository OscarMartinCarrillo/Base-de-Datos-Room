package org.izv.omc.practicajuegosdeintacdat.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "game",
        indices = {@Index(value = "name", unique = true)},
        foreignKeys = {@ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "idcategory", onDelete = ForeignKey.CASCADE)})
public class Game implements Parcelable {

    //id autonumérico
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "idcategory")
    public long idcategory;

    @ColumnInfo(name = "duration")
    public int duration;

    @ColumnInfo(name = "players")
    public int players;

    @ColumnInfo(name = "difficulty")
    public int difficulty;

    @ColumnInfo(name = "url")
    public String url;

    public Game() {
    }


    public boolean isValid() {
        return !(name.isEmpty() || idcategory <= 0 || duration <= 0 || players <= 0 || difficulty <= 0 || url.isEmpty());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(idcategory);
        dest.writeInt(duration);
        dest.writeInt(players);
        dest.writeInt(difficulty);
        dest.writeString(url);
    }
}