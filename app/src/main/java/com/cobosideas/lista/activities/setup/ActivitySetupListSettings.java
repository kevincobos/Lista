package com.cobosideas.lista.activities.setup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cobosideas.lista.R;
import com.cobosideas.lista.activities.setup.ui.setuplistsettings.SetupListSettingsFragment;

public class ActivitySetupListSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_list_settings);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SetupListSettingsFragment.newInstance())
                    .commitNow();
        }
    }
}
