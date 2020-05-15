package com.cobosideas.lista.activities.manage_functions;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

public class FunctionTypeReminder {
    public String name = "";
    public String reminderInformation = "";
    public boolean remainderNeededToday = false;
    public boolean repeat = false;
    public boolean hourControl = false;
    public boolean specificTime = false;
    public String selectedTime = "";
    public boolean selectedHours = false;
    public boolean[] hours = new boolean[12];
    public boolean dateControl = false;
    public boolean specificDay = false;
    public String selectedDate = "";
    public boolean selectedDay =false;
    public boolean[] days = new boolean[7];
    public boolean months = false;
    public boolean[] selectedMonths = new boolean[12];

    public boolean monthsControl = false;
    public long dateFunctionCreated = 0;
    public long dateFunctionModify = 0;

    public FunctionTypeReminder(String newName, String reminderInformation){
        this.name = newName;
        this.reminderInformation = reminderInformation;
        this.dateFunctionCreated = System.currentTimeMillis();
        this.dateFunctionModify = System.currentTimeMillis();
    }
}
