package com.cobosideas.lista.global;

public class Constants {
    //100 = CODES_ALERT_DIALOG_FRAGMENT
    private final static int CODE_ALERT_DIALOG_FRAGMENT = 100;
    //200 = CODES_ALERT_MAIN_RECYCLER
    private final static int CODES_ALERT_MAIN_RECYCLER = 200;
    //300 = CODES_ALERT_MAIN_RECYCLER
    private final static int CODES_ACTIVITY_LISTS = 300;

    public interface CODES_ALERT_DIALOG_FRAGMENT{
        //Strings will help to obtain and save values in the fragment
        String CODE_STRING_TITLE = "CODE_STRING_TITLE";
        String CODE_STRING_MESSAGE = "CODE_STRING_MESSAGE";
        String CODE_STRING_EDIT_STRING_VALUE = "CODE_STRING_EDIT_STRING_VALUE";
        String CODE_STRING_BUTTON_NEW_STATE = "CODE_STRING_BUTTON_NEW_STATE";

        int CODE_ALERT_DIALOG_FRAGMENT_ID_INT  =  CODE_ALERT_DIALOG_FRAGMENT + 1;
    }
    public interface CODES_ALERT_MAIN_RECYCLER{
        int CODE_MAIN_RECYCLER_ID_INT  =  CODES_ALERT_MAIN_RECYCLER + 1;
    }
    public interface CODES_ACTIVITY_LISTS{
        //Strings will help to obtain and save values in the activity
        String CODE_STRING_ACTIVITY_LISTS = "CODE_STRING_ACTIVITY_LISTS";

        int CODE_INT_ACTIVITY_LISTS  =  CODES_ACTIVITY_LISTS + 1;
    }
}