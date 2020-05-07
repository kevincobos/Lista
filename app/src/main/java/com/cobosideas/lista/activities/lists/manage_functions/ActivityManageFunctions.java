package com.cobosideas.lista.activities.lists.manage_functions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cobosideas.lista.activities.lists.ActivityLists;
import com.cobosideas.lista.activities.lists.DataBaseLists;
import com.cobosideas.lista.activities.lists.ModelItemLists;
import com.cobosideas.lista.global.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.cobosideas.lista.R;

public class ActivityManageFunctions extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupValues(savedInstanceState);
        setupDataBaseListsSelectedList();
        setupMainView();
        setupToolBar();
        setupFloatingActionButton();
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
        FloatingActionButton fab = findViewById(R.id.fab_add_management_functions);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        goingBackListsActivity();
        super.onBackPressed();
    }
}
