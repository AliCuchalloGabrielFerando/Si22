package com.ali.si2.Repositorio;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Modelos.ProductoCarrito;
import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;
import com.google.gson.JsonObject;

import java.util.ArrayList;
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
        Map<String,String> map = new HashMap<>();
       apiRequest.productosDeCarrito(map).enqueue(new Callback<List<ProductoCarrito>>() {
           @Override
           public void onResponse(Call<List<ProductoCarrito>> call, Response<List<ProductoCarrito>> response) {
               if (response.isSuccessful()){
                   if(response.body() != null){

                       mutableLiveData.setValue(response.body());
                   }else{
                       List<ProductoCarrito> lista= new ArrayList<>();
                       mutableLiveData.setValue(lista);
                   }
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

/*    public MutableLiveData<Boolean> compra(Map<String, Object> map) {
        MutableLiveData<Boolean> mutableLiveData=new MutableLiveData<>();
        apiRequest.compra(map).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {


                    mutableLiveData.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG",t.getMessage());
                mutableLiveData.setValue(false);
            }
        });
        return mutableLiveData;
    }*/
}
