package com.cobosideas.lista.activities.lists;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.cobosideas.lista.MainActivityLista;
import com.cobosideas.lista.activities.dagger.ComponentListCard;
import com.cobosideas.lista.activities.dagger.DaggerComponentListCard;
import com.cobosideas.lista.activities.dagger.DaggerListCard;
import com.cobosideas.lista.dialogs.DialogStringInput;
import com.cobosideas.lista.global.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cobosideas.lista.R;

import java.util.List;

public class ActivityLists extends AppCompatActivity implements RecyclerLists.RecyclerListsInputListener, DialogStringInput.DialogStringInputListener {
    //CODE_INT_RECYCLER_CARD_VIEW
    private final int CODE_INT_CARD_VIEW_DEFAULT = Constants.CODES_LISTS_CARD_VIEW.CODE_INT_CARD_VIEW_DEFAULT;
    private final int CODE_INT_CARD_VIEW_SIMPLE = Constants.CODES_LISTS_CARD_VIEW.CODE_INT_CARD_VIEW_SIMPLE;
    private final int CODE_INT_CARD_VIEW_IMAGE = Constants.CODES_LISTS_CARD_VIEW.CODE_INT_CARD_VIEW_IMAGE;

    //CODE_ALERT_DIALOG_FRAGMENT
    final int CODE_INT_ADF_ID = Constants.CODES_ALERT_DIALOG_FRAGMENT.CODE_INT_ALERT_DIALOG_FRAGMENT_ID;
    //CODE_INT_ACTIVITY_LISTS
    final int CODE_INT_ACTIVITY_LISTS = Constants.CODES_ACTIVITY_LISTS.CODE_INT_ACTIVITY_LISTS;
    //database name combining (CODE_DATABASE_ID and globalId)
    final String CODE_DATABASE_ID =  Constants.CODES_ACTIVITY_LISTS.CODE_DATABASE_ID;
    Long globalId;

    Context context; //Context to use globally
    DataBaseLists dataBaseLists;//Initialize the DataBase to be able to use it
    ViewModelLists viewModelLists;//Model View View Model
    RecyclerLists recyclerLists;//Variables for RecyclerView

    DaggerListCard daggerListCard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();

        setupApplicationView(); //setup toolbar and change the title name

        setupDataAndRecycler();

