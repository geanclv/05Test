package com.example.a05test.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.a05test.R;
import com.example.a05test.util.Constantes;
import com.example.a05test.util.UtilSharedPref;
import com.example.a05test.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UtilSharedPref.initSharedPref(this);
        Utils.setActivityTitle(this, "Hola "
                + UtilSharedPref.getStringFromSharedPref(Constantes.SHAPR_EMAIL, ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuCerrarSesion:
                cerrarSesion();
                return true;
            case R.id.mnuOlvidarDatos:
                olvidarDatos();
                cerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void olvidarDatos() {
        UtilSharedPref.clearSharedPref();
    }

    private void cerrarSesion() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
