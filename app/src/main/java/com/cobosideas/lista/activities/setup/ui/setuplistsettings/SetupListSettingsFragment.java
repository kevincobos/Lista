package com.cobosideas.lista.activities.setup.ui.setuplistsettings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cobosideas.lista.R;

public class SetupListSettingsFragment extends Fragment {

    private SetupListSettingsViewModel mViewModel;

    public static SetupListSettingsFragment newInstance() {
        return new SetupListSettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setup_list_settings_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProvider.of(this).get(SetupListSettingsViewModel.class);
        // TODO: Use the ViewModel
    }

}
