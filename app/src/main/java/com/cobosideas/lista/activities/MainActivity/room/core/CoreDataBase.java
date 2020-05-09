package com.cobosideas.lista.activities.MainActivity.room.core;

import android.content.Context;

import androidx.room.Room;

import com.cobosideas.lista.activities.MainActivity.room.DAO.ItemDAO;
import com.cobosideas.lista.activities.MainActivity.room.ListaDataBase;
import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;
import com.cobosideas.lista.global.Constants;

import java.util.List;

public class CoreDataBase {
    private ListaDataBase dataBase;
    public CoreDataBase(Context context){
        //getting coreDataBase name
        final String CODE_STRING_LISTA_ID =  Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_ID;
        this.dataBase = Room.databaseBuilder(context, ListaDataBase.class, CODE_STRING_LISTA_ID)
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
    //update selected item from database
    public void updateSelectedItem(ItemRoom updatingItem){
        ItemDAO DAOItem = dataBase.getItemDAO();
        DAOItem.update(updatingItem);
    }
}
