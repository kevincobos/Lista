package com.cobosideas.lista.global.remainders_manager;

import android.content.Context;

import com.cobosideas.lista.activities.MainActivity.room.DAO.ItemDAO;
import com.cobosideas.lista.activities.MainActivity.room.core.CoreDataBase;
import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;
import com.cobosideas.lista.activities.manage_functions.FunctionTypeReminder;
import com.cobosideas.lista.global.Constants;

import java.util.ArrayList;
import java.util.List;

public class ReminderClock {
    private List<ItemRoom> listaDataBaseNames;
    private List<List<String>> listsDataBaseNames;
    public ReminderClock(Context context){
        CoreDataBase coreDataBase = new CoreDataBase(context);//Starting DataBase
        this.listaDataBaseNames = coreDataBase.getListaItemsFromDataBase();
    }
    public List<String> getAllListasIds(){
        List<String> listasIds = null;
        for (int cont = 0; cont < listaDataBaseNames.size(); cont++){
            listasIds.add(listaDataBaseNames.get(cont).id.toString());
        }
        return listasIds;
    }
    //getting data from database in memory
    public List<String> getListaDataBaseName(){
        List<String> listaIdsString = null;
        ItemDAO itemDAO = listaDataBase.getItemDAO();
        List<ItemRoom> itemRooms = itemDAO.getItems();
        for(int cont = 0; cont < itemDAO.getItems().size(); cont++){
            //create a List of All the names of Listas
            //database name combining (CODE_STRING_LISTS_ID and selectedDataBaseNumber)
            final String CODE_STRING_LISTS_ID =  Constants.CODES_ACTIVITY_LISTS.CODE_STRING_LISTS_ID;
            String getListaDataBase = CODE_STRING_LISTS_ID + itemRooms.get(cont).id;
            listaIdsString.add(getListaDataBase);
        }
        return listaIdsString;
    }
    public int getAllListas(){
        return listaDataBaseNames.size();
    }
    public List<String> getAllListsIds(){
        List<String> listsIds = null;

        return listsIds;
    }
    public ArrayList<FunctionTypeReminder> getAllReminders(){
        ArrayList<FunctionTypeReminder> allReminders =  new ArrayList<>();

        return allReminders;
    }
}
