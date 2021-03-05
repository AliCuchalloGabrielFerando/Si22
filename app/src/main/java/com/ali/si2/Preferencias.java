package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.ali.si2.Modelos.Categoria;
import com.ali.si2.VistaModelo.VMProducto;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preferencias extends AppCompatActivity {
    ChipGroup chipCategorias;
    List<Categoria> listaCategoria;
    VMProducto vmProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        chipCategorias = findViewById(R.id.categorias);
        listaCategoria = new ArrayList<>();
        vmProducto = new ViewModelProvider(this).get(VMProducto.class);
        vmProducto.initRepo(this);
        chipCategorias.clearCheck();  //para limpiar los chequeos android no lo hace bien
        cargarCategorias();
        //Uso de Chipgroup
        List<String> categorias;
        categorias = Arrays.asList(getResources().getStringArray(R.array.generos));
        LayoutInflater inflater= LayoutInflater.from(this);
        for(String cat:categorias){
            Chip chip= (Chip) inflater.inflate(R.layout.item_chip,null,false);
            chip.setText(cat);

            chipCategorias.addView(chip);
            //chip.setChecked(true);
           // chipCategorias.notify(); no sirve cuelga la aplicacion

        }



    }

    private void cargarCategorias() {
        vmProducto.getCategorias().observe(this,categorias -> {
            listaCategoria.clear();
            listaCategoria.addAll(categorias);

            LayoutInflater inflater= LayoutInflater.from(this);
            for(int i=0 ;i<listaCategoria.size();i++){
                Chip chip= (Chip) inflater.inflate(R.layout.item_chip,null,false);
                chip.setText(listaCategoria.get(i).getNombre());
                chipCategorias.addView(chip);
            }
            categoriasDeUsuario();
        });

    }

    private void categoriasDeUsuario() {
        vmProducto.getCategoriasDeUsuario().observe(this,categorias -> {
            if(!categorias.isEmpty()){
                for(int i=0;i<categorias.size();i++){
                    for(int j=0;j<chipCategorias.getChildCount();j++) {
                        Chip chip = (Chip) chipCategorias.getChildAt(j);
                        if (categorias.get(i).getNombre().compareTo(chip.getText().toString()) == 0) {
                            chip.setChecked(true);
                        }
                    }
                }

            }
        });

    }

    public void guardar(View view) {
        List<Integer> listaPreferencias=new ArrayList<>();
        for(int i=0;i<chipCategorias.getChildCount();i++) {
            Chip chip = (Chip) chipCategorias.getChildAt(i);
            if (chip.isChecked()) {
                listaPreferencias.add(listaCategoria.get(i).getId());
            }
        }
        vmProducto.guardarPreferencias(listaPreferencias);
        finish();
    }
}