package com.cobosideas.lista.activities.manage_functions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cobosideas.lista.R;
import com.cobosideas.lista.activities.lists.ActivityLists;
import com.cobosideas.lista.activities.lists.DataBaseLists;
import com.cobosideas.lista.activities.lists.ModelItemLists;
import com.cobosideas.lista.dialogs.DialogStringIntegerInput;
import com.cobosideas.lista.global.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class ActivityManageFunctions extends AppCompatActivity implements RecyclerManageFunctions.RecyclerManageFunctionsInputListener, DialogStringIntegerInput.DialogStringInputListener {
    /*     CONSTANTS
     *     CODE_STRING_ACTIVITY_LISTA this is the values coming from ActivityLists */
    final String CODE_STRING_LISTA_ID = Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_ID;
    //CODE_INT_ACTIVITY_EDIT_LISTS
    private final String CODE_STRING_EDIT_LISTS_ID_SELECTED = Constants.CODES_ACTIVITY_EDIT_LISTS
            .CODE_STRING_EDIT_LISTS_ID_SELECTED;
    Context gContext; //Context to use globally
    DataBaseLists gDataBaseLists;
    ModelItemLists gModelItemLists;
    Long gSelectedListaItemId;
    Long gSelectedListId;

    DataBaseManageFunctions gDataBaseManageFunctions;//Initialize the DataBase to be able to use it
    ViewModelManageFunctions gViewModelManageFunctions;//Model View View Model
    RecyclerManageFunctions gRecyclerManageFunctions;//Variables for RecyclerView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupValues(savedInstanceState);
        setupDataBaseListsSelectedList();
        setupMainView();
        setupDataAndRecycler();
        setupToolBar();
        setupFloatingActionButton();
    }
    Observer<List<ModelItemManageFunctions>> listUpdateObserver = new Observer<List<ModelItemManageFunctions>>() {
        @Override
        public void onChanged(List<ModelItemManageFunctions> itemLists) {
            setupRecycler(itemLists); //Managing Recycler
        }
    };
    public void setupDataAndRecycler(){
        gDataBaseManageFunctions = new DataBaseManageFunctions(gContext,
                gSelectedListaItemId);//Starting DataBase

        // Setup M V V M, after that jump to onChanged to setupRecycler
        gViewModelManageFunctions = new ViewModelProvider(this).get(ViewModelManageFunctions.class);
        gViewModelManageFunctions.getAllItemsMutableLiveDataModel().observe(this, listUpdateObserver);
        //get all values from Data Base and setup mainRecyclerViewModel
        gViewModelManageFunctions.setAllItemsViewModel(gDataBaseManageFunctions.getAllItems());
    }
    private void setupRecycler(List<ModelItemManageFunctions> itemsManageFunctions){
        //RecyclerView
        RecyclerView recyclerViewLists = findViewById(R.id.rv_activity_manage_functions);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerViewLists.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLists.setLayoutManager(layoutManager);

        //setup recycler: DataBase , MainRecyclerInputListener
        gRecyclerManageFunctions = new RecyclerManageFunctions(itemsManageFunctions, this);
        RecyclerView.Adapter mAdapter = gRecyclerManageFunctions;
        recyclerViewLists.setAdapter(mAdapter);
    }
    private void setupDataBaseListsSelectedList(){
        gDataBaseLists = new DataBaseLists(gContext, gSelectedListaItemId);
        gModelItemLists = gDataBaseLists.getListsItemFromDataBase(gSelectedListId);
    }
    private void setupValues(Bundle savedInstanceState){
        gContext = getApplicationContext();
        if (savedInstanceState == null){
            setGlobalVariables();
        }else{
            getGlobalVariables(savedInstanceState);
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putLong(CODE_STRING_EDIT_LISTS_ID_SELECTED, this.gSelectedListId);
        savedInstanceState.putLong(CODE_STRING_LISTA_ID, this.gSelectedListaItemId);
       super.onSaveInstanceState(savedInstanceState);
    }
    private void setGlobalVariables(){
        this.gSelectedListId = getIntent().getLongExtra(CODE_STRING_EDIT_LISTS_ID_SELECTED, 0);
        this.gSelectedListaItemId = getIntent().getLongExtra(CODE_STRING_LISTA_ID, 0);
    }
    private void getGlobalVariables(Bundle savedInstanceState) {
        this.gSelectedListId = savedInstanceState.getLong(CODE_STRING_EDIT_LISTS_ID_SELECTED, 0);
        this.gSelectedListaItemId = savedInstanceState.getLong(CODE_STRING_LISTA_ID, 0);
    }
    private void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(gModelItemLists.name);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goingBackListsActivity();
            }
        });
    }
    private void setupMainView(){
        setContentView(R.layout.activity_manage_functions);
    }
    private void goingBackListsActivity(){
        Intent intentToAccessActivityLists = new Intent(getApplicationContext(), ActivityLists.class);
        intentToAccessActivityLists.putExtra(CODE_STRING_LISTA_ID, gSelectedListaItemId);
        startActivity(intentToAccessActivityLists);
    }
    private void setupFloatingActionButton(){
        FloatingActionButton fab = findViewById(R.id.fab_add_manage_functions);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Showing Dialog Message
                FragmentManager fm = getSupportFragmentManager();
                DialogStringIntegerInput alertDialogFragment = DialogStringIntegerInput.newInstance();
                alertDialogFragment.show(fm, "alertDialogNewManageFunction");
            }
        });
    }
    @Override
    public void onBackPressed() {
        goingBackListsActivity();
        super.onBackPressed();
    }

    @Override
    public void onInterfaceString(int CODE_INT_RMF_ID, Long stringValue, int itemPosition) {
        final int CODE_INT_RECYCLER_DELETE = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_DELETE;
        final int CODE_INT_RECYCLER_EDIT = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_EDIT;
        final String CODE_STRING_EDIT_LISTS_ID_SELECTED = Constants.CODES_ACTIVITY_EDIT_LISTS.
                CODE_STRING_EDIT_LISTS_ID_SELECTED;
        Intent intent;
        switch (CODE_INT_RMF_ID) {
            case CODE_INT_RECYCLER_EDIT:

                break;
            case CODE_INT_RECYCLER_DELETE:

                break;
        }
    }
    @Override
    public void onInterfaceString(int CODE_ID, String stringValue, int integerValue) {
        //CODE_ALERT_DIALOG_FRAGMENT
        final int CODE_INT_ADF_STRING_INTEGER_ID = Constants.CODES_ADF_STRING_INTEGER_INPUT.
                CODE_INT_ADF_STRING_INTEGER_ID;
        /* Add Item to database*/
        //Create a item
        ModelItemManageFunctions modelItemManageFunctions = new ModelItemManageFunctions();
        modelItemManageFunctions.link = gSelectedListaItemId;
        modelItemManageFunctions.name = stringValue;
        //modelItemManageFunctions.jSON = integerValue + "";

        //modelItemLists.id:? Database assigns the value to this item
        //getting value auto generated
        Long newDataBaseItemId = gDataBaseManageFunctions.addNewItem(modelItemManageFunctions);
        modelItemManageFunctions.id = newDataBaseItemId;
        //create a counter to reorder table later
        modelItemManageFunctions.order = newDataBaseItemId;
        //inserting new item
        gRecyclerManageFunctions.addItemToRecycler(modelItemManageFunctions);
    }
}
