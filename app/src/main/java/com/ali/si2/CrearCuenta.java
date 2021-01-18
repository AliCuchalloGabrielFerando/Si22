package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ali.si2.Modelos.Pais;
import com.ali.si2.VistaModelo.VMUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// implements AdapterView.OnItemSelectedListener
public class CrearCuenta extends AppCompatActivity {
    EditText  nombre, correo, contraseña;
    VMUser vmUser;
    Spinner spinner1,spinner2;
    List<Pais> listaPaises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);
        listaPaises = new ArrayList<>();
        setContentView(R.layout.activity_crear_cuenta);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        contraseña = findViewById(R.id.contraseña);
        List<String> paises = new ArrayList<>();
        List<String> ciudades = new ArrayList<>();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                paises);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                ciudades);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ciudades.clear();
                for (int j=0 ; j<listaPaises.get(position).getCiudades().size();j++){

                    ciudades.add(listaPaises.get(position).getCiudades().get(j).getNombre());
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        vmUser.getPaises().observe(this,pais -> {
            if(pais != null){
                listaPaises.addAll(pais);
                paises.clear();
                for (int i=0 ; i<listaPaises.size();i++){
                    paises.add(listaPaises.get(i).getNombre());
                }
                for (int j=0 ; j< listaPaises.get(0).getCiudades().size();j++){
                    ciudades.add(listaPaises.get(0).getCiudades().get(j).getNombre());
                }
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();

            }
        });
    }

    public void crear(View view) {
        String email=correo.getText().toString();
        String password=contraseña.getText().toString();
        String nom =nombre.getText().toString();
        Map<String,String> map=new HashMap<>();
        map.put("email",email);
        map.put("password",password);
        map.put("name",nom);
        map.put("ciudad",String.valueOf(listaPaises.get(spinner1.getSelectedItemPosition()).getCiudades()
                .get(spinner2.getSelectedItemPosition()).getId()));

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