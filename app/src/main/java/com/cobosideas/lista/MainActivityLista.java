package com.cobosideas.lista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cobosideas.lista.activities.lists.ActivityLists;
import com.cobosideas.lista.dialogs.DialogStringInput;
import com.cobosideas.lista.global.Constants;
import com.cobosideas.lista.global.SharableUtilitiesMessages;
import com.cobosideas.lista.recyclerMain.MainRecycler;
import com.cobosideas.lista.room.core.CoreDataBase;
import com.cobosideas.lista.room.models.ItemRoom;
import com.cobosideas.lista.viewmodel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivityLista extends AppCompatActivity implements DialogStringInput.DialogStringInputListener, MainRecycler.MainRecyclerInputListener {
    /**     CONSTANTS     */
    //CODE_ALERT_DIALOG_FRAGMENT
    final int CODE_ADF_ID_INT = Constants.CODES_ALERT_DIALOG_FRAGMENT.CODE_ALERT_DIALOG_FRAGMENT_ID_INT;
    //CODE_MAIN_RECYCLER_ID_INT
    final int CODE_MR_ID_INT = Constants.CODES_ALERT_MAIN_RECYCLER.CODE_MAIN_RECYCLER_ID_INT;
    //CODE_STRING_ACTIVITY_LISTS
    final String CODE_STRING_ACTIVITY_LISTS = Constants.CODES_ACTIVITY_LISTS.CODE_STRING_ACTIVITY_LISTS;


    Context context; //Context to use globally

    SharableUtilitiesMessages Messages; //Using a class that simplifies the use of: SnackBar and Toast
    //View globalView;//globalView will be use to display the SnackBar

    //Variables for RecyclerView
    MainRecycler mainRecycler;
    //Model View View Model
    MainViewModel mainViewModel;

    CoreDataBase coreDataBase;//Initialize the DataBase to be able to use it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this.getApplicationContext();
        Messages = new SharableUtilitiesMessages(context);//setting up Messages with context


        setContentView(R.layout.activity_main_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCreateNewList();//Creating a new item list using a AlertDialog fragment
            }
        });

        //if (savedInstanceState == null){ } TODO If I need to know lifecycle has changed
        setupDataAndRecycler();
    }

    Observer<List<ItemRoom>> listUpdateObserver = new Observer<List<ItemRoom>>() {
        @Override
        public void onChanged(List<ItemRoom> itemRooms) {
            setupRecycler(itemRooms); //Managing Recycler
        }
    };

    public void setupDataAndRecycler(){
        coreDataBase = new CoreDataBase(context);//Starting DataBase

        /** Setup M V V M after that jump to onChanged to setupRecycler         */
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllItemsMutableLiveDataRoom().observe(this, listUpdateObserver);
        //get all values from Data Base and setup mainRecyclerViewModel
        mainViewModel.setAllItemsViewModel(coreDataBase.getListaItemsFromDataBase());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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



    /** Setup the recycler  */
    private void setupRecycler(List<ItemRoom> itemRoomsArrayList){
        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //setup recycler: DataBase , MainRecyclerInputListener
        mainRecycler = new MainRecycler(itemRoomsArrayList, this);
        RecyclerView.Adapter mAdapter = mainRecycler;
        recyclerView.setAdapter(mAdapter);
    }


    /** Managing Values coming from other fragments
     *
     * @param CODE_ID compare to values from Constants
     * @param stringValue get the string value
     */
    @Override
    public void onInterfaceString(int CODE_ID, String stringValue) {
        switch (CODE_ID){
            case CODE_ADF_ID_INT:
                /** Add Item to database*/
                //Create a item
                ItemRoom itemRoom = new ItemRoom();
                //itemRoom.id:? Database assigns the value to this item
                itemRoom.name = stringValue;
                itemRoom.description = "23 years old";
                itemRoom.photoId = R.drawable.ic_launcher_background;

                mainRecycler.addItemToRecycler(itemRoom);
                coreDataBase.addItemToListaDataBase(itemRoom);
                Messages.message(stringValue);
                break;
            case CODE_MR_ID_INT:
                /** Click on one of the Items in the Recycler*/
                    Messages.message(stringValue);
                Intent intent = new Intent(this, ActivityLists.class);
                int requestCode = 1; // Or some number you choose
                intent.putExtra(CODE_STRING_ACTIVITY_LISTS, stringValue);
                startActivityForResult(intent, requestCode);
                break;
        }


    }
}
