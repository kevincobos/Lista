package com.cobosideas.lista.activities.manage_functions;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

public class FunctionTypeReminder {
    public String name = "";
    public String reminderInformation = "";
    public long dateFunctionCreated = 0;
    public long dateFunctionModify = 0;

    public FunctionTypeReminder(String newName, String reminderInformation){
        this.name = newName;
        this.reminderInformation = reminderInformation;
        this.dateFunctionCreated = System.currentTimeMillis();
        this.dateFunctionModify = System.currentTimeMillis();
    }
}
