package com.cobosideas.lista.activities.MainActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;

import java.util.List;

public class MainViewModel extends ViewModel {
    //public List<ItemRoom> listaItemRoom;//Holds all data using itemRoom structure
    private MutableLiveData<List<ItemRoom>> listaItemRoom;//Holds all data using itemRoom structure

    public MainViewModel(){
        this.listaItemRoom = new MutableLiveData<>();
    }
    public void setAllItemsViewModel(MutableLiveData<List<ItemRoom>> allItemsRoom){
        this.listaItemRoom = allItemsRoom;
    }
    public void setAllItemsViewModel(List<ItemRoom> allItemsRoom){
        this.listaItemRoom.setValue(allItemsRoom);
    }
    public MutableLiveData<List<ItemRoom>> getAllItemsMutableLiveDataRoom(){
        return this.listaItemRoom;
    }
}
