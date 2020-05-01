package com.cobosideas.lista.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

public class DialogIconChooser extends DialogFragment {
    //CODE_ALERT_DIALOG_FRAGMENT
    private final int CODES_DIALOG_ICON_CHOOSER_ID = Constants.CODES_DIALOG_ICON_CHOOSER.CODES_DIALOG_ICON_CHOOSER_ID;
    private final String CODE_STRING_ICON_CHOOSER_CANCEL = Constants.CODES_DIALOG_ICON_CHOOSER.CODE_STRING_ICON_CHOOSER_CANCEL;
    private final String CODE_STRING_ICON_CHOOSER_SELECTED = Constants.CODES_DIALOG_ICON_CHOOSER.CODE_STRING_ICON_CHOOSER_SELECTED;
    /*        Trying to create a connection request sender for action    */
    public interface DialogIconChooserListener {
        void onInterfaceString(int CODE_ID, int selectedIconToChange);
    }
    //Global values to show on AlertDialog
    private int gIconSelected;
    //Buttons
    private Button b_Cancel, b_ChangeIcon;
    //buttonNewListState state
    private boolean buttonIconChooserState = false;
    public DialogIconChooser(){

    }
    public static DialogIconChooser newInstance() {
        DialogIconChooser dialogFragment = new DialogIconChooser();
        //Setting values on Arguments
        Bundle args = new Bundle();
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    private String getStringFromResources(int resourceName){
        return getResources().getString(resourceName);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        //This are use
        savedInstanceState.putInt(CODE_STRING_ICON_CHOOSER_SELECTED, gIconSelected);
        savedInstanceState.putBoolean(CODE_STRING_ICON_CHOOSER_CANCEL, buttonIconChooserState);

        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make sure getArguments() is not null, if null show different text
        if (savedInstanceState != null) {
            gIconSelected = savedInstanceState.getInt(CODE_STRING_ICON_CHOOSER_SELECTED, 0);
            buttonIconChooserState = savedInstanceState.getBoolean(CODE_STRING_ICON_CHOOSER_CANCEL);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_string_input , container, false);

        b_Cancel = view.findViewById(R.id.b_cancel);
        b_ChangeIcon = view.findViewById(R.id.b_change_icon);

        loadButtons();
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
        b_ChangeIcon.setEnabled(buttonIconChooserState);
        b_ChangeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on success
                //String valueToSendToActivity = et_stringValue.getText().toString();
                //send editText value to Activity
                DialogIconChooserListener listener = (DialogIconChooserListener) getActivity();
                if (listener != null) {
                    listener.onInterfaceString(CODES_DIALOG_ICON_CHOOSER_ID, gIconSelected);
                }else{
                    String errorMessage = getResources().getString(R.string.dialog_getting_string_error);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
                // Close the dialog and return back to the parent activity
                dismiss();
            }
        });
    }
}
