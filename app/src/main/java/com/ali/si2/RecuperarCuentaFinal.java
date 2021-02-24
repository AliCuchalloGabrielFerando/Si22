package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecuperarCuentaFinal extends AppCompatActivity {
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta_final);
        texto = findViewById(R.id.texto);
        String bandera =  getIntent().getExtras().getSerializable("bandera").toString();
        if(bandera.compareTo("true")==0) {
            texto.setText("Se ha enviado a " + getIntent().getExtras().getSerializable("correo").toString()
                    + " las intrucciones para recuperar su correo");
        }else {
            texto.setText("Se ha enviado a " + getIntent().getExtras().getSerializable("correo").toString()
                    + " para verficar su correo");
        }
    }

    public void aceptar(View view) {

        SharedPreferences sharedPreferences =getSharedPreferences("userToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove("token");
        editor.remove("id");

        // editor.remove("user");
        editor.commit();
        startActivity(new Intent(this,Login.class));
        finish();
    }
}