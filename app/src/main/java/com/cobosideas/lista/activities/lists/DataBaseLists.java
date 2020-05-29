package com.cobosideas.lista.activities.lists;

import android.content.Context;
import androidx.room.Room;

import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;
import com.cobosideas.lista.global.Constants;

import java.util.ArrayList;
import java.util.List;

public class DataBaseLists {
    private ROOMDataBaseLists roomDataBaseLists;
    public DataBaseLists(Context context, long selectedDataBaseNumber){
        //database name combining (CODE_STRING_LISTS_ID and selectedDataBaseNumber)
        final String CODE_STRING_LISTS_ID = Constants.
                CODES_ACTIVITY_LISTS.CODE_STRING_LISTS_ID + selectedDataBaseNumber;
        //Setup database
        this.roomDataBaseLists = Room.databaseBuilder(context,
                ROOMDataBaseLists.class,
                CODE_STRING_LISTS_ID).allowMainThreadQueries().build();
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
    //creating a lists of database integers to access each list items
    public List<Long> getListsDataBaseAccessIds(){
        List<Long> listAccessesIds = new ArrayList<>();
        List<ModelItemLists> itemRooms = getListsItemsFromDataBase();
        for(int cont = 0; cont < itemRooms.size(); cont++){
            Long dataBaseIdAccess = itemRooms.get(cont).id;
            listAccessesIds.add(dataBaseIdAccess);
        }
        return listAccessesIds;
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
