package com.cobosideas.lista.global.remainders_manager;

import android.content.Context;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.cobosideas.lista.activities.MainActivity.room.core.CoreDataBase;
import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;
import com.cobosideas.lista.activities.lists.DataBaseLists;
import com.cobosideas.lista.activities.manage_functions.DataBaseManageFunctions;
import com.cobosideas.lista.activities.manage_functions.FunctionTypeReminder;
import com.cobosideas.lista.activities.manage_functions.ModelItemManageFunctions;
import com.cobosideas.lista.activities.manage_functions.TypeConverterFunctions;
import com.cobosideas.lista.global.Constants;

import java.util.ArrayList;
import java.util.List;
public class ReminderClock {
    private Context gContext;
    private List<ItemRoom> gListaDataBaseNames;
    private List<List<String>> listsDataBaseNames;
    public ReminderClock(Context context){
        gContext = context;
        CoreDataBase coreDataBase = new CoreDataBase(gContext);//Starting DataBase
        this.gListaDataBaseNames = coreDataBase.getListaItemsFromDataBase();
    }
    public List<String> getAllListasIds(){
        List<String> listasIds = new ArrayList<>();
        for (int cont = 0; cont < gListaDataBaseNames.size(); cont++){
            listasIds.add(gListaDataBaseNames.get(cont).id.toString());
        }
        return listasIds;
    }
    public int getAllListas(){
        return gListaDataBaseNames.size();
    }
    public List<ModelItemManageFunctions> getAllManageFunctions(){
        List<ModelItemManageFunctions> modelItemManageFunctions = new ArrayList<>();
        CoreDataBase coreDataBase = new CoreDataBase(gContext);
        List<Long> listasIdsAccess = coreDataBase.getListaDataBaseAccessesIds();
        for (int cont = 0; cont < listasIdsAccess.size(); cont++){
            DataBaseLists dataBaseLists = new DataBaseLists(gContext, listasIdsAccess.get(cont));
            List<Long> listsIdsAccess = dataBaseLists.getListsDataBaseAccessIds();
            for (int counterLists = 0; counterLists < listsIdsAccess.size(); counterLists++) {
                DataBaseManageFunctions dataBaseManageFunctions = new DataBaseManageFunctions(gContext,
                        listsIdsAccess.get(counterLists));
                List<ModelItemManageFunctions> manageFunctions = dataBaseManageFunctions.getAllItems();
                modelItemManageFunctions.addAll(manageFunctions);
            }
        }
        return modelItemManageFunctions;
    }
    public List<FunctionTypeReminder> getAllReminders(){
        final int INT_REMAINDER_TEMPLATES = Constants.CODES_DATABASE_MANAGE_FUNCTIONS.
                INT_REMAINDER_TEMPLATES;
        List<ModelItemManageFunctions> modelItemManageFunctions = getAllManageFunctions();
        List<FunctionTypeReminder> allReminders = new ArrayList<>();
        FunctionTypeReminder functionTypeReminder;
        int allManageFunctions = modelItemManageFunctions.size();
        for (int cont = 0; cont < allManageFunctions; cont++) {
            int INT_FUNCTION_MANAGE_TEMPLATE = modelItemManageFunctions.get(cont).function;
            if (INT_FUNCTION_MANAGE_TEMPLATE == INT_REMAINDER_TEMPLATES) {
                String JSON = modelItemManageFunctions.get(cont).JSON;
                functionTypeReminder = TypeConverterFunctions.getFunctionTypeRemainder(JSON);
                allReminders.add(functionTypeReminder);
            }
        }
        return allReminders;
    }
    public List<String> getAllRemindersNames(){
        List<String> allRemindersNames = new ArrayList<>();
        List<FunctionTypeReminder> functionTypeReminders = getAllReminders();
        int totalReminders = functionTypeReminders.size();
        for (int cont = 0; cont <  totalReminders; cont++) {
            allRemindersNames.add(functionTypeReminders.get(cont).name);
        }
        return allRemindersNames;
    }
}
