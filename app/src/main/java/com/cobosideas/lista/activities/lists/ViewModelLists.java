package com.cobosideas.lista.activities.lists;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cobosideas.lista.room.models.ItemRoom;

import java.util.List;

public class ViewModelLists extends ViewModel {
    //public List<ItemRoom> listaItemRoom;//Holds all data using itemRoom structure
    private MutableLiveData<List<ModelItemLists>> modelItemLists;//Holds all data using itemRoom structure

    public ViewModelLists(){
        this.modelItemLists = new MutableLiveData<>();
    }
    public void setAllItemsViewModel(MutableLiveData<List<ModelItemLists>> allModelItems){
        this.modelItemLists = allModelItems;
    }
    public void setAllItemsViewModel(List<ModelItemLists> allModelItems){
        this.modelItemLists.setValue(allModelItems);
    }
    public MutableLiveData<List<ModelItemLists>> getAllItemsMutableLiveDataModel(){
        return this.modelItemLists;
    }
    public void addItemRoom(ModelItemLists modelItem){
        //this.listaItemRoom.setValue(modelItem); TODO fix adding itemize
    }
}
