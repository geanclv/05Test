package com.example.a05test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.a05test.R;
import com.example.a05test.util.Constantes;
import com.example.a05test.util.UtilSharedPref;
import com.example.a05test.util.Utils;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtClave;
    private Switch swcRecordar;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UtilSharedPref.initSharedPref(this);
        initComponents();
        Utils.setActivityTitle(this, "Bienvenido");

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String clave = txtClave.getText().toString();
                if(login(email, clave)){
                    recordarDatos(email, clave);
                    entrarAlSistema();
                }
            }
        });

        llenarDatosRecordados();
    }

    private void llenarDatosRecordados(){
        String email = UtilSharedPref.getStringFromSharedPref(Constantes.SHAPR_EMAIL, "");
        String clave = UtilSharedPref.getStringFromSharedPref(Constantes.SHAPR_CLAVE, "");
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(clave)) {
            txtEmail.setText(email);
            txtClave.setText(clave);
            swcRecordar.setChecked(true);
        } else {
            swcRecordar.setChecked(false);
        }
    }

    private void entrarAlSistema(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void recordarDatos(String email, String clave){
        if(swcRecordar.isChecked()){
            UtilSharedPref.setStringToSharedPref(Constantes.SHAPR_EMAIL, email);
            UtilSharedPref.setStringToSharedPref(Constantes.SHAPR_CLAVE, clave);
        } else {
            UtilSharedPref.removeStringFromSharedPref(Constantes.SHAPR_EMAIL);
            UtilSharedPref.removeStringFromSharedPref(Constantes.SHAPR_CLAVE);
        }
    }

    private boolean login(String email, String clave){
        if(validarCamposLogin(email, clave)){
            if(validarUsuario(email, clave)){
                return true;
            }
        }
        return false;
    }

    private boolean validarUsuario(String email, String clave){
        if(email.equals(Constantes.LOGIN_USUARIO) && clave.equals(Constantes.LOGIN_CLAVE)){
            return true;
        } else {
            Utils.mostrarToast(this, "Email o clave incorrectos");
            return false;
        }
    }

    private boolean validarCamposLogin(String email, String clave){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && clave.length() < 5){
            Utils.mostrarToast(this, "Email o clave incorrectos");
            return false;
        } else {
            return true;
        }
    }

    private void initComponents(){
        txtEmail = findViewById(R.id.txtEmail);
        txtClave = findViewById(R.id.txtClave);
        swcRecordar = findViewById(R.id.swcRecordar);
        btnAceptar = findViewById(R.id.btnAceptar);
    }
}
