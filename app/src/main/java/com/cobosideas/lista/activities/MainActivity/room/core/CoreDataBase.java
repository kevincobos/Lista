package com.cobosideas.lista.activities.MainActivity.room.core;

import android.content.Context;

import com.cobosideas.lista.activities.MainActivity.room.DAO.ItemDAO;
import com.cobosideas.lista.activities.MainActivity.room.ListaDataBase;
import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;

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
    //getting data from database in memory
    public List<Integer> getListaIsNumber(){
        List<Integer> listaIdsInteger = null;
        ItemDAO itemDAO = listaDataBase.getItemDAO();
        List<ItemRoom> itemRooms = itemDAO.getItems();
        for(int cont = 0; cont < itemDAO.getItems().size(); cont++){
            listaIdsInteger.add(itemRooms)
        }
        return listaIdsInteger;
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
