package com.cobosideas.lista.activities.lists;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cobosideas.lista.activities.MainActivity.MainActivityLista;
import com.cobosideas.lista.R;
import com.cobosideas.lista.activities.dagger.ComponentListCard;
import com.cobosideas.lista.activities.dagger.DaggerComponentListCard;
import com.cobosideas.lista.activities.dagger.DaggerListCard;
import com.cobosideas.lista.activities.edit_lists.ActivityEditLists;
import com.cobosideas.lista.activities.manage_functions.ActivityManageFunctions;
import com.cobosideas.lista.dialogs.DialogStringInput;
import com.cobosideas.lista.global.Constants;
import com.cobosideas.lista.activities.MainActivity.room.core.CoreDataBase;
import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityLists extends AppCompatActivity implements
        RecyclerLists.RecyclerListsInputListener, DialogStringInput.DialogStringInputListener {
    /*     CONSTANTS
         CODE_STRING_ACTIVITY_LISTA this is the values coming from MainActivityLista */
    private final String CODE_STRING_LISTA_ID = Constants.CODES_ACTIVITY_LISTA
            .CODE_STRING_LISTA_ID;

    //CODE_ALERT_DIALOG_FRAGMENT
    final int CODE_INT_ADF_ID = Constants.CODES_ALERT_DIALOG_FRAGMENT.CODE_INT_ALERT_DIALOG_FRAGMENT_ID;
    //Database number to combine with database name to access data
    Long gSelectedListaDataBaseNumber;

    Context context; //Context to use globally
    DataBaseLists dataBaseLists;//Initialize the DataBase to be able to use it
    ViewModelLists viewModelLists;//Model View View Model
    RecyclerLists recyclerLists;//Variables for RecyclerView

    DaggerListCard daggerListCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            setSelectedDataBase();
        }else{
            getGlobalVariables(savedInstanceState);
        }
        try {
            setupApplicationView(); //setup toolbar and change the title name
            setupDataAndRecycler();

            /* TODO Setup dagger  */
            setupDagger();
        }catch (Exception error){
            //In case there is a error on on starting the this activity,
            // we are going back to the MainActivity
            goingBackMainActivity();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_lists, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putLong(CODE_STRING_LISTA_ID,
                this.gSelectedListaDataBaseNumber);
        super.onSaveInstanceState(savedInstanceState);
    }
    private void getGlobalVariables(Bundle savedInstanceState) {
        this.gSelectedListaDataBaseNumber = savedInstanceState
                .getLong(CODE_STRING_LISTA_ID, 0);
    }
    private void setSelectedDataBase(){
        //Getting values coming from MainActivityLista
        this.gSelectedListaDataBaseNumber = getIntent().getLongExtra(CODE_STRING_LISTA_ID, 0);
    }
    Observer<List<ModelItemLists>> listUpdateObserver = new Observer<List<ModelItemLists>>() {
        @Override
        public void onChanged(List<ModelItemLists> itemLists) {
            setupRecycler(itemLists); //Managing Recycler
        }
    };
    public void setupDataAndRecycler(){
        dataBaseLists = new DataBaseLists(context, gSelectedListaDataBaseNumber);//Starting DataBase

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
    private ItemRoom getSelectedListaItemFromDataBase(Long selectedItemFromListaDataBase){
        CoreDataBase coreDataBase = new CoreDataBase(this);
        return coreDataBase.getListaItemFromDataBase(selectedItemFromListaDataBase);
    }
    /**
     * getting values sent from MainActivityLista
     * setup Toolbar
     * setup FloatingActionButton
     */
    private void setupApplicationView(){
        context = this.getApplicationContext();
        //This int contains the default picture for the cards
        ItemRoom itemRoom = getSelectedListaItemFromDataBase(gSelectedListaDataBaseNumber);
        //Getting values coming from MainActivityLista
        String listName = itemRoom.name;
        String listDescription = itemRoom.description;
        int listPhotoId = itemRoom.icon;
        setContentView(R.layout.activity_lists);//Setup view
        Toolbar toolbar = findViewById(R.id.tb_activity_lists);//find toolbar and set values
        toolbar.setTitle(listName); //set title on toolbar
        setSupportActionBar(toolbar);
        setupToolbar(listDescription, listPhotoId);
        /* Going back to MainActivityLista */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goingBackMainActivity();
            }
        });
        //setup floatingActionButton to add more items
        setupFloatingActionButton();
    }
    @Override
    public void onBackPressed() {
        goingBackMainActivity();
        super.onBackPressed();
    }
    private void goingBackMainActivity(){
        Intent intentToAccessMainActivity = new Intent(getApplicationContext(), MainActivityLista.class);
        startActivity(intentToAccessMainActivity);
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
        //Showing Dialog Message
        FragmentManager fm = getSupportFragmentManager();
        DialogStringInput alertDialog = DialogStringInput.newInstance();
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
        TextView tv_ToolbarDescription = findViewById(R.id.tv_toolbar_lists_description);
        ImageView iv_ToolbarLogo = findViewById(R.id.iV_collapsing_toolbar_list);
        tv_ToolbarDescription.setText(listDescription);
        iv_ToolbarLogo.setImageResource(listPhotoId);
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
     * @param longValueItemSelected long value is the id of database
     * @param selectedItemFromCardView selected cardView item
     */
    @Override
    public void onInterfaceString(int CODE_ID, Long longValueItemSelected, int selectedItemFromCardView) {
        final int CODE_INT_RECYCLER_ACCESS = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_ACCESS;
        final int CODE_INT_RECYCLER_DELETE = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_DELETE;
        final int CODE_INT_RECYCLER_EDIT = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_EDIT;
        final String CODE_STRING_EDIT_LISTS_ID_SELECTED = Constants.CODES_ACTIVITY_EDIT_LISTS.
                CODE_STRING_EDIT_LISTS_ID_SELECTED;
        Intent intent;
        switch (CODE_ID) {
            case CODE_INT_RECYCLER_EDIT:
                intent = new Intent(this, ActivityEditLists.class);
                intent.putExtra(CODE_STRING_EDIT_LISTS_ID_SELECTED, longValueItemSelected);
                intent.putExtra(CODE_STRING_LISTA_ID, gSelectedListaDataBaseNumber);
                //intent contains de selected lista information
                startActivity(intent);//Let's start the selected activity
                break;
            case CODE_INT_RECYCLER_DELETE:
                /* LongClick on one of the Items in the Recycler */
                //Looking inside database all information
                ModelItemLists modelItemLists = dataBaseLists.getListsItemFromDataBase(longValueItemSelected);
                createLongClickAlertDialog(selectedItemFromCardView, modelItemLists);
                break;
            case CODE_INT_RECYCLER_ACCESS:
                intent = new Intent(this, ActivityManageFunctions.class);
                intent.putExtra(CODE_STRING_EDIT_LISTS_ID_SELECTED, longValueItemSelected);
                intent.putExtra(CODE_STRING_LISTA_ID, gSelectedListaDataBaseNumber);
                //intent contains de selected lista information
                startActivity(intent);//Let's start the selected activity
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
