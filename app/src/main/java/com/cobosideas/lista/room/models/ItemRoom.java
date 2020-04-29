package com.cobosideas.lista.room.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "items")// if I want it to be only for this table
public class ItemRoom {
    @PrimaryKey (autoGenerate = true)
    public Long id;
    public String name;
    public String description;
    public int photoId;
}
