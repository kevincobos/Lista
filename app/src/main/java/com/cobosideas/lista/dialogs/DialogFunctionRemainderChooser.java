package com.cobosideas.lista.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cobosideas.lista.R;
import com.cobosideas.lista.activities.manage_functions.FunctionTypeReminder;
import com.cobosideas.lista.activities.manage_functions.ModelTypeReminder;
import com.cobosideas.lista.global.Constants;

public class DialogFunctionRemainderChooser extends DialogFragment {
    //CODE_ALERT_DIALOG_FRAGMENT
    private final int CODE_INT_ADF_ID = Constants.CODES_ADF_STRING_INPUT.CODE_INT_ALERT_DIALOG_FRAGMENT_ID;
    private final String CODE_STRING_EDIT_STRING_VALUE = Constants.CODES_ADF_STRING_INPUT.CODE_STRING_EDIT_STRING_VALUE;
    private final String CODE_STRING_BUTTON_NEW_STATE = Constants.CODES_ADF_STRING_INPUT.CODE_STRING_BUTTON_NEW_STATE;

    //Holds the access to liveData
    ModelTypeReminder modelTypeReminder;

    LiveData<FunctionTypeReminder> functionTypeReminder;
    //Global values to show on AlertDialog
    private String stringValue, stringDescription;

    //buttonNewListState state
    private boolean buttonNewListState = false;

