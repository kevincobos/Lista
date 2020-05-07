package com.cobosideas.lista.activities.lists;

import android.content.Context;
import androidx.room.Room;

import com.cobosideas.lista.global.Constants;

import java.util.List;

public class DataBaseLists {
    private ROOMDataBaseLists roomDataBaseLists;
    public DataBaseLists(Context context, long selectedDataBaseNumber){
        //database name combining (CODE_DATABASE_ID and globalId)
        final String CODE_DATABASE_ID =  Constants.CODES_ACTIVITY_LISTS.CODE_DATABASE_ID;
        this.roomDataBaseLists = Room.databaseBuilder(context, ROOMDataBaseLists.class, CODE_DATABASE_ID+selectedDataBaseNumber)
                .allowMainThreadQueries()
                .build();
    }
    //Adding Items to database
    public Long addItemToListDataBase(ModelItemLists newItem){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.insert(newItem);
    }
    //delete Items to database
    public void deleteItemSelected(ModelItemLists modelItemLists){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        DAOItem.delete(modelItemLists);
    }
    //getting data from database in memory
    public List<ModelItemLists> getListsItemsFromDataBase(){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.getAllItemsLists();
    }
    //getting selected item from database
    public ModelItemLists getListsItemFromDataBase(Long Id){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.getItemListsById(Id);
    }
    //update selected item from database
    public void updateSelectedItemListsFromDataBase(ModelItemLists modelItemLists){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        DAOItem.update(modelItemLists);
    }
}
