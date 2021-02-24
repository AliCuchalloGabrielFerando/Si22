package com.ali.si2.VistaModelo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ali.si2.Modelos.Producto;
import com.ali.si2.Repositorio.RepoBusqueda;

import java.util.List;
import java.util.Map;

public class VMBusqueda extends ViewModel {
    RepoBusqueda repoBusqueda;
    public void initRepo(Context context){
        repoBusqueda = new RepoBusqueda(context);
    }

    public LiveData<List<Producto>> getProductos(String nombre) {
        return repoBusqueda.getProductos(nombre);
    }

    public LiveData<List<Producto>> getProductosFiltrado(Map<String, String> map) {
        return repoBusqueda.getProductosFiltrados(map);
    }
}