    private AppCompatRadioButton rb_specific_time, rb_specific_hours, rb_specific_day, rb_specific_days;
    private LinearLayoutCompat ll_hour_control, ll_date_control, ll_control_months,
            ll_control_specific_time, ll_control_specific_hours,
            ll_control_specific_day,  ll_control_specific_days,
            ll_container_first_hours, ll_container_second_hours, ll_container_third_hours,
            ll_container_first_months, ll_container_second_months, ll_container_third_months;
    /*        Trying to create a connection request sender for action    */
    public interface DialogStringInputListener {
        void onInterfaceString(int CODE_ID, String stringValue, String stringDescription);
    }
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
    View gView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_function_remainder_chooser ,
                container,
                false);
        setupLiveData();
        //View global: gView
        gView = view;
        setupLinearLayouts();
        setupControlsCheckButtons();
        setupControlsRadioButtons();
        setupButtons();
        inflateHours();
        inflateDays();
        inflateMonths();
        return view;
    }
    private Observer<FunctionTypeReminder> typeReminderUpdateObserver = new Observer<FunctionTypeReminder>() {
        @Override
        public void onChanged(FunctionTypeReminder functionTypeReminder) {
            rb_specific_time.setChecked(functionTypeReminder.isSpecificTime());
        }
    };
    private void setupLiveData(){
        // Setup M V V M, after that jump to onChanged to setupRecycler
        modelTypeReminder = new ViewModelProvider(this.requireActivity()).get(ModelTypeReminder.class);
        modelTypeReminder.getAllValues().observe(this.requireActivity(), typeReminderUpdateObserver);
        //modelTypeReminder.setAllValues();
    }
    private void setupLinearLayouts(){
        ll_hour_control = gView.findViewById(R.id.ll_hour_control);
        ll_date_control = gView.findViewById(R.id.ll_date_control);
        ll_control_months = gView.findViewById(R.id.ll_control_months);
        //LinearLayout to specific hour or multiple hours
        ll_control_specific_time = gView.findViewById(R.id.ll_control_specific_time);
        ll_control_specific_hours = gView.findViewById(R.id.ll_control_specific_hours);
        //LinearLayout to specific day or multiple days
        ll_control_specific_day = gView.findViewById(R.id.ll_control_specific_day);
        ll_control_specific_days = gView.findViewById(R.id.ll_control_specific_days);
        //LinearLayouts to show hours 3 raw of 4; 1 to 12
        ll_container_first_hours = gView.findViewById(R.id.ll_container_first_hours);
        ll_container_second_hours = gView.findViewById(R.id.ll_container_second_hours);
        ll_container_third_hours = gView.findViewById(R.id.ll_container_third_hours);
        //LinearLayouts to show months 3 raw of 4; 1 to 12
        ll_container_first_months = gView.findViewById(R.id.ll_container_first_months);
        ll_container_second_months = gView.findViewById(R.id.ll_container_second_months);
        ll_container_third_months = gView.findViewById(R.id.ll_container_third_months);
    }
    private AppCompatCheckBox createNewCheckBox(String checkBoxName){
        AppCompatCheckBox checkBox = new AppCompatCheckBox(this.requireContext());
        checkBox.setText(checkBoxName);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        return checkBox;
    }
    /**
        Shows the checkboxes only hours from 1 to 12
     */
    private void inflateHours(){
        final int TOTAL_HOURS = 12;
        final int SECOND_HOURS= 4;
        final int THIRD_HOURS = 8;

        AppCompatCheckBox[] hours = new AppCompatCheckBox[TOTAL_HOURS];
        for (int cont = 0; cont < SECOND_HOURS; cont++){
            int secondRow = SECOND_HOURS + cont;
            int thirdRow = THIRD_HOURS + cont;

            String firstName = cont + 1 + "";
            String secondName = secondRow + 1 + "";
            String thirdName = thirdRow + 1 + "";

            hours[cont] = createNewCheckBox(firstName);
            hours[secondRow] = createNewCheckBox(secondName);
            hours[thirdRow] = createNewCheckBox(thirdName);

            ll_container_first_hours.addView(hours[cont]);
            ll_container_second_hours.addView(hours[secondRow]);
            ll_container_third_hours.addView(hours[thirdRow]);
        }
    }
    private void inflateDays(){
        final int TOTAL_DAYS = 6;
        AppCompatCheckBox[] days = new AppCompatCheckBox[TOTAL_DAYS];
        for (int cont = 0; cont < TOTAL_DAYS; cont++){
            String textName = cont + 1 + "";
            days[cont] = createNewCheckBox(textName);
            ll_control_specific_days.addView(days[cont]);
        }
    }
    private void inflateMonths(){
        final int TOTAL_MONTHS = 12;
        final int SECOND_MONTHS= 4;
        final int THIRD_MONTHS = 8;
        AppCompatCheckBox[] months = new AppCompatCheckBox[TOTAL_MONTHS];
        for (int cont = 0; cont < SECOND_MONTHS; cont++){
            int secondRow = SECOND_MONTHS + cont;
            int thirdRow = THIRD_MONTHS + cont;

            String fistName = cont + 1 + "";
            String secondName = secondRow + 1 + "";
            String thirdName = thirdRow + 1 + "";

            months[cont] = createNewCheckBox(fistName);
            months[secondRow] = createNewCheckBox(secondName);
            months[thirdRow] = createNewCheckBox(thirdName);

            ll_container_first_months.addView(months[cont]);
            ll_container_second_months.addView(months[secondRow]);
            ll_container_third_months.addView(months[thirdRow]);
        }
    }
    private void setupControlsRadioButtons(){
        final int SETUP_VIEW_CONTROL_TIME = 0;
        final int SETUP_VIEW_CONTROL_DATE= 1;

        rb_specific_time =  gView.findViewById(R.id.rb_specific_time);
        rb_specific_hours =  gView.findViewById(R.id.rb_specific_hours);
        rb_specific_day =  gView.findViewById(R.id.rb_specific_day);
        rb_specific_days =  gView.findViewById(R.id.rb_specific_days);

        setOnRadioButtonCheckedChange(rb_specific_time, SETUP_VIEW_CONTROL_TIME);
        setOnRadioButtonCheckedChange(rb_specific_hours, SETUP_VIEW_CONTROL_TIME);
        setOnRadioButtonCheckedChange(rb_specific_day, SETUP_VIEW_CONTROL_DATE);
        setOnRadioButtonCheckedChange(rb_specific_days, SETUP_VIEW_CONTROL_DATE);

    }
    private void setOnRadioButtonCheckedChange(AppCompatRadioButton appCompatRadioButton,
                                               final int setupViewControl){
        final int SETUP_VIEW_CONTROL_TIME = 0;
        final int SETUP_VIEW_CONTROL_DATE= 1;
        appCompatRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (setupViewControl){
                    case SETUP_VIEW_CONTROL_TIME:
                        if (rb_specific_time.isChecked()) ll_control_specific_time.
                                setVisibility(View.VISIBLE);
                        else ll_control_specific_time.setVisibility(View.GONE);
                        if (rb_specific_hours.isChecked()) ll_control_specific_hours.
                                setVisibility(View.VISIBLE);
                        else ll_control_specific_hours.setVisibility(View.GONE);
                        //functionTypeReminder.setSpecificTime(isChecked);
                        break;
                    case SETUP_VIEW_CONTROL_DATE:
                        if (rb_specific_day.isChecked()) ll_control_specific_day.
                                setVisibility(View.VISIBLE);
                        else ll_control_specific_day.setVisibility(View.GONE);
                        if (rb_specific_days.isChecked()) ll_control_specific_days.
                                setVisibility(View.VISIBLE);
                        else ll_control_specific_days.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }
    private void setOnCheckBoxCheckChange(AppCompatCheckBox appCompatCheckBox,
                                          final LinearLayoutCompat linearLayoutCompat){
        appCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) linearLayoutCompat.setVisibility(View.VISIBLE);
                else linearLayoutCompat.setVisibility(View.GONE);
            }
        });
    }
    private void setupControlsCheckButtons(){
        AppCompatCheckBox cb_hour_control = gView.findViewById(R.id.cb_hour_control);
        AppCompatCheckBox cb_date_control = gView.findViewById(R.id.cb_date_control);
        AppCompatCheckBox cb_months_control = gView.findViewById(R.id.cb_months_control);

        setOnCheckBoxCheckChange(cb_hour_control, ll_hour_control);
        setOnCheckBoxCheckChange(cb_date_control, ll_date_control);
        setOnCheckBoxCheckChange(cb_months_control, ll_control_months);
    }
    private void setupButtons() {
        //Buttons
        Button b_Cancel = gView.findViewById(R.id.b_cancel);
        Button b_CreateNewRemainder = gView.findViewById(R.id.b_new_remainder);
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
        Button b_select_specific_time = gView.findViewById(R.id.b_select_specific_time);
        b_select_specific_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TimePicker specificTimeSelected = new TimePicker();
            }
        });
    }
}
