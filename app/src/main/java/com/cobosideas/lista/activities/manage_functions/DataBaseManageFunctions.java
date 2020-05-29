package com.cobosideas.lista.activities.manage_functions;

import android.content.Context;

import androidx.room.Room;

import com.cobosideas.lista.activities.lists.ModelItemLists;
import com.cobosideas.lista.global.Constants;

import java.util.List;

public class DataBaseManageFunctions {
    private ROOMDataBaseManageFunctions roomDataBaseManageFunctions;
    public DataBaseManageFunctions(Context context, long selectedDataBaseNumber){
        //database name combining (MANAGE_FUNCTIONS_DATABASE_ID and selectedDataBaseNumber)
        final String MANAGE_FUNCTIONS_DATABASE_ID = Constants.
                CODES_ACTIVITY_MANAGE_FUNCTIONS.MANAGE_FUNCTIONS_DATABASE_ID + selectedDataBaseNumber;
        //Setup database
        this.roomDataBaseManageFunctions = Room.databaseBuilder(context,
                ROOMDataBaseManageFunctions.class,
                MANAGE_FUNCTIONS_DATABASE_ID).allowMainThreadQueries().build();
    }
    //Adding Items to database
    public Long addNewItem(ModelItemManageFunctions newItem){
        DAOItemFunctions DAOItem = roomDataBaseManageFunctions.getItemDAO();
        return DAOItem.insert(newItem);
    }
    //delete Items to database
    public void deleteItemSelected(ModelItemManageFunctions modelItemManageFunctions){
        DAOItemFunctions DAOItem = roomDataBaseManageFunctions.getItemDAO();
        DAOItem.delete(modelItemManageFunctions);
    }
    //getting data from database in memory
    public List<ModelItemManageFunctions> getAllItems(){
        DAOItemFunctions DAOItem = roomDataBaseManageFunctions.getItemDAO();
        return DAOItem.getAllItemsLists();
    }
    //getting selected item from database
    public ModelItemManageFunctions getItemSelected(Long Id){
        DAOItemFunctions DAOItem = roomDataBaseManageFunctions.getItemDAO();
        return DAOItem.getItemListsById(Id);
    }
    //creating a lists of database integers to access each list items
    public List<Long> getManageFunctionsDataBaseAccessesIds(){
        List<Long> manageFunctionsAccessesIds = null;
        List<ModelItemManageFunctions> itemRooms = getAllItems();
        for(int cont = 0; cont < itemRooms.size(); cont++){
            Long dataBaseIdAccess = itemRooms.get(cont).id;
            manageFunctionsAccessesIds.add(dataBaseIdAccess);
        }
        return manageFunctionsAccessesIds;
    }
    //update selected item from database
    public void updateItemSelected(ModelItemManageFunctions modelItemManageFunctions){
        DAOItemFunctions DAOItem = roomDataBaseManageFunctions.getItemDAO();
        DAOItem.update(modelItemManageFunctions);
    }
}
