package com.example.a05test.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.a05test.activity.LoginActivity;
import com.example.a05test.activity.MainActivity;
import com.example.a05test.util.Constantes;
import com.example.a05test.util.UtilSharedPref;

public class SplashActivity extends AppCompatActivity {

    /*Para crear el Splash:
    1. Creamos un drawable splash
    2. Creamos un style SplashScreen
    3. Creamos un activity sin layout SplashActivity
    4. Registramos nuestro SplashActivity como principal en el Manifest

    Mejora:
    - SharedPreferences se ha centralizado en la clase UtilSharedPref
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UtilSharedPref.initSharedPref(this);

        if(tieneDatosAlmacenados()){
            irAMain();
        } else {
            irALogin();
        }

        finish();
    }

    private void irAMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void irALogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean tieneDatosAlmacenados() {
        if (!TextUtils.isEmpty(UtilSharedPref.getStringFromSharedPref(Constantes.SHAPR_EMAIL, ""))
                && !TextUtils.isEmpty(UtilSharedPref.getStringFromSharedPref(Constantes.SHAPR_CLAVE, ""))) {
            return true;
        } else {
            return false;
        }
    }
}
