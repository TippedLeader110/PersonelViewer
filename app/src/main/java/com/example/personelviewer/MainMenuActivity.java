package com.example.personelviewer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.personelviewer.datalist.PersonelView;

public class MainMenuActivity extends AppCompatActivity {

    SettingsFragment stF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        stF = new SettingsFragment();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, stF)
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main Menu");
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);


        }

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {
            String key = preference.getKey();
            Intent intent;
            intent = new Intent(getActivity(), PersonelView.class);
            Log.w("Preference Click Tree", "key : " + key);
            intent.putExtra("data", key);
            startActivity(intent);
            return true;
        }
    }
}