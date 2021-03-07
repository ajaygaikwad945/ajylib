package com.engineerinside.ajaylibrary;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private static SharedPreference   sharedPreference;
    public static final String PREFS_NAME = "PREFS";
    public static final String PREFS_KEY = "PREFS_String";



    public static SharedPreference getInstance()
    {
        if (sharedPreference == null)
        {
            sharedPreference = new SharedPreference();
        }
        return sharedPreference;
    }

    public SharedPreference() {
        super();
    }

    public void save(Context context,  String Key,String value ) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(Key, value); //3

        editor.commit(); //4
    }

    public String getValue(Context context , String Key) {
        SharedPreferences settings;
        String text = "";
        //  settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(Key, "");
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context , String value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(value);
        editor.commit();
    }
}