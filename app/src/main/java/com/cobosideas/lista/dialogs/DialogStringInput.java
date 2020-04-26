package com.cobosideas.lista.dialogs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

public class DialogStringInput extends DialogFragment {

    /*
        Trying to create a connection request sender for action
    */
    //CODE_ALERT_DIALOG_FRAGMENT
    private final int CODE_ADF_ID_INT = Constants.CODES_ALERT_DIALOG_FRAGMENT.CODE_ALERT_DIALOG_FRAGMENT_ID_INT;
    private final String CODE_STRING_TITLE = Constants.CODES_ALERT_DIALOG_FRAGMENT.CODE_STRING_TITLE;
    private final String CODE_STRING_MESSAGE = Constants.CODES_ALERT_DIALOG_FRAGMENT.CODE_STRING_MESSAGE;
    private final String CODE_STRING_EDIT_STRING_VALUE = Constants.CODES_ALERT_DIALOG_FRAGMENT.CODE_STRING_EDIT_STRING_VALUE;
    private final String CODE_STRING_BUTTON_NEW_STATE = Constants.CODES_ALERT_DIALOG_FRAGMENT.CODE_STRING_BUTTON_NEW_STATE;

    public interface DialogStringInputListener {
        void onInterfaceString(int CODE_ID, String stringValue);
    }

    //Global values to show on AlertDialog
    private String title, message, stringValue;

    //we will extract the string from et_stringValue to send it to the Activity
    private EditText et_stringValue;
    //?disable:enable button newList
    private boolean buttonNewListState = false;

    //Buttons
    private Button b_Cancel, b_CreateNewList;
    public DialogStringInput(){

    }
    public static DialogStringInput newInstance(String titleValue, String messageValue) {
        DialogStringInput dialogFragment = new DialogStringInput();
        //Setting values on Arguments
        Bundle args = new Bundle();
        args.putString("title", titleValue);
        args.putString("message", messageValue);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    private String getStringFromResources(int resourceName){
        return getResources().getString(resourceName);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {

        //This are not visible
        savedInstanceState.putString(CODE_STRING_TITLE, title);
        savedInstanceState.putString(CODE_STRING_MESSAGE, message);

        //This are use
        savedInstanceState.putString(CODE_STRING_EDIT_STRING_VALUE, stringValue);
        savedInstanceState.putBoolean(CODE_STRING_BUTTON_NEW_STATE, buttonNewListState);

        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make sure getArguments() is not null, if null show different text
        if (getArguments() != null){
            title = getArguments().getString(CODE_STRING_TITLE);
            message = getArguments().getString(CODE_STRING_MESSAGE);
        }else{
            title = getStringFromResources(R.string.dialog_getting_string_title_error);
            message = getStringFromResources(R.string.dialog_getting_string_message_error);
        }
        if (savedInstanceState != null) {
            title = savedInstanceState.getString(CODE_STRING_TITLE);
            message = savedInstanceState.getString(CODE_STRING_MESSAGE);
            stringValue = savedInstanceState.getString(CODE_STRING_EDIT_STRING_VALUE);
            buttonNewListState = savedInstanceState.getBoolean(CODE_STRING_BUTTON_NEW_STATE);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_string_input , container, false);

        b_Cancel = view.findViewById(R.id.b_cancel);
        b_CreateNewList = view.findViewById(R.id.b_new_list);
        et_stringValue	= view.findViewById(R.id.et_new_list_name);

        loadButtons();
        loadEditorText();

        //getDialog().setTitle(title);
        return view;
    }

    private void loadButtons(){
        b_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        b_CreateNewList.setEnabled(buttonNewListState);
        b_CreateNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on success
                String valueToSendToActivity = et_stringValue.getText().toString();
                //send editText value to Activity
                DialogStringInputListener listener = (DialogStringInputListener) getActivity();
                if (listener != null) {
                    listener.onInterfaceString(CODE_ADF_ID_INT, valueToSendToActivity);
                }else{
                    String errorMessage = getResources().getString(R.string.dialog_getting_string_error);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
                // Close the dialog and return back to the parent activity
                dismiss();
            }
        });

    }
    private void loadEditorText(){
        et_stringValue.setText(title);
        et_stringValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,	int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                stringValue = et_stringValue.getText().toString();
                buttonNewListState = !stringValue.equals("");
                b_CreateNewList.setEnabled(buttonNewListState);
            }
        });
    }
}
