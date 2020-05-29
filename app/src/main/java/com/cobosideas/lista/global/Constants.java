package com.cobosideas.lista.global;

import com.cobosideas.lista.R;

public class Constants {
    private final static int CODES_ADF_STRING_INPUT = 100;
    private final static int CODE_ADF_STRING_INTEGER_INPUT = 200;
    private final static int CODES_RECYCLER_MAIN = 300;
    private final static int CODES_RECYCLER_LISTS = 400;
    private final static int CODES_RECYCLER_MANAGER_FUNCTIONS = 500;
    private final static int CODES_DIALOG_ICON_CHOOSER = 600;
    private final static int CODES_ACTIVITY_MANAGE_FUNCTIONS = 700;
    private final static int CODE_ADF_REMAINDER_CHOOSER = 800;


    public interface CODES_ACTIVITY_BOOT{
        boolean CODE_BOOLEAN_START_DEBUG = false;
        String CODE_STRING_EXTERNAL_VALUE = "CODE_STRING_EXTERNAL_VALUE";
    }
    public interface CODES_ACTIVITY_LISTA {
        //Strings will help to obtain and save values in the activity
        String CODE_STRING_LISTA_ID = "dbLista";
    }
    public interface CODES_ACTIVITY_EDIT_LISTA {
        //Strings will help to obtain and save values in the activity
        String CODE_STRING_EDIT_LISTA_SELECTED = "CODE_STRING_EDIT_LISTA_SELECTED";
        String[] STRINGS_LISTA_TEMPLATES = new String[]{
                "DEFAULT",
                "SELECTION"
        };
    }
    public interface CODES_ACTIVITY_LISTS{
        //String is added to the begging of each lists database as a identifier
        // will help to obtain and save values in the activity lists cards
        String CODE_STRING_LISTS_ID = "CODE_STRING_LISTS_ID";
    }
    public interface CODES_ACTIVITY_MANAGE_FUNCTIONS {
        //Strings will help to obtain and save values in the activity lists cards
        String MANAGE_FUNCTIONS_DATABASE_ID = "MANAGE_FUNCTIONS_DATABASE_ID";
    }
    public interface CODES_DATABASE_MANAGE_FUNCTIONS {
        String MANAGE_FUNCTIONS_DATABASE_ID = "MANAGE_FUNCTIONS_DATABASE_ID";
        int INT_MONEY_TEMPLATES = 0;
        int INT_REMAINDER_TEMPLATES = 1;
        int INT_ONE_TIME_REMAINDER_TEMPLATES = 2;
        int INT_DAILY_REMAINDER_TEMPLATES = 3;
        int INT_WEEKLY_REMAINDER_TEMPLATES = 4;
        int INT_MONTHLY_REMAINDER_TEMPLATES = 5;
        int INT_YEARLY_REMAINDER_TEMPLATES = 6;
        int INT_CONDITIONAL_REMAINDER_TEMPLATES = 7;

        int[] INT_MANAGE_FUNCTIONS_TEMPLATES_NAMES = new int[]{
                R.string.database_manage_functions_templates_money,
                R.string.database_manage_functions_templates_remainder
        };
    }
    public interface CODES_ACTIVITY_EDIT_LISTS{
        //Strings contains the database name
        String CODE_STRING_EDIT_LISTS_ID_SELECTED = "CODE_STRING_EDIT_LISTS_ID_SELECTED";

        String[] STRINGS_LISTS_TEMPLATES = new String[]{
                "DEFAULT",
                "SIMPLE",
                "IMAGE"
        };
    }
    public interface CODES_CARD_VIEW_LISTA {
        //setup cardView Views
        int CODE_INT_CARD_VIEW_DEFAULT = 0;
        int CODE_INT_CARD_VIEW_LINK = 1;
    }
    public interface CODES_CARD_VIEW_LISTS {
        //setup cardView Views
        int CODE_INT_CARD_VIEW_DEFAULT  =  0;
        int CODE_INT_CARD_VIEW_SIMPLE  =  1;
        int CODE_INT_CARD_VIEW_IMAGE  =  2;
    }
    public interface CODES_RECYCLER_LISTS {
        int CODE_INT_RECYCLER_ACCESS  =  CODES_RECYCLER_LISTS + 1;
        int CODE_INT_RECYCLER_DELETE  =  CODES_RECYCLER_LISTS + 2;
        int CODE_INT_RECYCLER_EDIT  =  CODES_RECYCLER_LISTS + 3;
    }
    public interface CODES_MAIN_RECYCLER {
        int CODE_INT_MAIN_RECYCLER_ACCESS  =  CODES_RECYCLER_MAIN + 1;
        int CODE_INT_MAIN_RECYCLER_DELETE  =  CODES_RECYCLER_MAIN + 2;
        int CODE_INT_MAIN_RECYCLER_PREFERENCES  =  CODES_RECYCLER_MAIN + 3;
    }
    //ALERT DIALOG FRAGMENTS
    public interface CODES_ADF_STRING_INPUT {
        //Strings will help to obtain and save values in the dialog fragment
        String CODE_STRING_EDIT_STRING_VALUE = "CODE_STRING_EDIT_STRING_VALUE";
        String CODE_STRING_BUTTON_NEW_STATE = "CODE_STRING_BUTTON_NEW_STATE";

        int CODE_INT_ALERT_DIALOG_FRAGMENT_ID  =  CODES_ADF_STRING_INPUT + 1;
    }
    public interface CODES_ADF_STRING_INTEGER_INPUT{
        //Strings will help to obtain and save values in the dialog fragment
        String CODE_STRING_EDIT_STRING_VALUE = "CODE_STRING_EDIT_STRING_VALUE";
        String CODE_STRING_EDIT_INTEGER_VALUE = "CODE_STRING_EDIT_INTEGER_VALUE";
        String CODE_STRING_BUTTON_NEW_STATE = "CODE_STRING_BUTTON_NEW_STATE";
    }
    public interface CODES_ADF_NEW_FUNCTION_TYPE_MENU{
        String[] STRINGS_MANAGE_FUNCTIONS_TEMPLATES = new String[]{
                "DEFAULT",
                "MONEY",
                "ONE TIME REMAINDER",
                "DAILY REMAINDER",
                "WEEKLY REMAINDER",
                "MONTHLY REMAINDER",
                "YEARLY REMAINDER",
                "CONDITIONAL REMAINDER",
        };
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
}