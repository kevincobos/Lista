package com.cobosideas.lista.activities.MainActivity.room.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

@Entity (tableName = "items")// if I want it to be only for this table
public class ItemRoom {
    @PrimaryKey (autoGenerate = true)
    public Long id;
    public String name;
    public String description;
    public Long date = System.currentTimeMillis();
    public Long dateModify = System.currentTimeMillis();
    public int function = Constants.CODES_LISTS_CARD_VIEW.CODE_INT_CARD_VIEW_DEFAULT;
    public int icon = R.drawable.ic_launcher_foreground;
}
