package com.cobosideas.lista.activities.booting;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

public class ActivityBooting extends AppCompatActivity {
    private String CODE_STRING_EXTERNAL_VALUE = Constants.
            CODES_ACTIVITY_BOOT.CODE_STRING_EXTERNAL_VALUE;
    private String gComingText = "";
    boolean gExternalAction = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            gComingText = getIntent().getStringExtra(CODE_STRING_EXTERNAL_VALUE);
        } else {
            gComingText = CODE_STRING_EXTERNAL_VALUE;
        }
        if (savedInstanceState == null) {

        } else {

        }
        setupActivityRootView();
    }
    private void setupActivityRootView(){
        setContentView(R.layout.activity_booting);
    }

}
