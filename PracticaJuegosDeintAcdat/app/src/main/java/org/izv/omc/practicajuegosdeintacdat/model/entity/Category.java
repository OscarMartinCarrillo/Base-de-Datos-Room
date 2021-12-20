package org.izv.omc.practicajuegosdeintacdat.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "category",
        indices = {@Index(value = "name", unique = true)})
public class Category implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "url")
    public String url;

    public Category() {
        url="https://us.123rf.com/450wm/urfandadashov/urfandadashov1808/urfandadashov180823404/108989369-icono-de-vector-de-juego-de-mesa-aislado-sobre-fondo-transparente-concepto-de-logo-de-juego-de-mesa.jpg?ver=6";
    }

    protected Category(Parcel in) {
        id = in.readLong();
        name = in.readString();
        url = in.readString();
    }

    public boolean isValid() {
        return !(name.isEmpty() || url.isEmpty());
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(url);

    }
}