package com.cobosideas.lista.activities.manage_functions;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class TypeReminderViewModel extends ViewModel {
    MutableLiveData<FunctionTypeReminder> functionTypeReminderMutableLiveData;
    public TypeReminderViewModel(){
        this.functionTypeReminderMutableLiveData = new MutableLiveData<>();
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
