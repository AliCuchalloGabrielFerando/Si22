package com.ali.si2.Repositorio;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Modelos.Producto;
import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoBusqueda {
    private ApiRequest apiRequest;
    private Context context;

    public RepoBusqueda(Context context) {
        apiRequest = RetrofitRequest.getRetrofitInstance(context).create(ApiRequest.class);
        this.context = context;
    }

    public MutableLiveData<List<Producto>> getProductos(String nombre) {
        MutableLiveData<List<Producto>> mutableLiveData = new MutableLiveData<>();
        Map<String,String> map = new HashMap<>();
        map.put("nombre",nombre);
        apiRequest.buscarProductos(map).enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(response.isSuccessful() && response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }

    public MutableLiveData<List<Producto>> getProductosFiltrados(Map<String, String> map) {
        MutableLiveData<List<Producto>> mutableLiveData = new MutableLiveData<>();
        apiRequest.buscarProductosFiltrados(map).enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });
        return  mutableLiveData;
    }
}
