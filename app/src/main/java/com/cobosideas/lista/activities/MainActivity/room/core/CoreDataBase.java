package com.cobosideas.lista.activities.MainActivity.room.core;

import android.content.Context;

import com.cobosideas.lista.activities.MainActivity.room.DAO.ItemDAO;
import com.cobosideas.lista.activities.MainActivity.room.ListaDataBase;
import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;
import com.cobosideas.lista.global.Constants;

import java.util.ArrayList;
import java.util.List;

public class CoreDataBase {
    private ListaDataBase listaDataBase;
    public CoreDataBase(Context context){
        this.listaDataBase = ListaDataBase.getInstance(context);
    }
    //Adding Items to database
    public Long addItemToListaDataBase(ItemRoom newItemROOM){
        ItemDAO itemDAO = listaDataBase.getItemDAO();
        return itemDAO.insert(newItemROOM);
    }
    //delete Items to database
    public void deleteItemSelected(ItemRoom newItemROOM){
        ItemDAO itemDAO = listaDataBase.getItemDAO();
        itemDAO.delete(newItemROOM);
    }
    //getting data from database in memory
    public List<ItemRoom> getListaItemsFromDataBase(){
        ItemDAO itemDAO = listaDataBase.getItemDAO();
        return itemDAO.getItems();
    }
    //creating a lista of database numbers to access each list
    public List<Long> getListaDataBaseAccessesIds(){
        List<Long> listaAccessesIds = new ArrayList<>();
        List<ItemRoom> itemRooms = getListaItemsFromDataBase();
        for(int cont = 0; cont < itemRooms.size(); cont++){
            long dataBaseAccessId = itemRooms.get(cont).id;
            listaAccessesIds.add(dataBaseAccessId);
        }
        return listaAccessesIds;
    }
    //getting selected item from database
    public ItemRoom getListaItemFromDataBase(Long Id){
        ItemDAO itemDAO = listaDataBase.getItemDAO();
        return itemDAO.getItemById(Id);
    }
    //update selected item from database
    public void updateSelectedItem(ItemRoom updatingItem){
        ItemDAO DAOItem = listaDataBase.getItemDAO();
        DAOItem.update(updatingItem);
    }
}
