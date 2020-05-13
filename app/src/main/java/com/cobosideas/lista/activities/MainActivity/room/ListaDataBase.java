package com.cobosideas.lista.activities.MainActivity.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;
import com.cobosideas.lista.activities.MainActivity.room.DAO.ItemDAO;
import com.cobosideas.lista.activities.manage_functions.ROOMDataBaseManageFunctions;
import com.cobosideas.lista.global.Constants;

@Database(entities = {ItemRoom.class}, version = 1)
public abstract class ListaDataBase extends RoomDatabase {
    public abstract ItemDAO getItemDAO();

    private static ListaDataBase instance;
    public synchronized static ListaDataBase getInstance(final Context context) {
        //getting coreDataBase name
        final String CODE_STRING_LISTA_ID =  Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_ID;
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    ListaDataBase.class,
                    CODE_STRING_LISTA_ID)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    /** I should look into this lines of code, there is an interesting way to way to declare
     *  a synchronized object
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
