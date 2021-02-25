package com.ali.si2.VistaModelo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ali.si2.Modelos.ProductoCarrito;
import com.ali.si2.Repositorio.RepoCarrito;

import java.util.HashMap;
import java.util.List;

public class VMCarrito extends ViewModel {
    RepoCarrito repoCarrito;
    public void initRepo(Context context){
    repoCarrito = new RepoCarrito(context);
    }

    public LiveData<List<ProductoCarrito>> getProductos() {
        return repoCarrito.getProductosCarrito();
    }

    public void eliminarProducto(int productoID) {
        repoCarrito.eliminarProducto(productoID);
    }

    public void actualizarCompraProducto(int productoID, int valor) {
        repoCarrito.actualizarCompraProducto(productoID,valor);
    }

    public void pagar(HashMap<String, Object> map) {
        repoCarrito.compra(map);
    }
}
