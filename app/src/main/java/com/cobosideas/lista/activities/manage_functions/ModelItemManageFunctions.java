package com.cobosideas.lista.activities.manage_functions;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cobosideas.lista.global.Constants;

import java.util.ArrayList;

@Entity (tableName = "itemManageFunctions") //This is the name of the table to be view values
public class ModelItemManageFunctions {
    @PrimaryKey(autoGenerate = true)
    public Long id;         //Auto Generate number
    public Long order;      //This one will be use to organize the items
    public Long link;       //This is the selected Lista item for the table identification
    public String name;     //value's name
    public Long date = System.currentTimeMillis();
    public Long dateModify = System.currentTimeMillis();
    public int function = Constants.CODES_DATABASE_MANAGE_FUNCTIONS.INT_DEFAULT_TEMPLATES;
    public ArrayList<String> jSON = new ArrayList<>();
}
