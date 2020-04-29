package com.cobosideas.lista.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cobosideas.lista.room.models.ItemRoom;
import com.cobosideas.lista.room.DAO.ItemDAO;

@Database(entities = {ItemRoom.class}, version = 1)
public abstract class ListaDataBase extends RoomDatabase {

    public abstract ItemDAO getItemDAO();

    /**
    private static ListaDataBase INSTANCE;
    private static final Object sLock = new Object();
    public static ListaDataBase getInstance(Context context){
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PostsDatabase.class, "Posts.db")
                        .allowMainThreadQueries()
                        .build();
            }
        return INSTANCE;
    }*/
}
