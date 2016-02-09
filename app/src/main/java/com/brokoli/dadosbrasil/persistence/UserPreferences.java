package com.brokoli.dadosbrasil.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.brokoli.dadosbrasil.MyApp;

public class UserPreferences {

    // Common methods
    private static SharedPreferences getSharedPreferences(){
        return MyApp.getAppContext().getSharedPreferences(MyApp.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }

    public static void clearSharedPreferences(){
        UserPreferences.getSharedPreferences().edit().clear().commit();
    }

    // Should show drawer
    public static final String SAVED_SHOULD_SHOW_DRAWER = "SAVED_SHOULD_SHOW_DRAWER";
    public static boolean readShouldShowDrawer() {
        SharedPreferences sharedPref = UserPreferences.getSharedPreferences();
        return sharedPref.getBoolean(SAVED_SHOULD_SHOW_DRAWER, true);
    }

    public static void writeShouldShowDrawer(@SuppressWarnings("SameParameterValue") boolean newShouldShowDrawer) {
        SharedPreferences sharedPref = UserPreferences.getSharedPreferences();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(SAVED_SHOULD_SHOW_DRAWER, newShouldShowDrawer);
        editor.apply();
    }
}
