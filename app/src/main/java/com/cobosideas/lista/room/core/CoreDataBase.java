package com.cobosideas.lista.room.core;

import android.content.Context;

import androidx.room.Room;

import com.cobosideas.lista.room.DAO.ItemDAO;
import com.cobosideas.lista.room.ListaDataBase;
import com.cobosideas.lista.room.models.ItemRoom;

import java.util.List;

public class CoreDataBase {
    private ListaDataBase dataBase;

    public CoreDataBase(Context context){
        this.dataBase = Room.databaseBuilder(context, ListaDataBase.class, "dbLista")
                .allowMainThreadQueries()
                .build();
    }
    //Adding Items to database
    public Long addItemToListaDataBase(ItemRoom newItemROOM){
        ItemDAO itemDAO = dataBase.getItemDAO();
        return itemDAO.insert(newItemROOM);
    }
    //delete Items to database
    public void deleteItemSelected(ItemRoom newItemROOM){
        ItemDAO itemDAO = dataBase.getItemDAO();
        itemDAO.delete(newItemROOM);
    }
    //getting data from database in memory
    public List<ItemRoom> getListaItemsFromDataBase(){
        ItemDAO itemDAO = dataBase.getItemDAO();
        return itemDAO.getItems();
    }
    //getting selected item from database
    public ItemRoom getListaItemFromDataBase(Long Id){
        ItemDAO itemDAO = dataBase.getItemDAO();
        return itemDAO.getItemById(Id);
    }
}
