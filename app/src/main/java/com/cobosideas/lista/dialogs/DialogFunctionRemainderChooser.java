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

public class DialogFunctionRemainderChooser extends DialogFragment {
    //CODE_ALERT_DIALOG_FRAGMENT
    private final int CODE_INT_ADF_ID = Constants.CODES_ADF_STRING_INPUT.CODE_INT_ALERT_DIALOG_FRAGMENT_ID;
    private final String CODE_STRING_EDIT_STRING_VALUE = Constants.CODES_ADF_STRING_INPUT.CODE_STRING_EDIT_STRING_VALUE;
    private final String CODE_STRING_BUTTON_NEW_STATE = Constants.CODES_ADF_STRING_INPUT.CODE_STRING_BUTTON_NEW_STATE;
    /*        Trying to create a connection request sender for action    */
    public interface DialogStringInputListener {
        void onInterfaceString(int CODE_ID, String stringValue, String stringDescription);
    }
    //Global values to show on AlertDialog
    private String stringValue, stringDescription;
    //we will extract the string from et_stringValue to send it to the Activity
    //private EditText et_stringValue;
    //we will extract the string from et_stringDescription to send it to the Activity
    //private EditText et_stringDescription;
    //Buttons
    private Button b_Cancel, b_CreateNewRemainder;
    //buttonNewListState state
    private boolean buttonNewListState = false;
    public DialogFunctionRemainderChooser(){
    }
    public static DialogFunctionRemainderChooser newInstance() {
        DialogFunctionRemainderChooser dialogFragment = new DialogFunctionRemainderChooser();
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_function_remainder_chooser , container, false);

        b_Cancel = view.findViewById(R.id.b_cancel);
        b_CreateNewRemainder = view.findViewById(R.id.b_new_remainder);

        loadButtons();

        //getDialog().setTitle(title);
        return view;
    }

    private void loadButtons() {
        b_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        b_CreateNewRemainder.setEnabled(buttonNewListState);
        b_CreateNewRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on success
                //String valueToSendToActivity = et_stringValue.getText().toString();
                //send editText value to Activity
                DialogStringInputListener listener = (DialogStringInputListener) getActivity();
                if (listener != null) {
                    listener.onInterfaceString(CODE_INT_ADF_ID, stringValue, stringDescription);
                } else {
                    String errorMessage = getResources().getString(R.string.dialog_getting_string_error);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
                // Close the dialog and return back to the parent activity
                dismiss();
            }
        });
    }
}
