package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ali.si2.VistaModelo.VMUser;

public class Logout extends AppCompatActivity {
    VMUser vmUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);
        (findViewById(R.id.boton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });
    }
    public void salir() {

        vmUser.logout().observe(this,value->{
            startActivity(new Intent(this, Login.class));
            finish();
        });
    }
}