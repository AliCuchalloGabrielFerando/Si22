package com.ali.si2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ali.si2.Modelos.Pais;
import com.ali.si2.VistaModelo.VMUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// implements AdapterView.OnItemSelectedListener
public class EditarCuenta extends AppCompatActivity {
    EditText contraseñavieja, contraseña, repetir;
    VMUser vmUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar_perfil);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);
      //  nombre = findViewById(R.id.nombre);
     //  correo = findViewById(R.id.correo);
        contraseñavieja = findViewById(R.id.contraseñavieja);
        contraseña = findViewById(R.id.contraseña);
        repetir = findViewById(R.id.repetir);

    }

  /*  public void actualizarPerfil(View view) {
        if (!nombre.getText().toString().isEmpty() && !correo.getText().toString().isEmpty()) {
            Map<String, String> map = new HashMap<>();
            map.put("nombre", nombre.getText().toString());
            map.put("correo", correo.getText().toString());
            vmUser.actualizarPerfil(map).observe(this, bandera -> {
                if (bandera.getBandera())
                    finish();
                else
                    Toast.makeText(this, "fallo", Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "ponga datos", Toast.LENGTH_SHORT).show();
        }
    }*/

    public void actualizatContraseña(View view) {
        if (!contraseñavieja.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty() &&
                !repetir.getText().toString().isEmpty()) {
            Map<String, String> map = new HashMap<>();
            map.put("contraseñav", contraseñavieja.getText().toString());
            map.put("contraseña", contraseña.getText().toString());
            map.put("repetir", repetir.getText().toString());
            vmUser.actualizarContrasaeña(map).observe(this, bandera -> {
                if (bandera.getBandera()) {
                    finish();
                } else {
                    Toast.makeText(this, "ponga datos", Toast.LENGTH_SHORT).show();
                }

        });

    }else
    {
        Toast.makeText(this, "ponga datos", Toast.LENGTH_SHORT).show();
    }
}


}