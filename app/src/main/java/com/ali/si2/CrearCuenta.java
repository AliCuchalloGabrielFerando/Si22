package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ali.si2.VistaModelo.VMUser;

import java.util.HashMap;
import java.util.Map;

public class CrearCuenta extends AppCompatActivity {
    EditText  nombre, correo, contraseña;
    VMUser vmUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);
        setContentView(R.layout.activity_crear_cuenta);
        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        contraseña = findViewById(R.id.contraseña);
    }

    public void crear(View view) {
        String email=correo.getText().toString();
        String password=contraseña.getText().toString();
        String nom =nombre.getText().toString();
        Map<String,String> map=new HashMap<>();
        map.put("email",email);
        map.put("password",password);
        map.put("name",nom);
        vmUser.crearUsuario(map).observe(this,value -> {
            if(value){
                startActivity(new Intent(this,Login.class));
                finish();
            }else{
                Toast.makeText(this, "Intente otra vez", Toast.LENGTH_SHORT).show();
            }
        });

    }
}