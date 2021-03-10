package com.ali.si2.Repositorio;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Modelos.Tarjeta;
import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoCompra {

    private ApiRequest apiRequest;
    private Context context;

    public RepoCompra(Context context) {
        this.apiRequest = RetrofitRequest.getRetrofitInstance(context).create(ApiRequest.class);
        this.context = context;
    }

    public MutableLiveData<Boolean> compra(Map<String, Object> map) {
        MutableLiveData<Boolean> mutableLiveData=new MutableLiveData<>();
        apiRequest.compra(map).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(true);
                }else {
                    mutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG",t.getMessage());
                mutableLiveData.setValue(false);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<Tarjeta> registrarTarjeta(Map<String, Object> map) {
        MutableLiveData<Tarjeta> mutableLiveData=new MutableLiveData<>();
        apiRequest.registrarTarjeta(map).enqueue(new Callback<Tarjeta>() {
            @Override
            public void onResponse(Call<Tarjeta> call, Response<Tarjeta> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }else {
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Tarjeta> call, Throwable t) {
                Log.d("TAG",t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<Tarjeta> obtenerTarjeta() {
        MutableLiveData<Tarjeta> mutableLiveData=new MutableLiveData<>();
        apiRequest.obtenerTarjeta().enqueue(new Callback<Tarjeta>() {
            @Override
            public void onResponse(Call<Tarjeta> call, Response<Tarjeta> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }else {
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Tarjeta> call, Throwable t) {
                Log.d("TAG",t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
