package com.cobosideas.lista.activities.lists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cobosideas.lista.MainActivityLista;
import com.cobosideas.lista.activities.dagger.ComponentListCard;
import com.cobosideas.lista.activities.dagger.DaggerComponentListCard;
import com.cobosideas.lista.activities.dagger.DaggerListCard;
import com.cobosideas.lista.dialogs.DialogStringInput;
import com.cobosideas.lista.global.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
        setContentView(R.layout.activity_lists);
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

        //find toolbar and set values
        Toolbar toolbar = findViewById(R.id.tb_activity_lists);
        toolbar.setTitle(listName);
        setSupportActionBar(toolbar);
        setupToolbar(listDescription, listPhotoId);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Going back to MainActivityLista */
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
    /** Managing Long Values coming from other fragments
     *
     * @param CODE_ID compare to values from Constants
     * @param longValue get the string value
     */
    @Override
    public void onInterfaceString(int CODE_ID, Long longValue) {
        switch (CODE_ID){
            case CODE_INT_ACTIVITY_LISTS:
                /* Click on one of the Items in the Recycler */

                break;
        }


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
}
