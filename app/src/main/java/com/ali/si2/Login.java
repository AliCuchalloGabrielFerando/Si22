package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ali.si2.Utilidades.Utility;
import com.ali.si2.VistaModelo.VMUser;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText correo, contraseña;
    VMUser vmUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String token= Utility.readToken(this);
        if(!token.isEmpty()) toHome();
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);
        correo = findViewById(R.id.correo);
        contraseña = findViewById(R.id.contraseña);

    }








    public void recuperar(View view) {
        startActivity(new Intent(this,RecuperarCuenta.class));

    }

    public void crearCuenta(View view) {
        startActivity(new Intent(this,CrearCuenta.class));
    }
    public void iniciar(View view) {
        String email=correo.getText().toString();
        String password=contraseña.getText().toString();
        Map<String,String> map=new HashMap<>();
        map.put("email",email);
        map.put("password",password);
        vmUser.login(map).observe(this,value->{
            if(value){
                toHome();
            }else{
                startActivity(new Intent(this,VerificarCorreo.class));
                finish();
            }
        });
    }
    public void toHome(){

        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}