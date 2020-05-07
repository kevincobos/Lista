package com.cobosideas.lista.global;

import com.cobosideas.lista.R;

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
    //900 = CODES_ACTIVITY_EDIT_LISTA
    private final static int CODES_ACTIVITY_EDIT_LISTA = 900;

    public interface CODES_ACTIVITY_BOOT{
        boolean CODE_BOOLEAN_START_DEBUG = false;
        String CODE_STRING_EXTERNAL_VALUE = "CODE_STRING_EXTERNAL_VALUE";
    }
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
        int[] DRAWABLE_ICONS_FRUITS = new int[]{
                R.drawable.fruits_apple_color,
                R.drawable.fruits_banana_color,
                R.drawable.fruits_blackberry_color,
                R.drawable.fruits_cherries_color,
                R.drawable.fruits_grapes_color,
                R.drawable.fruits_green_apple_color,
                R.drawable.fruits_orange_color,
                R.drawable.fruits_pineaple,
                R.drawable.fruits_peach_color,
                R.drawable.fruits_pear_color,
                R.drawable.fruits_strawberry_color,
                R.drawable.fruits_lemon_color,
                R.drawable.fruits_watermelon_color
        };
        int[] DRAWABLE_ICONS_VEGETABLES = new int[]{
                R.drawable.vegetables_carrot_color,
                R.drawable.vegetables_jalapeno_color,
                R.drawable.vegetables_onion_color,
                R.drawable.vegetables_radish_color,
                R.drawable.vegetables_tomato_color,
                R.drawable.vegetables_yellow_pepper_color
        };
        int[] DRAWABLE_ICONS_FINANCES = new int[]{
                R.drawable.finances_bank,
                R.drawable.finances_sign,
                R.drawable.finances_piggy_bank
        };
        int[] DRAWABLE_ICONS_INSURANCE = new int[]{
                R.drawable.insurance_bills,
                R.drawable.insurance_car,
                R.drawable.insurance_dental,
                R.drawable.insurance_money,
                R.drawable.insurance_nurse
        };
        int[] DRAWABLE_ICONS_ENTERTAINMENT = new int[]{
                R.drawable.entertainment_netflix,
                R.drawable.entertainment_tv_clasic,
                R.drawable.entertainment_tv_simple
        };
        int[] DRAWABLE_ICONS_CAR = new int[]{
                R.drawable.car_yellow,
                R.drawable.car_orange,
                R.drawable.car_gray
        };
        int[] DRAWABLE_ICONS_HOUSE = new int[]{
            R.drawable.house_clasic,
            R.drawable.house_classic_trees,
            R.drawable.house_simple
        };
        int[] DRAWABLE_ICONS_TRASH = new int[]{
                R.drawable.trash_container_green,
                R.drawable.trash_bag,
                R.drawable.trash_car,
                R.drawable.trash_cart,
                R.drawable.trash_container_orange
        };
        int[][] DRAWABLE_ICONS = new int[][]{
                DRAWABLE_ICONS_FRUITS,
                DRAWABLE_ICONS_VEGETABLES,
                DRAWABLE_ICONS_FINANCES,
                DRAWABLE_ICONS_INSURANCE,
                DRAWABLE_ICONS_ENTERTAINMENT,
                DRAWABLE_ICONS_CAR,
                DRAWABLE_ICONS_HOUSE,
                DRAWABLE_ICONS_TRASH
        };
    }
    public interface CODES_MAIN_RECYCLER {
        int CODE_INT_MAIN_RECYCLER_ACCESS  =  CODES_MAIN_RECYCLER + 1;
        int CODE_INT_MAIN_RECYCLER_DELETE  =  CODES_MAIN_RECYCLER + 2;
        int CODE_INT_MAIN_RECYCLER_PREFERENCES  =  CODES_MAIN_RECYCLER + 3;
    }
    public interface CODES_LISTA_CARD_VIEW {
        //setup cardView Views
        int CODE_INT_CARD_VIEW_DEFAULT = 0;
        int CODE_INT_CARD_VIEW_LINK = 1;
    }
    public interface CODES_ACTIVITY_LISTA {
        //Strings will help to obtain and save values in the activity
        String CODE_STRING_LISTA_ID = "LISTA_ID";

        int CODE_INT_ACTIVITY_LISTA  =  CODES_ACTIVITY_LISTA + 1;
    }
    public interface CODES_ACTIVITY_EDIT_LISTA {
        //Strings will help to obtain and save values in the activity
        String CODE_STRING_EDIT_LISTA_SELECTED = "CODE_STRING_EDIT_LISTA_SELECTED";

        int CODE_INT_ACTIVITY_EDIT_LISTA  =  CODES_ACTIVITY_EDIT_LISTA + 1;

        String[] STRINGS_LISTA_TEMPLATES = new String[]{
                "DEFAULT",
                "SELECTION"
        };
    }
    public interface CODES_RECYCLER_LISTS {
        int CODE_INT_RECYCLER_ACCESS  =  CODES_RECYCLER_LISTS + 1;
        int CODE_INT_RECYCLER_DELETE  =  CODES_RECYCLER_LISTS + 2;
        int CODE_INT_RECYCLER_EDIT  =  CODES_RECYCLER_LISTS + 3;
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

        String[] STRINGS_LISTS_TEMPLATES = new String[]{
                "DEFAULT",
                "SIMPLE",
                "IMAGE"
        };

        int CODE_INT_EDIT_LISTS  =  CODES_ACTIVITY_EDIT_LISTS + 1;
    }
}