package com.cobosideas.lista.activities.manage_functions;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class ModelTypeReminder extends ViewModel {
    private MutableLiveData<FunctionTypeReminder> functionTypeReminderMutableLiveData;
    private MutableLiveData<String> name;
    public MutableLiveData<String> getName(){
        return this.name;
    }
    public ModelTypeReminder(){
        this.functionTypeReminderMutableLiveData = new MutableLiveData<FunctionTypeReminder>();
        this.name = new MutableLiveData<String>();
    }
    public void setAllValues(FunctionTypeReminder functionTypeReminder){
        this.functionTypeReminderMutableLiveData.setValue(functionTypeReminder);
    }
    public FunctionTypeReminder setAllAutoValues( ){
        if (functionTypeReminderMutableLiveData == null) {
            this.functionTypeReminderMutableLiveData.setValue(new FunctionTypeReminder());
        }
        return this.functionTypeReminderMutableLiveData.getValue();
    }
    public MutableLiveData<FunctionTypeReminder> getAllValues(){
        return this.functionTypeReminderMutableLiveData;
    }
}
