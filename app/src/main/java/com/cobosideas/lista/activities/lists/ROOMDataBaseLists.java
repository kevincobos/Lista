package com.cobosideas.lista.activities.lists;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {ModelItemLists.class}, version = 1)
public abstract class ROOMDataBaseLists extends RoomDatabase {

    public abstract DAOItemLists getItemDAO();

}
