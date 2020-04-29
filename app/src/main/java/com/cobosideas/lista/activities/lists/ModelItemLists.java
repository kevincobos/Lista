package com.cobosideas.lista.activities.lists;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "itemLists") //This is the name of the table to be view values
public class ModelItemLists {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public Long order;
    public String name;
    public String description;
    public Long date;
    public Long dateModify;
    public int function;
}
