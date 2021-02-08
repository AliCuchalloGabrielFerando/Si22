package com.ali.si2.VistaModelo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ali.si2.Modelos.Producto;
import com.ali.si2.Repositorio.RepoTienda;
import com.ali.si2.Repositorio.RepoUser;

import java.util.List;

public class VMProducto extends ViewModel {
RepoTienda repoTienda;
    public void initRepo(Context context){
        repoTienda=new RepoTienda(context);
    }
    public LiveData<List<Producto>> getProductos(int categoria) {
        return repoTienda.getAllProductos(categoria);
    }
}
