package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RecuperarCuenta extends AppCompatActivity {
    EditText correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);
        correo = findViewById(R.id.correo);
    }

    public void recuperar(View view) {
        Intent inten = new Intent(this,RecuperarCuentaFinal.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("correo",correo.getText().toString());
        inten.putExtras(bundle);
        startActivity(inten);
        finish();
    }
}