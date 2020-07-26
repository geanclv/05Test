package com.example.a05test.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void setActivityTitle(Activity activity, String titulo){
        activity.setTitle(titulo);
    }

    public static void mostrarToast(Context context, String mensaje){
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
    }
}
