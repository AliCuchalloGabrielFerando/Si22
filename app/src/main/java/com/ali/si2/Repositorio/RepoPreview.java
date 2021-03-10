package com.ali.si2.Repositorio;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Modelos.Agregado;
import com.ali.si2.Modelos.Empresa;
import com.ali.si2.Modelos.Garantia;
import com.ali.si2.Modelos.Marca;
import com.ali.si2.Modelos.Promocion;
import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoPreview {
    private ApiRequest apiRequest;
    private Context context;

    public RepoPreview(Context context) {
        this.apiRequest = RetrofitRequest.getRetrofitInstance(context).create(ApiRequest.class);
        this.context = context;
    }

    public MutableLiveData<HashMap<String,Object>> datosExtraDeProducto(int productoID) {
        MutableLiveData<HashMap<String,Object>> mutableLiveData = new MutableLiveData<>();
        Map<String,String> map = new HashMap<>();
        HashMap<String,Object> maper = new HashMap<>();
        map.put("productoID",String.valueOf(productoID));

        apiRequest.datosExtraDeProducto(map).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Gson gson = new Gson();

                    Promocion promocion=new Promocion();
                    JsonObject marcaObject = response.body().getAsJsonObject("marca");
                    try {
                        JsonObject promocionObject = response.body().getAsJsonObject("promocion");
                        promocion = gson.fromJson(promocionObject,Promocion.class);
                    }catch (Exception e){

                    }
                    JsonObject garantiaObject = response.body().getAsJsonObject("garantia");
                    JsonObject enCarritoObject = response.body().getAsJsonObject("agregado");
                    JsonObject empresaObject = response.body().getAsJsonObject("empresa");
                    Marca marca = gson.fromJson(marcaObject, Marca.class);
                    Garantia garantia = gson.fromJson(garantiaObject, Garantia.class);
                    Agregado agregado = gson.fromJson(enCarritoObject,Agregado.class);
                    Empresa empresa = gson.fromJson(empresaObject,Empresa.class);

                    maper.put("marca", marca);
                    maper.put("garantia", garantia);
                    maper.put("promocion",promocion);
                    maper.put("agregado",agregado);
                    maper.put("empresa",empresa);
                    mutableLiveData.setValue(maper);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


        return  mutableLiveData;
    }

    public void agregarACarrito(int productoID) {
        Map<String,String> map = new HashMap<>();
        map.put("productoID",String.valueOf(productoID));
        apiRequest.agregarACarrito(map).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful() && response.body() != null){

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<Boolean> setCalificacion(Map<String, String> map) {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        apiRequest.calificarProducto(map).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful() && response.body() != null){
                    Gson gson = new Gson();
                    JsonObject banderaObject = response.body().getAsJsonObject("bandera");
                    Boolean bandera = gson.fromJson(banderaObject,Boolean.class);
                    mutableLiveData.setValue(bandera);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
