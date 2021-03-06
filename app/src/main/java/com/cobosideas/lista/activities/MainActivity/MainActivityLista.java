package com.cobosideas.lista.activities.MainActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cobosideas.lista.R;
import com.cobosideas.lista.activities.edit_lista.ActivityEditLista;
import com.cobosideas.lista.activities.lists.ActivityLists;
import com.cobosideas.lista.activities.manage_functions.FunctionTypeReminder;
import com.cobosideas.lista.dialogs.DialogStringInput;
import com.cobosideas.lista.global.Constants;
import com.cobosideas.lista.global.SharableUtilitiesMessages;
import com.cobosideas.lista.activities.MainActivity.room.core.CoreDataBase;
import com.cobosideas.lista.activities.MainActivity.room.models.ItemRoom;
import com.cobosideas.lista.global.remainders_manager.Notifications;
import com.cobosideas.lista.global.remainders_manager.Notifications2;
import com.cobosideas.lista.global.remainders_manager.ReminderClock;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivityLista extends AppCompatActivity
        implements
        DialogStringInput.DialogStringInputListener,
        MainRecycler.MainRecyclerInputListener{
    /**     CONSTANTS     */
    //CODE_ALERT_DIALOG_FRAGMENT
    final int CODE_INT_ADF_ID = Constants.CODES_ADF_STRING_INPUT.CODE_INT_ALERT_DIALOG_FRAGMENT_ID;
    //CODE_INT_MAIN_RECYCLER
    final int CODE_INT_MAIN_RECYCLER_ACCESS = Constants.CODES_MAIN_RECYCLER.CODE_INT_MAIN_RECYCLER_ACCESS;
    final int CODE_INT_MAIN_RECYCLER_DELETE = Constants.CODES_MAIN_RECYCLER.CODE_INT_MAIN_RECYCLER_DELETE;
    //CODE_STRING_ACTIVITY_LISTS
    final String CODE_STRING_LISTA_ID = Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_ID;
    Context gContext; //Context to use globally
    SharableUtilitiesMessages Messages; //Using a class that simplifies the use of: SnackBar and Toast
    MainRecycler mainRecycler;//Variables for RecyclerView
    MainViewModel mainViewModel;//Model View View Model
    CoreDataBase coreDataBase;//Initialize the DataBase to be able to use it
    long timeStamp = 0;
    long timeToWaite = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gContext = this.getApplicationContext();
        Messages = new SharableUtilitiesMessages(gContext);//setting up Messages with context

        setContentView(R.layout.activity_main_lista);
        Toolbar toolbar = findViewById(R.id.tb_main_activity_lista);
        setSupportActionBar(toolbar);

        setupFloatingActionButton();
        setupDataAndRecycler();
    }
    Observer<List<ItemRoom>> listaUpdateObserver = new Observer<List<ItemRoom>>() {
        @Override
        public void onChanged(List<ItemRoom> itemRooms) {
            setupRecycler(itemRooms); //Managing Recycler
        }
    };
    private  void setupFloatingActionButton(){
        FloatingActionButton fab = findViewById(R.id.fab_lista_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCreateNewList();//Creating a new item list using a AlertDialog fragment
            }
        });
    }
    public void setupDataAndRecycler(){
        coreDataBase = new CoreDataBase(gContext);//Starting DataBase
        // Setup M V V M, after that jump to onChanged to setupRecycler
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllItemsMutableLiveDataRoom().observe(this, listaUpdateObserver);
        //get all values from Data Base and setup mainRecyclerViewModel
        mainViewModel.setAllItemsViewModel(coreDataBase.getListaItemsFromDataBase());
    }

    /**
     * Only doubleClick will show the menu
     */
    @Override
    public void onBackPressed() {
        long newTimeStamp = System.currentTimeMillis();
        if ((newTimeStamp - timeStamp) < timeToWaite){
            createAlertDialogExit();
        }else{
            timeStamp = newTimeStamp;
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context,
                             @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_activity_main_settings) {
            showingAllNotifications();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void createNotification(
            int  notificationId,
            String  channelId,
            int choosePriority,
            String title,
            String message){
        Notification notification;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = title;
                String description = message;
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
                notificationManager.notify();
            notification =
                    new Notification.Builder(gContext, channelId)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setSmallIcon(R.drawable.app_developer)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            //.setPriority(choosePriority)
                            .build();
        }else{
            notification =
                    new NotificationCompat.Builder(gContext, channelId)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setSmallIcon(R.drawable.app_developer)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            //.setPriority(choosePriority)
                            .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(notificationId, notification);
        }


    }
    //TODO finish this
    private void showingAllNotifications(){
        ReminderClock remainderClock = new ReminderClock(gContext);
        List<FunctionTypeReminder> listReminders = remainderClock.getAllRemindersTodayTime();
        String listNamesToShow = "";
        for (int cont = 0; cont < listReminders.size(); cont++){
            listNamesToShow = listNamesToShow + listReminders.get(cont).getSelectedTime() + "\n";
        }
        //int totalLists = remainderClock.getAllLists();
        //int totalListsItems = remainderClock.getAllListsItems();
        Toast.makeText(gContext,"totalListas{\n"+listNamesToShow+"}", Toast.LENGTH_LONG).show();
        Notifications2 notification = new Notifications2(gContext);
        notification.showNotification();

        //createNotification(1,"Lista-one", 4, "Lista - Title","Lista - Message");
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
    /** Setup the recycler
     *
     * @param itemRoomsArrayList items to be displayed
     */
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
    public void onInterfaceString(int CODE_ID, String stringValue, String stringDescription) {
        switch (CODE_ID){
            case CODE_INT_ADF_ID:
                String description = stringDescription;
                if (stringDescription.equals("")) {
                    description = getString(R.string.dialog_getting_string_description);
                }
                /* Add Item to database*/
                //Create a item
                ItemRoom itemRoom = new ItemRoom();
                itemRoom.name = stringValue;
                itemRoom.description = description;
                //itemRoom.id:? Database assigns the value to this item
                //getting value auto generated
                itemRoom.id = coreDataBase.addItemToListaDataBase(itemRoom);
                //inserting new item
                mainRecycler.addItemToRecycler(itemRoom);
                Messages.message(stringValue);
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
        final int CODE_INT_MAIN_RECYCLER_PREFERENCES = Constants.CODES_MAIN_RECYCLER.
                CODE_INT_MAIN_RECYCLER_PREFERENCES;
        ItemRoom modelViewItem;
        Long sessionId;
        switch (CODE_ID){
            case CODE_INT_MAIN_RECYCLER_ACCESS:

                /* Click on one of the Items in the Recycler */
                //Looking inside database all information
                modelViewItem = coreDataBase.getListaItemFromDataBase(longValue);
                sessionId = modelViewItem.id;

                Intent intent = new Intent(this, ActivityLists.class);
                //intent contains de selected lista id
                intent.putExtra(CODE_STRING_LISTA_ID, sessionId);
                startActivity(intent);//Let's start the selected activity
                break;
            case CODE_INT_MAIN_RECYCLER_DELETE:
                /* LongClick on one of the Items in the Recycler */
                //Looking inside database all information
                modelViewItem = coreDataBase.getListaItemFromDataBase(longValue);
                createAlertDialogDelete(selectedItemFromCardView, modelViewItem);
                break;
            case CODE_INT_MAIN_RECYCLER_PREFERENCES:
                /* Click on preferences in selected Item in the Recycler */
                //Looking inside database all information
                modelViewItem = coreDataBase.getListaItemFromDataBase(longValue);
                sessionId = modelViewItem.id;
                Intent intentEditLista = new Intent(this, ActivityEditLista.class);
                //intent contains de selected lista id
                intentEditLista.putExtra(CODE_STRING_LISTA_ID, sessionId);
                startActivity(intentEditLista);//Let's edit the selected Lista
                break;
        }
    }

    /**Alert Dialog
     * try to delete item selected from cardView in MainActivityLista
     * @param selectedItemFromCardView id of selected item
     * @param itemRoomComing  viewModel item from selected item
     */
    private void createAlertDialogDelete(int selectedItemFromCardView, ItemRoom itemRoomComing){
        final ItemRoom itemRoom = itemRoomComing;
        final int selectedItem = selectedItemFromCardView;
        String deleteMessage = getString(R.string.alert_dialog_delete);
        String cancelMessage = getString(R.string.alert_dialog_delete_message_cancel);
        final String alertDeleteMessage = getString(R.string.alert_dialog_delete_message)
                + itemRoom.name;
        final String alertDeleteSuccess = getString(R.string.alert_dialog_delete_success);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(alertDeleteMessage)
                .setCancelable(false)
                .setPositiveButton(deleteMessage, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*    remove item   */
                        coreDataBase.deleteItemSelected(itemRoom);
                        mainRecycler.deleteItemToRecycler(selectedItem, itemRoom);
                        Messages.message(alertDeleteSuccess);
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
    /**
     * menu to exit or stay
     */
    private void createAlertDialogExit(){
        final String exitMessage = getString(R.string.alert_dialog_exit_message);
        final String buttonExit = getString(R.string.alert_dialog_exit_button);
        final String cancelMessage = getString(R.string.alert_dialog_exit_cancel_button);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(exitMessage)
                .setCancelable(false)
                .setPositiveButton(buttonExit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int pid = android.os.Process.myPid();
                        android.os.Process.killProcess(pid);
                        finishAndRemoveTask();
                        System.exit(0);
                        finish();
                        finishAffinity();
                        System.exit(0);
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
