package com.example.a05test.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilSharedPref {

    private static SharedPreferences preferences;

    public static void removeStringFromSharedPref(String key){
        preferences.edit().remove(key).apply();
    }

    public static String getStringFromSharedPref(String key, String defaultValue){
        return preferences.getString(key, defaultValue);
    }

    public static void setStringToSharedPref(String key, String value){
        preferences.edit().putString(key, value).apply();
    }

    public static void clearSharedPref(){
        preferences.edit().clear().apply();
    }

    public static void initSharedPref(Context context){
        preferences = context.getSharedPreferences(Constantes.SHAPR_KEY, Context.MODE_PRIVATE);
    }
}
