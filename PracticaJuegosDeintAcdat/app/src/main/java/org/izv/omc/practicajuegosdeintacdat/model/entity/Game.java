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

    @ColumnInfo(name = "releaseDate")
    public String releaseDate;

    @ColumnInfo(name = "url")
    public String url;

    public Game() {
        url="https://images.ecestaticos.com/O32uj25dhFe3szWBUUklVIhRYL0=/0x0:0x0/600x450/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2Fc83%2Fffb%2F385%2Fc83ffb385a4e05f3448c6777f5120517.jpg";
    }


    protected Game(Parcel in) {
        id = in.readLong();
        name = in.readString();
        idcategory = in.readLong();
        duration = in.readInt();
        players = in.readInt();
        difficulty = in.readInt();
        releaseDate = in.readString();
        url = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public boolean isValid() {
        return !(name.isEmpty() || idcategory <= 0 || duration <= 0 || players <= 0 || (difficulty != 0 && difficulty != 1 && difficulty != 2) || releaseDate.isEmpty() || url.isEmpty());
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
        dest.writeString(releaseDate);
        dest.writeString(url);
    }
}