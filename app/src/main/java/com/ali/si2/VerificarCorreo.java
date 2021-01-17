package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ali.si2.VistaModelo.VMUser;

public class VerificarCorreo extends AppCompatActivity {
    EditText correo;
    VMUser vmUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_correo);
        correo = findViewById(R.id.correo);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
    }

    public void verificar(View view) {

        if (!correo.getText().toString().isEmpty()) {

            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}