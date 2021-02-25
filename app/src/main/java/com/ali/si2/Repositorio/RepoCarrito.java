package com.ali.si2.Repositorio;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Modelos.ProductoCarrito;
import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoCarrito {
    private ApiRequest apiRequest;
    private Context context;

    public RepoCarrito(Context context) {
        this.apiRequest = RetrofitRequest.getRetrofitInstance(context).create(ApiRequest.class);
        this.context = context;
    }

    public MutableLiveData<List<ProductoCarrito>> getProductosCarrito() {
        MutableLiveData<List<ProductoCarrito>> mutableLiveData = new MutableLiveData<>();
        apiRequest.productosDeCarrito().enqueue(new Callback<List<ProductoCarrito>>() {
            @Override
            public void onResponse(Call<List<ProductoCarrito>> call, Response<List<ProductoCarrito>> response) {
                if (response.isSuccessful() && response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductoCarrito>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public void eliminarProducto(int productoID) {
        Map<String,String> map = new HashMap<>();
        map.put("productoID",String.valueOf(productoID));
        apiRequest.eliminarProducto(map).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if ( response.isSuccessful() && response.body() != null){

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void actualizarCompraProducto(int productoID, int valor) {
        Map<String,String> map = new HashMap<>();
        map.put("productoID",String.valueOf(productoID));
        map.put("valor",String.valueOf(valor));
        apiRequest.actualizarCompraProducto(map).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null){

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}