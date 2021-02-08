package com.ali.si2.Repositorio;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Modelos.Producto;
import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoTienda {
    private ApiRequest apiRequest;
    private Context context;

    public RepoTienda(Context context) {
        this.apiRequest = RetrofitRequest.getRetrofitInstance(context).create(ApiRequest.class);
        this.context=context;
    }

    public MutableLiveData<List<Producto>> getAllProductos(int categoria) {
        MutableLiveData<List<Producto>> mutableLiveData = new MutableLiveData<>();
        Map<String,String> map = new HashMap<>();
        map.put("categoriaID",String.valueOf(categoria));
        apiRequest.productoPorCategoria(map)
                .enqueue(new Callback<List<Producto>>() {
                    @Override
                    public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                        if (response.isSuccessful() && response.body() != null){
                            Log.d("TAG"," no error");
                            mutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Producto>> call, Throwable t) {
                        Log.d("TAG","error");
                    }
                });
    return mutableLiveData;}
}
