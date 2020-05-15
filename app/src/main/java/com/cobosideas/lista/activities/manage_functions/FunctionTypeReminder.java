package com.cobosideas.lista.activities.manage_functions;

public class FunctionTypeReminder {
    public String name = "";
    private String reminderInformation = "";
    private boolean remainderNeededToday = false;
    private boolean repeat = false;
    private boolean hourControl = false;
    private boolean specificTime = false;
    private String selectedTime = "";
    private boolean selectedHours = false;
    private boolean[] hours = new boolean[12];
    private boolean dateControl = false;
    private boolean specificDay = false;
    private String selectedDate = "";
    private boolean selectDays =false;
    private boolean[] selectedDays = new boolean[7];
    private boolean months = false;
    private boolean monthsControl = false;
    private boolean[] selectedMonths = new boolean[12];
    private long dateFunctionCreated = 0;
    private long dateFunctionModify = 0;

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
                                boolean specificDay, String selectedDate, boolean selectDays,
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
        this.selectedHours = selectedHours;
        this.hours = hours;
        this.dateControl = dateControl;
        this.specificDay = specificDay;
        this.selectedDate = selectedDate;
        this.selectDays = selectDays;
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

    public boolean isSelectedHours() {
        return selectedHours;
    }

    public void setSelectedHours(boolean selectedHours) {
        this.selectedHours = selectedHours;
    }

    public boolean[] getHours() {
        return hours;
    }

    public void setHours(boolean[] hours) {
        this.hours = hours;
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

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public boolean isSelectDays() {
        return selectDays;
    }

    public void setSelectDays(boolean selectDays) {
        this.selectDays = selectDays;
    }

    public boolean[] getSelectedDays() {
        return selectedDays;
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
