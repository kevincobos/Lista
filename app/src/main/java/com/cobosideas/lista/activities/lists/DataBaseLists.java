package com.cobosideas.lista.activities.lists;

import android.content.Context;
import androidx.room.Room;
import java.util.List;

class DataBaseLists {
    private ROOMDataBaseLists roomDataBaseLists;
    DataBaseLists(Context context, String dataBaseName){
        this.roomDataBaseLists = Room.databaseBuilder(context, ROOMDataBaseLists.class, dataBaseName)
                .allowMainThreadQueries()
                .build();
    }
    //Adding Items to database
    Long addItemToListDataBase(ModelItemLists newItem){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.insert(newItem);
    }
    //delete Items to database
    void deleteItemSelected(ModelItemLists modelItemLists){
        DAOItemLists itemDAO = roomDataBaseLists.getItemDAO();
        itemDAO.delete(modelItemLists);
    }
    //getting data from database in memory
    List<ModelItemLists> getListsItemsFromDataBase(){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.getAllItemsLists();
    }
    //getting selected item from database
    ModelItemLists getListaItemFromDataBase(Long Id){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.getItemListsById(Id);
    }
}
