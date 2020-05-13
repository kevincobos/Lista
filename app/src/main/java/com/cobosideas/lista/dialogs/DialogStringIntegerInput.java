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
import com.cobosideas.lista.activities.manage_functions.FunctionTypeMoneyAmount;
import com.cobosideas.lista.global.Constants;

public class DialogStringIntegerInput extends DialogFragment{
    //CODE_ALERT_DIALOG_FRAGMENT
    private final int INT_MONEY_TEMPLATES = Constants.CODES_DATABASE_MANAGE_FUNCTIONS.
            INT_MONEY_TEMPLATES;
    private final String CODE_STRING_EDIT_STRING_VALUE = Constants.CODES_ADF_STRING_INTEGER_INPUT.
            CODE_STRING_EDIT_STRING_VALUE;
    private final String CODE_STRING_EDIT_INTEGER_VALUE = Constants.CODES_ADF_STRING_INTEGER_INPUT.
            CODE_STRING_EDIT_INTEGER_VALUE;
    private final String CODE_STRING_BUTTON_NEW_STATE = Constants.CODES_ADF_STRING_INTEGER_INPUT.
            CODE_STRING_BUTTON_NEW_STATE;

    /*        Trying to create a connection request sender for action    */
    public interface DialogStringInputListener {
        void onInterfaceNewFunctionType(int CODE_FUNCTION_ID, Object objectFunctionType);
    }
    //Global values to show on AlertDialog
    private String stringValue;
    private int integerValue;
    //we will extract the string from et_stringValue to send it to the Activity
    private EditText et_StringValue;
    //we will extract the string from et_stringDescription to send it to the Activity
    private EditText et_IntegerValue;
    //Buttons
    private Button b_Cancel, b_CreateNewManageFunctions;
    //buttonNewListState state
    private boolean buttonNewListState = false;
    public DialogStringIntegerInput(){        }
    public static DialogStringIntegerInput newInstance() {
        DialogStringIntegerInput dialogFragment = new DialogStringIntegerInput();
        //Setting values on Arguments
        Bundle args = new Bundle();
        dialogFragment.setArguments(args);
        return dialogFragment;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        //This are use
        savedInstanceState.putString(CODE_STRING_EDIT_STRING_VALUE, stringValue);
        savedInstanceState.putBoolean(CODE_STRING_BUTTON_NEW_STATE, buttonNewListState);

        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make sure getArguments() is not null, if null show different text
        if (savedInstanceState != null) {
            stringValue = savedInstanceState.getString(CODE_STRING_EDIT_STRING_VALUE);
            buttonNewListState = savedInstanceState.getBoolean(CODE_STRING_BUTTON_NEW_STATE);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_string_integer_input , container, false);

        b_Cancel = view.findViewById(R.id.b_cancel);
        b_CreateNewManageFunctions = view.findViewById(R.id.b_new_manage_functions);
        et_StringValue = view.findViewById(R.id.et_new_string);
        et_IntegerValue= view.findViewById(R.id.et_new_value);

        loadButtons();
        loadEditorTexts();
        return view;
    }

    private void loadButtons(){
        b_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        b_CreateNewManageFunctions.setEnabled(buttonNewListState);
        b_CreateNewManageFunctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on success
                //String valueToSendToActivity = et_stringValue.getText().toString();
                //send editText value to Activity
                DialogStringInputListener listener = (DialogStringInputListener) getActivity();
                if (listener != null) {
                    FunctionTypeMoneyAmount functionTypeMoneyAmount = new FunctionTypeMoneyAmount(
                            stringValue, integerValue);
                    listener.onInterfaceNewFunctionType(
                            INT_MONEY_TEMPLATES,
                            functionTypeMoneyAmount);
                }else{
                    String errorMessage = getResources().getString(R.string.dialog_getting_string_error);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
                // Close the dialog and return back to the parent activity
                dismiss();
            }
        });

    }
    private void loadEditorTexts(){
        et_StringValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,	int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                stringValue = et_StringValue.getText().toString();
                try {
                    integerValue = Integer.parseInt(et_IntegerValue.getText().toString());
                }catch (Exception ignored){
                    integerValue = 0;
                }
                buttonNewListState = (!stringValue.equals(""));
                b_CreateNewManageFunctions.setEnabled(buttonNewListState);
            }
        });
        et_IntegerValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,	int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                stringValue = et_StringValue.getText().toString();
                try {
                    integerValue = Integer.parseInt(et_IntegerValue.getText().toString());
                }catch (Exception ignored){
                    integerValue = 0;
                }
                buttonNewListState = (!stringValue.equals(""));
                b_CreateNewManageFunctions.setEnabled(buttonNewListState);
            }
        });
    }
}