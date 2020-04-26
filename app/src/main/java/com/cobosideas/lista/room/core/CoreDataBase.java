package com.cobosideas.lista.room.core;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.cobosideas.lista.room.DAO.ItemDAO;
import com.cobosideas.lista.room.ListaDataBase;
import com.cobosideas.lista.room.models.ItemRoom;

import java.util.List;

public class CoreDataBase {
    ListaDataBase dataBase;
    Context context;
    public CoreDataBase(Context context){
        this.context = context;
        this.dataBase = Room.databaseBuilder(context, ListaDataBase.class, "dbLista")
                .allowMainThreadQueries()
                .build();
    }
    //Adding Items to database
    public void addItemToListaDataBase(ItemRoom newItemROOM){
        ItemDAO itemDAO = dataBase.getItemDAO();
        itemDAO.insert(newItemROOM);
    }
    //getting data from database in memory
    public List<ItemRoom> getListaItemsFromDataBase(){
        ItemDAO itemDAO = dataBase.getItemDAO();
        return itemDAO.getItems();
    }
}
