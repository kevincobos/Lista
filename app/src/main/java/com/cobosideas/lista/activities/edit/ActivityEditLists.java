package com.cobosideas.lista.activities.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.cobosideas.lista.R;
import com.cobosideas.lista.activities.lists.ActivityLists;
import com.cobosideas.lista.activities.lists.DataBaseLists;
import com.cobosideas.lista.activities.lists.ModelItemLists;
import com.cobosideas.lista.dialogs.DialogIconChooser;
import com.cobosideas.lista.global.Constants;

public class ActivityEditLists extends AppCompatActivity implements DialogIconChooser.DialogIconChooserListener{
    /*     CONSTANTS
         CODE_STRING_ACTIVITY_LISTA this is the values coming from MainActivityLista */
    final String CODE_STRING_LISTA_ID = Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_ID;
    //CODE_ALERT_DIALOG_FRAGMENT
    private final int CODES_DIALOG_ICON_CHOOSER_ID = Constants.CODES_DIALOG_ICON_CHOOSER.CODES_DIALOG_ICON_CHOOSER_ID;
    //CODE_INT_ACTIVITY_EDIT_LISTS
    private final String CODE_STRING_EDIT_LISTS_ID_SELECTED = Constants.CODES_ACTIVITY_EDIT_LISTS.
            CODE_STRING_EDIT_LISTS_ID_SELECTED;
    private final String CODE_STRING_LISTS_DATABASE_NAME = Constants.CODES_ACTIVITY_EDIT_LISTS
            .CODE_STRING_LISTS_DATABASE_NAME;
    private final String[] CODE_STRINGS_LISTS_TEMPLATES = Constants.CODES_ACTIVITY_EDIT_LISTS
            .STRINGS_LISTS_TEMPLATES;
    Context gContext; //Context to use globally
    ModelItemLists gModelItemLists;
    DataBaseLists gDataBaseLists;

    Long gSelectedListaItemId;
    Long gSelectedListId;
    String gSelectedListaDataBaseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gContext = this.getApplicationContext();
        if (savedInstanceState == null){
            setGlobalVariables();
        }else{
            getGlobalVariables(savedInstanceState);
        }
        setupDataBaseListsSelectedList();
        setupView();
        setupToolBar();
        Button b_SaveChanges = findViewById(R.id.b_save_changes);

        ImageButton ib_SelectedIconListItem = findViewById(R.id.ib_selected_icon_lists_item);
        //TextView tv_Name = findViewById(R.id.tv_name);
        //TextView tv_description = findViewById(R.id.tv_description);
        EditText et_Name = findViewById(R.id.et_name);
        EditText et_Description = findViewById(R.id.et_description);

        et_Name.setText(gModelItemLists.name);
        et_Description.setText(gModelItemLists.description);
        ib_SelectedIconListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFragmentIconChooser();
            }
        });
        b_SaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }
    /**Dialog to create a New List and adding to the database*/
    private void showDialogFragmentIconChooser(){
        //Showing Dialog Fragment
        FragmentManager fm = getSupportFragmentManager();
        DialogIconChooser alertDialog = DialogIconChooser.newInstance();
        alertDialog.show(fm, "alertDialogFragmentIconChooser");
    }
    private void setupDataBaseListsSelectedList(){
        gDataBaseLists = new DataBaseLists(gContext, gSelectedListaDataBaseName);
        gModelItemLists = gDataBaseLists.getListsItemFromDataBase(gSelectedListId);
    }
    private void saveChanges(){
        EditText et_Name = findViewById(R.id.et_name);
        EditText et_Description = findViewById(R.id.et_description);
        gModelItemLists.name = et_Name.getText().toString();
        gModelItemLists.description = et_Description.getText().toString();
        gDataBaseLists.updateSelectedItemListsFromDataBase(gModelItemLists);
    }
    private void setupView(){
        setContentView(R.layout.activity_edit_lists);
        RadioGroup rg_lists_templates = findViewById(R.id.rg_lists_templates);
        for (int cont = 0; cont < CODE_STRINGS_LISTS_TEMPLATES.length; cont++){
            RadioButton rb_template = new RadioButton(this);
            rb_template.setId(cont);
            rb_template.setText(CODE_STRINGS_LISTS_TEMPLATES[cont]);
            int rb_id = rb_template.getId();
            if (rb_id == (gModelItemLists.function)){
                //rb_template.setSelected(true);
                rb_template.setChecked(true);
            }
            //rb_template.setOnClickListener(this);
            rg_lists_templates.addView(rb_template);
        }

        rg_lists_templates.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gModelItemLists.function = checkedId;
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putLong(CODE_STRING_EDIT_LISTS_ID_SELECTED, this.gSelectedListId);
        savedInstanceState.putLong(CODE_STRING_LISTA_ID, this.gSelectedListaItemId);
        savedInstanceState.putString(CODE_STRING_LISTS_DATABASE_NAME, this.gSelectedListaDataBaseName);
        super.onSaveInstanceState(savedInstanceState);
    }
    private void setGlobalVariables(){
        this.gSelectedListId = getIntent().getLongExtra(CODE_STRING_EDIT_LISTS_ID_SELECTED, 0);
        this.gSelectedListaItemId = getIntent().getLongExtra(CODE_STRING_LISTA_ID, 0);
        this.gSelectedListaDataBaseName = getIntent().getStringExtra(CODE_STRING_LISTS_DATABASE_NAME);
    }
    private void getGlobalVariables(Bundle savedInstanceState) {
        this.gSelectedListId = savedInstanceState.getLong(CODE_STRING_EDIT_LISTS_ID_SELECTED);
        this.gSelectedListaItemId = savedInstanceState.getLong(CODE_STRING_LISTA_ID);
        this.gSelectedListaDataBaseName = savedInstanceState.getString(CODE_STRING_LISTS_DATABASE_NAME);
    }
    private void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.tb_activity_edit_lists);
        toolbar.setTitle(gModelItemLists.name);
        setSupportActionBar(toolbar);

        /* Going back to MainActivityLista */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityLists.class);
                intent.putExtra(CODE_STRING_LISTA_ID, gSelectedListaItemId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onInterfaceString(int CODE_ID, int selectedIconToChange) {
        switch (CODE_ID) {
            case CODES_DIALOG_ICON_CHOOSER_ID:
                gModelItemLists.icon = selectedIconToChange;
                break;
        }
    }
}