        /* TODO Setup dagger  */
        setupDagger();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_lists, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private String getDataBaseIdName(){
        /*     CONSTANTS
         CODE_STRING_ACTIVITY_LISTS this is the values coming from MainActivityLista */
        final String CODE_STRING_LISTS_ID = Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_ID;
        //Getting values coming from MainActivityLista
        this.globalId = getIntent().getLongExtra(CODE_STRING_LISTS_ID, 0);
        return CODE_DATABASE_ID + globalId;
    }
    Observer<List<ModelItemLists>> listUpdateObserver = new Observer<List<ModelItemLists>>() {
        @Override
        public void onChanged(List<ModelItemLists> itemLists) {
            setupRecycler(itemLists); //Managing Recycler
        }
    };
    public void setupDataAndRecycler(){
        String dataBaseIdName = getDataBaseIdName();
        dataBaseLists = new DataBaseLists(context, dataBaseIdName);//Starting DataBase

        // Setup M V V M, after that jump to onChanged to setupRecycler
        viewModelLists = new ViewModelProvider(this).get(ViewModelLists.class);
        viewModelLists.getAllItemsMutableLiveDataModel().observe(this, listUpdateObserver);
        //get all values from Data Base and setup mainRecyclerViewModel
        viewModelLists.setAllItemsViewModel(dataBaseLists.getListsItemsFromDataBase());
    }
    /** Setup the recycler
     *
     * @param itemsLists items to be displayed
     */
    private void setupRecycler(List<ModelItemLists> itemsLists){
        //RecyclerView
        RecyclerView recyclerViewLists = findViewById(R.id.rv_activity_lists);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerViewLists.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLists.setLayoutManager(layoutManager);

        //setup recycler: DataBase , MainRecyclerInputListener
        recyclerLists = new RecyclerLists(itemsLists, this);
        RecyclerView.Adapter mAdapter = recyclerLists;
        recyclerViewLists.setAdapter(mAdapter);
    }
    /**
     * getting values sent from MainActivityLista
     * setup Toolbar
     * setup FloatingActionButton
     */
    private void setupApplicationView(){
        /*     CONSTANTS
         CODE_STRING_ACTIVITY_LISTS this is the values coming from MainActivityLista */
        final String CODE_STRING_LISTA_NAME = Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_NAME;
        final String CODE_STRING_LISTA_DESCRIPTION = Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_DESCRIPTION;
        final String CODE_STRING_LISTA_PHOTO_ID = Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_PHOTO_ID;
        //This int contains the default picture for the cards
        final int CODE_DEFAULT_PHOTO_ID = R.drawable.ic_launcher_background;

        //Getting values coming from MainActivityLista
        String listName = getIntent().getStringExtra(CODE_STRING_LISTA_NAME);
        String listDescription = getIntent().getStringExtra(CODE_STRING_LISTA_DESCRIPTION);
        int listPhotoId = getIntent().getIntExtra(CODE_STRING_LISTA_PHOTO_ID, CODE_DEFAULT_PHOTO_ID);

        setContentView(R.layout.activity_lists);//Setup view
        Toolbar toolbar = findViewById(R.id.tb_activity_lists);//find toolbar and set values
        toolbar.setTitle(listName);
        setSupportActionBar(toolbar);
        setupToolbar(listDescription, listPhotoId);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Going back to MainActivityLista */
                Intent intent = new Intent(getApplicationContext(), MainActivityLista.class);
                int requestCode = 1; // Or some number you choose
                startActivityForResult(intent, requestCode);
            }
        });

        //setup floatingActionButton
        setupFloatingActionButton();
    }

    private  void setupFloatingActionButton(){
        FloatingActionButton fab = findViewById(R.id.fab_lists_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCreateNewList();//Creating a new item list using a AlertDialog fragment
            }
        });
    }
    /**Dialog to create a New List and adding to the database*/
    private void showDialogCreateNewList(){
        //Values to setup AlertFragment, so far I'm not using this values
        String title = getStringFromResources(R.string.dialog_getting_string_new_title);
        String message = getStringFromResources(R.string.dialog_getting_string_new_message_name);
        //Showing Dialog Message
        FragmentManager fm = getSupportFragmentManager();
        DialogStringInput alertDialog = DialogStringInput.newInstance(title, message);
        alertDialog.show(fm, "alertDialogNewList");
    }
    /**Getting String Value from String Resources
     *
     * @param resourceName name on file
     * @return return the string that was found
     */
    private String getStringFromResources(int resourceName){
        return getResources().getString(resourceName);
    }
    /**getting toolbar components to set them up
     *
     * @param listDescription description of this list
     * @param listPhotoId this is the photo being show top
     */
    private void setupToolbar(String listDescription, int listPhotoId){
        TextView tvToolbarDescription = findViewById(R.id.tv_toolbar_lists_description);
        ImageView ivToolbarLogo = findViewById(R.id.iV_collapsing_toolbar_list);
        tvToolbarDescription.setText(listDescription);
        ivToolbarLogo.setImageResource(listPhotoId);
    }
    private void setupDagger(){
        ComponentListCard componentListCard = DaggerComponentListCard.create();
        daggerListCard = componentListCard.getDaggerListCard();
    }
    @Override
    public void onInterfaceString(int CODE_ID, String stringValue, String stringDescription) {
        switch (CODE_ID){
            case CODE_INT_ADF_ID:
                String listName = stringValue;
                if (stringValue.equals("")) {
                    listName = getString(R.string.dialog_getting_string_name_empty);
                }
                String description = stringDescription;
                if (stringDescription.equals("")) {
                    description = getString(R.string.dialog_getting_string_description);
                }
                /* Add Item to database*/
                //Create a item
                ModelItemLists modelItemLists = new ModelItemLists();
                modelItemLists.name = listName;
                modelItemLists.description = description;
                modelItemLists.date = System.currentTimeMillis();
                modelItemLists.dateModify = System.currentTimeMillis();
                modelItemLists.function = CODE_INT_CARD_VIEW_DEFAULT;
                //modelItemLists.id:? Database assigns the value to this item
                //getting value auto generated
                Long newDataBaseItemId = dataBaseLists.addItemToListDataBase(modelItemLists);
                modelItemLists.id = newDataBaseItemId;
                //create a counter to reorder table later
                modelItemLists.order = newDataBaseItemId;
                //inserting new item
                recyclerLists.addItemToRecycler(modelItemLists);
                break;
        }
    }
    /** Managing Long Values coming from other fragments
     *
     * @param CODE_ID compare to values from Constants
     * @param longValue long value is the id of database
     * @param selectedItemFromCardView selected cardView item
     */
    @Override
    public void onInterfaceString(int CODE_ID, Long longValue, int selectedItemFromCardView) {
        final int CODE_INT_RECYCLER_ACCESS = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_ACCESS;
        final int CODE_INT_RECYCLER_DELETE = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_DELETE;
        int requestCode = 1; // Or some number you choose
        Intent intent = new Intent(this, ActivityLists.class);
        ModelItemLists modelItemLists;
        Long sessionId;
        String sessionName;
        String sessionDescription;
        int sessionPhotoId;
        switch (CODE_ID) {
            case CODE_INT_RECYCLER_ACCESS:

                break;
            case CODE_INT_RECYCLER_DELETE:
                /* LongClick on one of the Items in the Recycler */
                //Looking inside database all information
                modelItemLists = dataBaseLists.getListaItemFromDataBase(longValue);
                createLongClickAlertDialog(selectedItemFromCardView, modelItemLists);
                break;
        }
    }
    /**Alert Dialog
     * try to delete item selected from cardView in MainActivityLista
     * @param selectedItemFromCardView id of selected item
     * @param modelItemComing  viewModel item from selected item
     */
    private void createLongClickAlertDialog(int selectedItemFromCardView, ModelItemLists modelItemComing){
        final ModelItemLists modelItem = modelItemComing;
        final int selectedItem = selectedItemFromCardView;
        String deleteDuplicate = getString(R.string.alert_dialog_duplicate);
        String deleteMessage = getString(R.string.alert_dialog_delete);
        String cancelMessage = getString(R.string.alert_dialog_delete_message_cancel);
        final String alertDeleteMessage = getString(R.string.alert_dialog_delete_message)
                + modelItem.name;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(alertDeleteMessage)
                .setCancelable(false)
                .setNeutralButton(deleteDuplicate, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*    duplicating item   */
                        modelItem.id = null;
                        Long newDataBaseItemId = dataBaseLists.addItemToListDataBase(modelItem);
                        modelItem.id = newDataBaseItemId;
                        modelItem.order = newDataBaseItemId;
                        modelItem.date = System.currentTimeMillis();
                        modelItem.dateModify = System.currentTimeMillis();
                        recyclerLists.addItemToRecycler(modelItem);
                    }
                })
                .setPositiveButton(deleteMessage, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*    remove item   */
                        dataBaseLists.deleteItemSelected(modelItem);
                        recyclerLists.deleteItemToRecycler(selectedItem, modelItem);
                    }
                })
                .setNegativeButton(cancelMessage, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
