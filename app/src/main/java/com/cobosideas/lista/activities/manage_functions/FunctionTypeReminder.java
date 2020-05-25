package com.cobosideas.lista.activities.manage_functions;

public class FunctionTypeReminder {
    public String name = "";
    private String reminderInformation = "";
    private boolean remainderNeededToday = false;
    private boolean repeat = false;
    private boolean hourControl = false;
    private boolean specificTime = false;
    private String selectedTime = "";
    private boolean[] selectedHours = new boolean[12];
    private boolean dateControl = false;
    private boolean specificDay = false;
    private String selectedDay = "";
    private boolean[] selectedDays = new boolean[7];
    private boolean months = false;
    private boolean monthsControl = false;
    private boolean[] selectedMonths = new boolean[12];
    private long dateFunctionCreated = 0;
    private long dateFunctionModify = 0;

    public FunctionTypeReminder(){
        this.setSpecificTime(false);
    }

    public FunctionTypeReminder(String newName, String reminderInformation){
        this.name = newName;
        this.reminderInformation = reminderInformation;
        this.dateFunctionCreated = System.currentTimeMillis();
        this.dateFunctionModify = System.currentTimeMillis();
    }

    public FunctionTypeReminder(String name, String reminderInformation,
                                boolean remainderNeededToday, boolean repeat, boolean hourControl,
                                boolean specificTime, String selectedTime, boolean selectedHours,
                                boolean[] hours, boolean dateControl,
                                boolean specificDay, String selectedDay, boolean selectDays,
                                boolean[] selectedDays, boolean months,
                                boolean monthsControl, boolean[] selectedMonths,
                                long dateFunctionCreated, long dateFunctionModify) {
        this.name = name;
        this.reminderInformation = reminderInformation;
        this.remainderNeededToday = remainderNeededToday;
        this.repeat = repeat;
        this.hourControl = hourControl;
        this.specificTime = specificTime;
        this.selectedTime = selectedTime;
        this.selectedHours = hours;
        this.dateControl = dateControl;
        this.specificDay = specificDay;
        this.selectedDay = selectedDay;
        this.selectedDays = selectedDays;
        this.months = months;
        this.monthsControl = monthsControl;
        this.selectedMonths = selectedMonths;
        this.dateFunctionCreated = dateFunctionCreated;
        this.dateFunctionModify = dateFunctionModify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReminderInformation() {
        return reminderInformation;
    }

    public void setReminderInformation(String reminderInformation) {
        this.reminderInformation = reminderInformation;
    }

    public boolean isRemainderNeededToday() {
        return remainderNeededToday;
    }

    public void setRemainderNeededToday(boolean remainderNeededToday) {
        this.remainderNeededToday = remainderNeededToday;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isHourControl() {
        return hourControl;
    }

    public void setHourControl(boolean hourControl) {
        this.hourControl = hourControl;
    }

    public boolean isSpecificTime() {
        return specificTime;
    }

    public void setSpecificTime(boolean specificTime) {
        this.specificTime = specificTime;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }

    public boolean[] getSelectedHours() {
        return selectedHours;
    }
    public boolean getSelectedHours(int selectedItem) {
        return selectedHours[selectedItem];
    }

    public void setSelectedHours(boolean[] selectedHours) {
        this.selectedHours = selectedHours;
    }

    public boolean isDateControl() {
        return dateControl;
    }

    public void setDateControl(boolean dateControl) {
        this.dateControl = dateControl;
    }

    public boolean isSpecificDay() {
        return specificDay;
    }

    public void setSpecificDay(boolean specificDay) {
        this.specificDay = specificDay;
    }

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
    }

    public boolean[] getSelectedDays() {
        return selectedDays;
    }

    public boolean getSelectedDays(int selectedItem) {
        return selectedDays[selectedItem];
    }


    public void setSelectedDays(boolean[] selectedDays) {
        this.selectedDays = selectedDays;
    }

    public boolean isMonths() {
        return months;
    }

    public void setMonths(boolean months) {
        this.months = months;
    }

    public boolean[] getSelectedMonths() {
        return selectedMonths;
    }

    public boolean getSelectedMonths(int selectedItem) {
        return selectedMonths[selectedItem];
    }

    public void setSelectedMonths(boolean[] selectedMonths) {
        this.selectedMonths = selectedMonths;
    }

    public boolean isMonthsControl() {
        return monthsControl;
    }

    public void setMonthsControl(boolean monthsControl) {
        this.monthsControl = monthsControl;
    }

    public long getDateFunctionCreated() {
        return dateFunctionCreated;
    }

    public void setDateFunctionCreated(long dateFunctionCreated) {
        this.dateFunctionCreated = dateFunctionCreated;
    }

    public long getDateFunctionModify() {
        return dateFunctionModify;
    }

    public void setDateFunctionModify(long dateFunctionModify) {
        this.dateFunctionModify = dateFunctionModify;
    }
}
