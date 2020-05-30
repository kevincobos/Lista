package com.cobosideas.lista.dialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cobosideas.lista.R;
import com.cobosideas.lista.activities.manage_functions.FunctionTypeReminder;
import com.cobosideas.lista.activities.manage_functions.ModelTypeReminder;
import com.cobosideas.lista.global.Constants;

import java.util.Calendar;

public class DialogFunctionRemainderChooser extends DialogFragment {
    final int INT_REMAINDER_TEMPLATES = Constants.CODES_DATABASE_MANAGE_FUNCTIONS.
            INT_REMAINDER_TEMPLATES;
    //CODE_ALERT_DIALOG_FRAGMENT
    private final String CODE_STRING_EDIT_STRING_VALUE = Constants.CODES_ADF_STRING_INPUT.
            CODE_STRING_EDIT_STRING_VALUE;
    private final String CODE_STRING_BUTTON_NEW_STATE = Constants.CODES_ADF_STRING_INPUT.
            CODE_STRING_BUTTON_NEW_STATE;

    //Holds the access to liveData
    private ModelTypeReminder gModelTypeReminder;

    private FunctionTypeReminder gFunctionTypeReminder;
    //Global values to show on AlertDialog
    private String stringValue, stringDescription;

    //buttonNewListState state
    private boolean buttonNewListState = false;
    private EditText et_name;
    private TextView g_tv_selectedSpecificTime, g_tv_selectedSpecificDay;
    Button b_selectSpecificTime, b_selectSpecificDay;
    private AppCompatRadioButton rb_specific_time, rb_specific_hours, rb_specific_day,
            rb_specific_days;
    private AppCompatCheckBox cb_hour_control, cb_date_control, cb_months_control;
    private AppCompatCheckBox[] cb_hours, cb_days, cb_months;

    private LinearLayoutCompat ll_hour_control, ll_date_control, ll_control_months,
            ll_control_specific_time, ll_control_specific_hours,
            ll_control_specific_day,  ll_control_specific_days,
            ll_container_first_hours, ll_container_second_hours, ll_container_third_hours,
            ll_container_first_months, ll_container_second_months, ll_container_third_months;
    /*        Trying to create a connection request sender for action    */
    public interface DialogRemainderChooserListener {
        void onInterfaceNewFunctionType(int CODE_ID, Object functionTypeRemainder);
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
        setupLiveData();
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

        //View global: gView
        gView = view;
        setupLinearLayouts();
        setupControlsCheckButtons();
        setupControlsRadioButtons();
        setupEditText();
        setupTextView();
        setupButtons();
        inflateHours();
        inflateDays();
        inflateMonths();
        return view;
    }

    private FunctionTypeReminder getFunctionTypeRemainder(){
        /* TODO depending if FunctionTypeReminder exist update it or create a new one
            for now we are only creating one but should be allowing to update it in the future
            the main difference is the time stamp of "dateFunctionModify" */
        FunctionTypeReminder functionTypeReminder = new FunctionTypeReminder();

        functionTypeReminder.setName(et_name.getText().toString());

        functionTypeReminder.setHourControl(cb_hour_control.isChecked());
        functionTypeReminder.setDateControl(cb_date_control.isChecked());
        functionTypeReminder.setMonthsControl(cb_months_control.isChecked());

        functionTypeReminder.setSpecificTime(rb_specific_time.isChecked());
        functionTypeReminder.setSpecificDay(rb_specific_day.isChecked());

        functionTypeReminder.setSelectedTime(g_tv_selectedSpecificTime.getText().toString());
        functionTypeReminder.setSelectedDay(g_tv_selectedSpecificDay.getText().toString());

        boolean[] selectedHours = new boolean[cb_hours.length];
        for (int cont = 0; cont < cb_hours.length; cont++){
            selectedHours[cont] = cb_hours[cont].isChecked();
        }
        functionTypeReminder.setSelectedHours(selectedHours);

        boolean[] selectedDays = new boolean[cb_days.length];
        for (int cont = 0; cont < cb_days.length; cont++){
            selectedDays[cont] = cb_days[cont].isChecked();
        }
        functionTypeReminder.setSelectedDays(selectedDays);

        boolean[] selectedMonths = new boolean[cb_months.length];
        for (int cont = 0; cont < cb_months.length; cont++){
            selectedMonths[cont] = cb_months[cont].isChecked();
        }
        functionTypeReminder.setSelectedMonths(selectedMonths);
        return functionTypeReminder;
    }

