package com.example.chad.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by chad on 2018/3/18.
 */

public class PrefActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences sharedPreferences;
    private Preference DefaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mypreference);

        DefaultColor=(Preference)findPreference("DEFAULT_COLOR");
        DefaultColor.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorDialog colorDialog=new ColorDialog(PrefActivity.this);
                colorDialog.show();
                return false;
            }
        });
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.d("tag","prefActicity resume");
        int color=sharedPreferences.getInt("DEFAULT_COLOR",-1);
        if (color!= -1){
            DefaultColor.setSummary(getString(R.string.default_color_summary)+":"+ItemActivity.getColors(color));
        }
    }




    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d("tag","prefActicity resume");
        int color=sharedPreferences.getInt("DEFAULT_COLOR",-1);
        if (color!= -1){
            DefaultColor.setSummary(getString(R.string.default_color_summary)+":"+ItemActivity.getColors(color));
        }
    }
}
