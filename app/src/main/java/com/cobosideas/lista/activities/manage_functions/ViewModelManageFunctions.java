package com.cobosideas.lista.activities.manage_functions;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ViewModelManageFunctions extends ViewModel {
    //public List<ItemRoom> listaItemRoom;//Holds all data using itemRoom structure
    private MutableLiveData<List<ModelItemManageFunctions>> modelItemLists;//Holds all data using itemRoom structure

    public ViewModelManageFunctions(){
        this.modelItemLists = new MutableLiveData<>();
    }
    public void setAllItemsViewModel(MutableLiveData<List<ModelItemManageFunctions>> allModelItems){
        this.modelItemLists = allModelItems;
    }
    public void setAllItemsViewModel(List<ModelItemManageFunctions> allModelItems){
        this.modelItemLists.setValue(allModelItems);
    }
    public MutableLiveData<List<ModelItemManageFunctions>> getAllItemsMutableLiveDataModel(){
        return this.modelItemLists;
    }
    public void addItemRoom(ModelItemManageFunctions modelItem){
        //this.listaItemRoom.setValue(modelItem); TODO fix adding itemize
    }
}
