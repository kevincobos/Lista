package com.cobosideas.lista.global;

public class Constants {
    //100 = CODES_ALERT_DIALOG_FRAGMENT
    private final static int CODE_ALERT_DIALOG_FRAGMENT = 100;
    //200 = CODES_MAIN_RECYCLER
    private final static int CODES_MAIN_RECYCLER = 200;
    //300 = CODES_ACTIVITY_LIST
    private final static int CODES_ACTIVITY_LISTA = 300;
    //200 = CODES_ACTIVITY_LISTS_CARDS
    private final static int CODES_ACTIVITY_LISTS = 400;
    //500 = CODES_RECYCLER_LISTS
    private final static int CODES_RECYCLER_LISTS = 500;
    //600 = CODES_RECYCLER_LISTS
    private final static int CODES_LISTS_CARD_VIEW = 600;
    //700 = CODES_RECYCLER_LISTS
    private final static int CODES_ACTIVITY_EDIT_LISTS = 700;
    //800 = CODES_ALERT_DIALOG_FRAGMENT
    private final static int CODES_DIALOG_ICON_CHOOSER = 800;

    public interface CODES_ALERT_DIALOG_FRAGMENT{
        //Strings will help to obtain and save values in the dialog fragment
        String CODE_STRING_TITLE = "CODE_STRING_TITLE";
        String CODE_STRING_MESSAGE = "CODE_STRING_MESSAGE";
        String CODE_STRING_EDIT_STRING_VALUE = "CODE_STRING_EDIT_STRING_VALUE";
        String CODE_STRING_BUTTON_NEW_STATE = "CODE_STRING_BUTTON_NEW_STATE";

        int CODE_INT_ALERT_DIALOG_FRAGMENT_ID  =  CODE_ALERT_DIALOG_FRAGMENT + 1;
    }
    public interface CODES_DIALOG_ICON_CHOOSER{
        //Strings will help to obtain and save values in the dialog fragment icon chooser
        String CODE_STRING_ICON_CHOOSER_CANCEL = "CODE_STRING_ICON_CHOOSER_CANCEL";
        String CODE_STRING_ICON_CHOOSER_SELECTED = "CODE_STRING_ICON_CHOOSER_SELECTED";

        int CODES_DIALOG_ICON_CHOOSER_ID  =  CODES_DIALOG_ICON_CHOOSER + 1;
    }
    public interface CODES_MAIN_RECYCLER {
        int CODE_INT_MAIN_RECYCLER_ACCESS  =  CODES_MAIN_RECYCLER + 1;
        int CODE_INT_MAIN_RECYCLER_DELETE  =  CODES_MAIN_RECYCLER + 2;
    }
    public interface CODES_ACTIVITY_LISTA {
        //Strings will help to obtain and save values in the activity
        String CODE_STRING_LISTA_ID = "LISTA_ID";
        String CODE_STRING_LISTA_NAME = "CODE_STRING_LISTA_NAME";
        String CODE_STRING_LISTA_DESCRIPTION = "CODE_STRING_LISTA_DESCRIPTION";
        String CODE_STRING_LISTA_PHOTO_ID = "CODE_STRING_LISTA_PHOTO_ID";

        int CODE_INT_ACTIVITY_LISTA  =  CODES_ACTIVITY_LISTA + 1;
    }
    public interface CODES_RECYCLER_LISTS {
        int CODE_INT_RECYCLER_ID  =  CODES_RECYCLER_LISTS + 1;
        int CODE_INT_RECYCLER_ACCESS  =  CODES_RECYCLER_LISTS + 2;
        int CODE_INT_RECYCLER_DELETE  =  CODES_RECYCLER_LISTS + 3;
    }
    public interface CODES_LISTS_CARD_VIEW {
        //setup cardView Views
        int CODE_INT_CARD_VIEW_DEFAULT  =  0;
        int CODE_INT_CARD_VIEW_SIMPLE  =  1;
        int CODE_INT_CARD_VIEW_IMAGE  =  2;
    }
    public interface CODES_ACTIVITY_LISTS{
        //Strings will help to obtain and save values in the activity lists cards
        String CODE_DATABASE_ID = "CODE_STRING_LISTS_ID";

        String CODE_STRING_LISTS_ID = "CODE_STRING_LISTS_ID";
        String CODE_STRING_LISTS_NAME = "CODE_STRING_LISTS_NAME";
        String CODE_STRING_LISTS_DESCRIPTION = "CODE_STRING_LISTS_DESCRIPTION";
        String CODE_STRING_LISTS_PHOTO_ID = "CODE_STRING_LISTS_PHOTO_ID";

        int CODE_INT_ACTIVITY_LISTS  =  CODES_ACTIVITY_LISTS + 1;
    }
    public interface CODES_ACTIVITY_EDIT_LISTS{
        //Strings will help to obtain and save values in the activity lists cards
        String CODE_STRING_EDIT_LISTS_ID_SELECTED = "CODE_STRING_EDIT_LISTS_ID_SELECTED";
        String CODE_STRING_LISTS_DATABASE_NAME = "CODE_STRING_LISTS_DATABASE_NAME";

        String[] STRINGS_LISTS_TEMPLATES = new String[]{
                "DEFAULT",
                "SIMPLE",
                "IMAGE"
        };

        int CODE_INT_EDIT_LISTS  =  CODES_ACTIVITY_EDIT_LISTS + 1;
    }
}