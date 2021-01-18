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

public class RecuperarCuenta extends AppCompatActivity {
    EditText correo;
    VMUser vmUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);
        correo = findViewById(R.id.correo);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);
    }

    public void recuperar(View view) {
        Map<String,String> map=new HashMap<>();
        map.put("email",correo.getText().toString());
        if(correo.getText().toString().contains("@")) {


            vmUser.recuperar(map).observe(this, s -> {
                if (s.compareTo("¡Recordatorio de contraseña enviado!")==0) {
                    Intent inten = new Intent(this, RecuperarCuentaFinal.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("correo", correo.getText().toString());
                    bundle.putSerializable("bandera", "true");
                    inten.putExtras(bundle);
                    startActivity(inten);
                    finish();
                }else{
                    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "escriba un correo valido", Toast.LENGTH_SHORT).show();
        }
    }
}