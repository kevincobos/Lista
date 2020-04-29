package com.cobosideas.lista.activities.lists;

import android.content.Context;
import androidx.room.Room;
import java.util.List;

public class DataBaseLists {
    private ROOMDataBaseLists roomDataBaseLists;
    public DataBaseLists(Context context, String dataBaseName){
        this.roomDataBaseLists = Room.databaseBuilder(context, ROOMDataBaseLists.class, dataBaseName)
                .allowMainThreadQueries()
                .build();
    }
    //Adding Items to database
    public Long addItemToListDataBase(ModelItemLists newItem){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.insert(newItem);
    }
    //getting data from database in memory
    public List<ModelItemLists> getListsItemsFromDataBase(){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.getAllItemsLists();
    }
    //getting selected item from database
    public ModelItemLists getListaItemFromDataBase(Long Id){
        DAOItemLists DAOItem = roomDataBaseLists.getItemDAO();
        return DAOItem.getItemListsById(Id);
    }
}
