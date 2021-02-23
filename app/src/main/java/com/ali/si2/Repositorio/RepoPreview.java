package com.ali.si2.Repositorio;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public MutableLiveData<HashMap<String,Object>> getMarcaGarantiaDeProducto(int productoID) {
        MutableLiveData<HashMap<String,Object>> mutableLiveData = new MutableLiveData<>();
        Map<String,String> map = new HashMap<>();
        HashMap<String,Object> maper = new HashMap<>();
        map.put("productoID",String.valueOf(productoID));

        apiRequest.marcaGarantiaDeProducto(map).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful() && response.body() != null) {

                    JsonObject marcaObject = response.body().getAsJsonObject("marca");
                    JsonObject garantiaObject = response.body().getAsJsonObject("garantia");
                    JsonObject promocionObject = response.body().getAsJsonObject("promocion");
                    Gson gson = new Gson();
                    Marca marca = gson.fromJson(marcaObject, Marca.class);
                    Garantia garantia = gson.fromJson(garantiaObject, Garantia.class);
                    Promocion promocion = gson.fromJson(promocionObject,Promocion.class);
                    maper.put("marca", marca);
                    maper.put("garantia", garantia);
                    maper.put("promocion",promocion);
                    mutableLiveData.setValue(maper);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


        return  mutableLiveData;
    }
}
