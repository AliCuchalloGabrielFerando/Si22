package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        texto.setText("Se ha enviado a "+
                getIntent().getExtras().getSerializable("correo").toString()
        +" las intrucciones para recuperar su correo");
    }

    public void aceptar(View view) {
        startActivity(new Intent(this,Login.class));
        finish();
    }
}