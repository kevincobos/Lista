package com.cobosideas.lista.activities.edit_lista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cobosideas.lista.MainActivityLista;
import com.cobosideas.lista.dialogs.DialogIconChooser;
import com.cobosideas.lista.global.Constants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cobosideas.lista.R;
import com.cobosideas.lista.room.core.CoreDataBase;
import com.cobosideas.lista.room.models.ItemRoom;

public class ActivityEditLista extends AppCompatActivity implements DialogIconChooser.DialogIconChooserListener{
    /*     CONSTANTS
         CODE_STRING_ACTIVITY_LISTA this is the values coming from MainActivityLista */
    final String CODE_STRING_LISTA_ID = Constants.CODES_ACTIVITY_LISTA.CODE_STRING_LISTA_ID;

    //CODE_ALERT_DIALOG_FRAGMENT
    private final int CODES_DIALOG_ICON_CHOOSER_ID = Constants.CODES_DIALOG_ICON_CHOOSER.CODES_DIALOG_ICON_CHOOSER_ID;

    //CODE_INT_ACTIVITY_EDIT_LISTS
    private final String CODE_STRING_EDIT_LISTA_SELECTED = Constants.CODES_ACTIVITY_EDIT_LISTA.
            CODE_STRING_EDIT_LISTA_SELECTED;
    private final String[] STRINGS_LISTA_TEMPLATES = Constants.CODES_ACTIVITY_EDIT_LISTA
            .STRINGS_LISTA_TEMPLATES;
    Context gContext; //Context to use globally
    CoreDataBase gCoreDataBase;
    ItemRoom gItemListaModel;

    Long gSelectedListaItemId;

    ImageView ib_SelectedIconListItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gContext = this.getApplicationContext();
        if (savedInstanceState == null){
            setGlobalVariables();
        }else{
            getGlobalVariables(savedInstanceState);
        }
        //TODO when rotation we loose values  + images look into livedata and previewIconChange(int selectedIconToChange)
        setupCoreDataBaseSelectedLista();
        setupView();
        setupToolBar();
        Button b_SaveChanges = findViewById(R.id.b_save_changes);

        //setup the main ImageButton with the selected Image
        ib_SelectedIconListItem = findViewById(R.id.ib_selected_icon_lista_item);
        ib_SelectedIconListItem.setImageDrawable(this.getDrawable(gItemListaModel.icon));
        //TextView tv_Name = findViewById(et_lista_name);
        //TextView tv_description = findViewById(et_lista_description);
        EditText et_Name = findViewById(R.id.et_lista_name);
        EditText et_Description = findViewById(R.id.et_lista_description);

        et_Name.setText(gItemListaModel.name);
        et_Description.setText(gItemListaModel.description);
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
    private void setGlobalVariables(){
        this.gSelectedListaItemId = getIntent().getLongExtra(CODE_STRING_LISTA_ID, 0);
    }
    /**Dialog to create a New List and adding to the database*/
    private void showDialogFragmentIconChooser(){
        //Showing Dialog Fragment
        FragmentManager fm = getSupportFragmentManager();
        DialogIconChooser alertDialog = DialogIconChooser.newInstance();
        alertDialog.show(fm, "alertDialogFragmentIconChooser");
    }
    private void setupCoreDataBaseSelectedLista(){
        gCoreDataBase = new CoreDataBase(gContext);
        gItemListaModel = gCoreDataBase.getListaItemFromDataBase(gSelectedListaItemId);
    }
    private void saveChanges(){
        EditText et_Name = findViewById(R.id.et_lista_name);
        EditText et_Description = findViewById(R.id.et_lista_description);
        gItemListaModel.name = et_Name.getText().toString();
        gItemListaModel.description = et_Description.getText().toString();
        gCoreDataBase.updateSelectedItem(gItemListaModel);
    }
    private void setupView(){
        setContentView(R.layout.activity_edit_listas);
        RadioGroup rg_lists_templates = findViewById(R.id.rg_lista_templates);
        for (int cont = 0; cont < STRINGS_LISTA_TEMPLATES.length; cont++){
            RadioButton rb_template = new RadioButton(this);
            rb_template.setId(cont);
            rb_template.setText(STRINGS_LISTA_TEMPLATES[cont]);
            int rb_id = rb_template.getId();
            if (rb_id == (gItemListaModel.function)){
                //rb_template.setSelected(true);
                rb_template.setChecked(true);
            }
            //rb_template.setOnClickListener(this);
            rg_lists_templates.addView(rb_template);
        }

        rg_lists_templates.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gItemListaModel.function = checkedId;
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putLong(CODE_STRING_LISTA_ID, this.gSelectedListaItemId);
        super.onSaveInstanceState(savedInstanceState);
    }
    private void getGlobalVariables(Bundle savedInstanceState) {
        this.gSelectedListaItemId = savedInstanceState.getLong(CODE_STRING_LISTA_ID);
    }
    private void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.tb_activity_edit_lista);
        toolbar.setTitle(gItemListaModel.name);
        setSupportActionBar(toolbar);

        /* Going back to MainActivityLista */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivityLista.class);
                intent.putExtra(CODE_STRING_LISTA_ID, gSelectedListaItemId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onInterfaceString(int CODE_ID, int selectedIconToChange) {
        switch (CODE_ID) {
            case CODES_DIALOG_ICON_CHOOSER_ID:
                //update the selected list icon image and replace the imageButton
                previewIconChange(selectedIconToChange);
                break;
        }
    }
    private void previewIconChange(int selectedIconToChange){
        gItemListaModel.icon = selectedIconToChange;
        ib_SelectedIconListItem.setImageDrawable(this.getDrawable(selectedIconToChange));
    }
}
