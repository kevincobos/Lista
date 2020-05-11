package com.cobosideas.lista.activities.manage_functions;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.cobosideas.lista.global.Constants;

import java.util.ArrayList;

@Entity (tableName = "itemManageFunctions") //This is the name of the table to be view values
public class ModelItemManageFunctions {
    @PrimaryKey(autoGenerate = true)
    public Long id;         //Auto Generate number
    public Long order;      //This one will be use to organize the items
    public Long link;       //This is the selected Lista item for the table identification
    public int function = Constants.CODES_DATABASE_MANAGE_FUNCTIONS.INT_DEFAULT_TEMPLATES;
    public String JSON = "";
}
