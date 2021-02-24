package com.ali.si2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ali.si2.Fragmentos.FragmentBusqueda;
import com.ali.si2.Fragmentos.FragmentCarrito;
import com.ali.si2.Fragmentos.FragmentConfiguracion;
import com.ali.si2.Fragmentos.FragmentPerfil;
import com.ali.si2.Fragmentos.FragmentTienda;
import com.ali.si2.VistaModelo.VMUser;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    VMUser vmUser;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);
        bottomNavigationView = findViewById(R.id.menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.tienda);


    }

    public void salir(View view) {

        vmUser.logout().observe(this,value->{
            startActivity(new Intent(this, Login.class));
            finish();
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tienda:
                FragmentTienda fragmentTienda = new FragmentTienda();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentTienda).commit();
                break;
            case R.id.busqueda:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new FragmentBusqueda()).commit();
                break;
            case R.id.compra:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentCarrito()).commit();
                break;
            case R.id.perfil:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentConfiguracion()).commit();
                break;

            default:
                break;
        }
        return true;
    }

}