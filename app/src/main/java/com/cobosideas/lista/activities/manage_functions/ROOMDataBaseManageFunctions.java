package com.cobosideas.lista.activities.manage_functions;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ModelItemManageFunctions.class}, version = 1)
@TypeConverters({ConverterManageFunctions.class})   //Adding this to be able to save JSON data
public abstract class ROOMDataBaseManageFunctions extends RoomDatabase {
    public abstract DAOItemFunctions getItemDAO();
}
