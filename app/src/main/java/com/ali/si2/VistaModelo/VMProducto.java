package com.ali.si2.VistaModelo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ali.si2.Modelos.Categoria;
import com.ali.si2.Modelos.Marca;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Repositorio.RepoPreview;
import com.ali.si2.Repositorio.RepoTienda;
import com.ali.si2.Repositorio.RepoUser;

import java.util.HashMap;
import java.util.List;

public class VMProducto extends ViewModel {
RepoTienda repoTienda;
RepoPreview repoPreview;
    public void initRepo(Context context){
        repoTienda = new RepoTienda(context);
        repoPreview = new RepoPreview(context);
    }
    public LiveData<List<Producto>> getProductos(int categoria) {
        return repoTienda.getAllProductos(categoria);
    }

    public LiveData<List<Categoria>> getCategorias() {
        return repoTienda.getAllCategorias();
    }

    public LiveData<List<Producto>> getProductos(Producto producto) {
        return repoTienda.getProductosSimilares(producto);
    }

    public LiveData<HashMap<String,Object>> getMasVendido() {
        return repoTienda.getMasVendido();
    }

    public LiveData<List<Categoria>> getCategoriasDeUsuario() {
        return repoTienda.getCategoriasDeUsuario();
    }

    public void guardarPreferencias(List<Integer> listaPreferencias) {
        repoTienda.guardarPreferencias(listaPreferencias);
    }

    public LiveData<HashMap<String,Object>> datosExtraDeProducto(int productoID) {
        return repoPreview.datosExtraDeProducto(productoID);
    }

    public void agregarACarrito(int productoID) {
        repoPreview.agregarACarrito(productoID);
    }

    public LiveData<List<Categoria>> getLasCategorias() {
        return repoTienda.getLasCategorias();
    }
}