    private Observer<FunctionTypeReminder> typeReminderUpdateObserver = new Observer<FunctionTypeReminder>() {
        @Override
        public void onChanged(FunctionTypeReminder functionTypeReminder) {
            gFunctionTypeReminder = functionTypeReminder;
            et_name.setText(gFunctionTypeReminder.getName());

            cb_hour_control.setChecked(gFunctionTypeReminder.isHourControl());
            cb_date_control.setChecked(gFunctionTypeReminder.isDateControl());
            cb_months_control.setChecked(gFunctionTypeReminder.isMonthsControl());

            rb_specific_time.setChecked(gFunctionTypeReminder.isSpecificTime());
            rb_specific_day.setChecked(gFunctionTypeReminder.isSpecificDay());

            g_tv_selectedSpecificTime.setText(gFunctionTypeReminder.getSelectedTime());
            g_tv_selectedSpecificDay.setText(gFunctionTypeReminder.getSelectedDay());

            for (int cont = 0; cont < cb_hours.length; cont++){
                cb_hours[cont].setChecked(gFunctionTypeReminder.getSelectedHours(cont));
            }
            for (int cont = 0; cont < cb_days.length; cont++){
                cb_days[cont].setChecked(gFunctionTypeReminder.getSelectedDays(cont));
            }
            for (int cont = 0; cont < cb_months.length; cont++){
                cb_months[cont].setChecked(gFunctionTypeReminder.getSelectedMonths(cont));
            }
        }

    };
    private void setupLiveData(){
        // Setup M V V M, after that jump to onChanged to setupRecycler
        gModelTypeReminder = new ViewModelProvider(this.requireActivity()).get(ModelTypeReminder.class);
        gModelTypeReminder.getAllValues().observe(this.requireActivity(), typeReminderUpdateObserver);
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
    private AppCompatCheckBox createNewCheckBox(int idNumber, String checkBoxName){
        AppCompatCheckBox checkBox = new AppCompatCheckBox(this.requireContext());
        checkBox.setId(idNumber);
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
        final int ID_HOURS = 10;
        final int TOTAL_HOURS = 12;
        final int SECOND_HOURS= 4;
        final int THIRD_HOURS = 8;

        cb_hours = new AppCompatCheckBox[TOTAL_HOURS];
        for (int cont = 0; cont < SECOND_HOURS; cont++){
            int secondRow = SECOND_HOURS + cont;
            int thirdRow = THIRD_HOURS + cont;

            String firstName = cont + 1 + "";
            String secondName = secondRow + 1 + "";
            String thirdName = thirdRow + 1 + "";

            cb_hours[cont] = createNewCheckBox(ID_HOURS + cont, firstName);
            cb_hours[secondRow] = createNewCheckBox(ID_HOURS + secondRow, secondName);
            cb_hours[thirdRow] = createNewCheckBox(ID_HOURS + thirdRow, thirdName);

            ll_container_first_hours.addView(cb_hours[cont]);
            ll_container_second_hours.addView(cb_hours[secondRow]);
            ll_container_third_hours.addView(cb_hours[thirdRow]);
        }
    }
    private void inflateDays(){
        final int TOTAL_DAYS = 6;
        cb_days = new AppCompatCheckBox[TOTAL_DAYS];
        for (int cont = 0; cont < TOTAL_DAYS; cont++){
            String textName = cont + 1 + "";
            cb_days[cont] = createNewCheckBox(cont, textName);
            ll_control_specific_days.addView(cb_days[cont]);
        }
    }
    private void inflateMonths(){
        final int ID_MONTHS = 100;
        final int TOTAL_MONTHS = 12;
        final int SECOND_MONTHS= 4;
        final int THIRD_MONTHS = 8;
        cb_months = new AppCompatCheckBox[TOTAL_MONTHS];
        for (int cont = 0; cont < SECOND_MONTHS; cont++){
            int secondRow = SECOND_MONTHS + cont;
            int thirdRow = THIRD_MONTHS + cont;

            String fistName = cont + 1 + "";
            String secondName = secondRow + 1 + "";
            String thirdName = thirdRow + 1 + "";

            cb_months[cont] = createNewCheckBox(ID_MONTHS + cont, fistName);
            cb_months[secondRow] = createNewCheckBox(ID_MONTHS + secondRow, secondName);
            cb_months[thirdRow] = createNewCheckBox(ID_MONTHS + thirdRow, thirdName);

            ll_container_first_months.addView(cb_months[cont]);
            ll_container_second_months.addView(cb_months[secondRow]);
            ll_container_third_months.addView(cb_months[thirdRow]);
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
    //
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
        cb_hour_control = gView.findViewById(R.id.cb_hour_control);
        cb_date_control = gView.findViewById(R.id.cb_date_control);
        cb_months_control = gView.findViewById(R.id.cb_months_control);
        setOnCheckBoxCheckChange(cb_hour_control, ll_hour_control);
        setOnCheckBoxCheckChange(cb_date_control, ll_date_control);
        setOnCheckBoxCheckChange(cb_months_control, ll_control_months);
    }
    private void setupButtons() {
        //Buttons
        Button b_Cancel = gView.findViewById(R.id.b_cancel);
        Button b_Accept = gView.findViewById(R.id.b_accept);
        b_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        b_Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on success
                //String valueToSendToActivity = et_stringValue.getText().toString();
                //send editText value to Activity
                DialogRemainderChooserListener listener = (DialogRemainderChooserListener) getActivity();
                if (listener != null) {
                    FunctionTypeReminder functionTypeReminder = getFunctionTypeRemainder();
                    listener.onInterfaceNewFunctionType(INT_REMAINDER_TEMPLATES,
                            functionTypeReminder);
                } else {
                    String errorMessage = getResources().getString(R.string.dialog_getting_string_error);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
                // Close the dialog and return back to the parent activity
                dismiss();
            }
        });
        b_selectSpecificTime = gView.findViewById(R.id.b_select_specific_time);
        b_selectSpecificDay = gView.findViewById(R.id.b_select_specific_day);
        b_selectSpecificTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final int minutes = calendar.get(Calendar.MINUTE);
                // date picker dialog
                TimePickerDialog picker = new TimePickerDialog(requireContext(),
                        new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String stringMinutes;
                        if (minute < 9) stringMinutes = ":0"+minute;
                        else stringMinutes = ":"+minute;
                        String selectedTime = hourOfDay + stringMinutes;
                        g_tv_selectedSpecificTime.setText(selectedTime);
                    }
                },hour, minutes, true);
                picker.show();            }
        });
        b_selectSpecificDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDay = year + "/" +month + "/" + dayOfMonth;
                        g_tv_selectedSpecificDay.setText(selectedDay);

                    }
                }, year, month, day);
                picker.show();            }
        });
    }
    private void setupEditText(){
        et_name = gView.findViewById(R.id.et_name);
    }
    private void setupTextView(){
        g_tv_selectedSpecificTime = gView.findViewById(R.id.tv_select_specific_time);
        g_tv_selectedSpecificDay = gView.findViewById(R.id.tv_select_specific_day);
    }
}
