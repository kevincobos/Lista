package com.cobosideas.lista.activities.manage_functions;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ModelItemManageFunctions.class}, version = 1)
public abstract class ROOMDataBaseManageFunctions extends RoomDatabase {
    public abstract DAOItemFunctions getItemDAO();
}
