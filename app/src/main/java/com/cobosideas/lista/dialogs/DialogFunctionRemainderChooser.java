package com.cobosideas.lista.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;

import com.cobosideas.lista.R;
import com.cobosideas.lista.activities.manage_functions.FunctionTypeReminder;
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

    FunctionTypeReminder functionTypeReminder;
    //Global values to show on AlertDialog
    private String stringValue, stringDescription;

    //Buttons
    private Button b_Cancel, b_CreateNewRemainder;
    //buttonNewListState state
    private boolean buttonNewListState = false;

    private AppCompatCheckBox cb_hour_control, cb_date_control, cb_months_control;
    private AppCompatRadioButton rb_specific_time, rb_specific_hours;
    private LinearLayoutCompat ll_hour_control, ll_date_control, ll_control_months,
            ll_control_specific_time, ll_control_specific_hours, ll_control_specific_day,
            ll_control_specific_days, ll_container_first_hours, ll_container_second_hours,
            ll_container_third_hours, ll_specific_days, ll_specific_months;
    public DialogFunctionRemainderChooser(){
    }
    public static DialogFunctionRemainderChooser newInstance(FunctionTypeReminder functionTypeReminder) {
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_function_remainder_chooser ,
                container,
                false);


        setupLinearLayouts(view);
        setupControlsCheckButtons(view);
        setupControlsRadioButtons(view);
        loadButtons(view);
        inflateHours(view);
        //getDialog().setTitle(title);
        return view;
    }
    private void setupLinearLayouts(View view){
        ll_hour_control = view.findViewById(R.id.ll_hour_control);
        ll_date_control = view.findViewById(R.id.ll_date_control);
        ll_control_months = view.findViewById(R.id.ll_control_months);

        ll_control_specific_time = view.findViewById(R.id.ll_control_specific_time);
        ll_control_specific_hours = view.findViewById(R.id.ll_control_specific_hours);

        ll_control_specific_day = view.findViewById(R.id.ll_control_specific_day);
        ll_control_specific_days = view.findViewById(R.id.ll_control_specific_days);

        ll_container_first_hours = view.findViewById(R.id.ll_container_first_hours);
        ll_container_second_hours = view.findViewById(R.id.ll_container_second_hours);
        ll_container_third_hours = view.findViewById(R.id.ll_container_third_hours);
        ll_specific_days = view.findViewById(R.id.ll_control_specific_day);
    }
    private void inflateHours(View view){
        final int TOTAL_HOURS = 12;
        final int SECOND_HOURS= 4;
        final int THIRD_HOURS = 8;

        AppCompatCheckBox[] hours = new AppCompatCheckBox[TOTAL_HOURS];
        //AppCompatCheckBox[] secondHours = new AppCompatCheckBox[halfHours];
        for (int cont = 0; cont < SECOND_HOURS; cont++){
            int secondRow = SECOND_HOURS + cont;
            int thirdRow = THIRD_HOURS + cont;
            String idFirstName = cont + 1 + "";
            String idSecondName = secondRow + 1 + "";
            String idThirdName = thirdRow + 1 + "";
            hours[cont] = new AppCompatCheckBox(this.getContext());
            hours[secondRow] = new AppCompatCheckBox(this.getContext());
            hours[thirdRow] = new AppCompatCheckBox(this.getContext());
            hours[cont].setText(idFirstName);
            hours[secondRow].setText(idSecondName);
            hours[thirdRow].setText(idThirdName);
            hours[cont].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            hours[secondRow].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            hours[thirdRow].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            ll_container_first_hours.addView(hours[cont]);
            ll_container_second_hours.addView(hours[secondRow]);
            ll_container_third_hours.addView(hours[thirdRow]);
        }
    }
    private void setupControlsRadioButtons(View view){
        rb_specific_time =  view.findViewById(R.id.rb_specific_time);
        rb_specific_hours =  view.findViewById(R.id.rb_specific_hours);

        rb_specific_time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setupViewControlTime();
            }
        });
        rb_specific_hours.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setupViewControlTime();
            }
        });
    }
    private void setupViewControlTime(){
        if (rb_specific_time.isChecked()) ll_control_specific_time.setVisibility(View.VISIBLE);
        else ll_control_specific_time.setVisibility(View.GONE);
        if (rb_specific_hours.isChecked()) ll_control_specific_hours.setVisibility(View.VISIBLE);
        else ll_control_specific_hours.setVisibility(View.GONE);
    }
    private void setupControlsCheckButtons(View view){
        cb_hour_control = view.findViewById(R.id.cb_hour_control);
        cb_date_control = view.findViewById(R.id.cb_date_control);
        cb_months_control = view.findViewById(R.id.cb_months_control);

        cb_hour_control.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) ll_hour_control.setVisibility(View.VISIBLE);
                else ll_hour_control.setVisibility(View.GONE);
            }
        });
        cb_date_control.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) ll_date_control.setVisibility(View.VISIBLE);
                else ll_date_control.setVisibility(View.GONE);
            }
        });
        cb_months_control.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) ll_control_months.setVisibility(View.VISIBLE);
                else ll_control_months.setVisibility(View.GONE);
            }
        });
    }
    private void loadButtons(View view) {
        b_Cancel = view.findViewById(R.id.b_cancel);
        b_CreateNewRemainder = view.findViewById(R.id.b_new_remainder);
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
        Button b_select_specific_time = view.findViewById(R.id.b_select_specific_time);
        b_select_specific_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TimePicker specificTimeSelected = new TimePicker();
            }
        });
    }
}
