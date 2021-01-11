package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ali.si2.VistaModelo.VMUser;

public class MainActivity extends AppCompatActivity {
    VMUser vmUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);


    }

    public void salir(View view) {
        vmUser.logout().observe(this,value->{
            startActivity(new Intent(this, Login.class));
            finish();
        });
    }
}