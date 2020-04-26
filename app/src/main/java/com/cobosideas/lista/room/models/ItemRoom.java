package com.cobosideas.lista.room.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class ItemRoom {
    @PrimaryKey (autoGenerate = true)
    public Long id;
    public String name;
    public String description;
    public int photoId;


}
