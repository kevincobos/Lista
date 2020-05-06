package com.cobosideas.lista.dialogs;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
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
        View view = inflater.inflate(R.layout.dialog_icon_chooser , container, false);

        b_Cancel = view.findViewById(R.id.b_cancel);
        b_ChangeIcon = view.findViewById(R.id.b_change_icon);

        LinearLayout linearLayoutContainer = view.findViewById(R.id.ll_icon_container);

        setupImageViews(linearLayoutContainer);
        loadButtons();
        return view;
    }

    private View.OnClickListener ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buttonIconChooserState = true;
            b_ChangeIcon.setEnabled(true);
            gIconSelected = (Integer) view.getTag();
            Animation animationDefaultEnter = AnimationUtils.loadAnimation(getContext(),R.anim.nav_default_enter_anim);
            view.startAnimation(animationDefaultEnter);
        }
    };
    private void setupImageViews(LinearLayout linearLayoutContainer){
        final int[] DRAWABLE_ICONS_FRUITS = Constants.CODES_DIALOG_ICON_CHOOSER.DRAWABLE_ICONS_FRUITS;
        final int[] DRAWABLE_ICONS_VEGETABLES = Constants.CODES_DIALOG_ICON_CHOOSER.DRAWABLE_ICONS_VEGETABLES;
        setupImagesButtonsFruits(linearLayoutContainer, DRAWABLE_ICONS_FRUITS);
        setupImagesButtonsFruits(linearLayoutContainer, DRAWABLE_ICONS_VEGETABLES);
    }
    private void setupImagesButtonsFruits(LinearLayout linearLayoutContainer, int[] DRAWABLE_ICONS){



        LinearLayout.LayoutParams ll_Icon_Params = new LinearLayout.LayoutParams(150,
                150);
        ll_Icon_Params.setMargins(4, 10, 4, 10);
        ll_Icon_Params.setLayoutDirection(LinearLayout.HORIZONTAL);

        int countingTotalIcons = 0;
        int totalDrawableIconsFruits = DRAWABLE_ICONS.length;



        LinearLayout ll_Icon_Column;

        Boolean createSpaces = true;
        LinearLayout.LayoutParams ll_SpaceParams = new LinearLayout.LayoutParams(0,
                1,1);
        Space[] fillingSpace = new Space[5];

        while (countingTotalIcons < totalDrawableIconsFruits) {
            ll_Icon_Column = new LinearLayout(this.getActivity());
            ll_Icon_Column.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                            LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

            ll_Icon_Column.setGravity(Gravity.CENTER);
            ll_Icon_Column.setOrientation(LinearLayout.HORIZONTAL);

            ImageView[] ib_IconShow = new ImageView[4];
            for (int count = 0; count < 4; count++) {

                ib_IconShow[count] = new ImageView(this.getActivity());

                    ib_IconShow[count].setLayoutParams(ll_Icon_Params);
                if (countingTotalIcons < totalDrawableIconsFruits) {
                    int imageIcon = DRAWABLE_ICONS[countingTotalIcons];
                    ib_IconShow[count].setImageResource(imageIcon);

                    ib_IconShow[count].setOnClickListener(ClickListener);
                    ib_IconShow[count].setTag(imageIcon);
                }
                    ib_IconShow[count].setBackgroundResource(R.drawable.rectangle);

                    fillingSpace[count] = new Space(this.getContext());
                    fillingSpace[count].setLayoutParams(ll_SpaceParams);
                    ll_Icon_Column.addView(fillingSpace[count]);

                    ll_Icon_Column.addView(ib_IconShow[count]);
                    countingTotalIcons++;



            }

            fillingSpace[fillingSpace.length-1] = new Space(this.getContext());
            fillingSpace[fillingSpace.length-1].setLayoutParams(ll_SpaceParams);
            ll_Icon_Column.addView(fillingSpace[fillingSpace.length-1]);

            linearLayoutContainer.addView(ll_Icon_Column);

        }
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
