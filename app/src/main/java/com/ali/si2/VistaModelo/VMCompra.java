package com.ali.si2.VistaModelo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ali.si2.Modelos.ProductoCarrito;
import com.ali.si2.Modelos.Tarjeta;
import com.ali.si2.Repositorio.RepoCarrito;
import com.ali.si2.Repositorio.RepoCompra;

import java.util.List;
import java.util.Map;

public class VMCompra extends ViewModel {
    RepoCompra repoCompra;
    public void initRepo(Context context){
        repoCompra = new RepoCompra(context);
    }



    public LiveData<Tarjeta> registrarTarjeta(Map<String, Object> map) {
        return repoCompra.registrarTarjeta(map);
    }
    public LiveData<Tarjeta> obtenerTarjeta() {
        return repoCompra.obtenerTarjeta();
    }

    public LiveData<Boolean> compra(Map<String, Object> map) {
        return repoCompra.compra(map);
    }
}
