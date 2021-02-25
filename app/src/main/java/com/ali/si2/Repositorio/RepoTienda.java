package com.ali.si2.Repositorio;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Modelos.Categoria;
import com.ali.si2.Modelos.Marca;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;
import com.ali.si2.Utilidades.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

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
                            mutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Producto>> call, Throwable t) {
                    }
                });
    return mutableLiveData;}

    public MutableLiveData<List<Categoria>> getAllCategorias() {
        MutableLiveData<List<Categoria>> mutableLiveData = new MutableLiveData<>();
        Map<String,String> map = new HashMap<>();
        apiRequest.categoriasActivadas()
                .enqueue(new Callback<List<Categoria>>() {
                    @Override
                    public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                        if (response.isSuccessful() && response.body()!= null){
                            mutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Categoria>> call, Throwable t) {

                    }
                });
        return mutableLiveData;
    }

    public MutableLiveData<List<Producto>> getProductosSimilares(Producto producto) {
        MutableLiveData<List<Producto>> mutableLiveData = new MutableLiveData<>();
        Map<String,String> map = new HashMap<>();
        map.put("productoID",String.valueOf(producto.getId()));
        apiRequest.productosSimilares(map).enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body()!= null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<HashMap<String,Object>> getMasVendido() {
        MutableLiveData<HashMap<String,Object>> mutableLiveData = new MutableLiveData<>();
        HashMap<String,Object> map = new HashMap<>();
        apiRequest.productoMasVendido().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body()!= null){
                    Log.d("tag,","pasa por aqui");
                    JsonObject producto1 = (response.body().getAsJsonObject("producto"));
                    JsonObject marca1 = (response.body().getAsJsonObject("marca"));
                    Gson gson = new Gson();
                    Log.d("Tag","le llegaron");
                    Producto producto = gson.fromJson(producto1,Producto.class);
                    Marca marca = gson.fromJson(marca1,Marca.class);
                    map.put("producto",producto);
                    map.put("marca",marca);
                    mutableLiveData.setValue(map);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Utility.logD();
                Log.d("E",t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Categoria>> getCategoriasDeUsuario() {
        MutableLiveData<List<Categoria>> mutableLiveData = new MutableLiveData<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences("userToken", Context.MODE_PRIVATE);

        Map<String,String> map = new HashMap<>();
        map.put("usuarioID",sharedPreferences.getString("id",""));
        apiRequest.categoriasDeUsuario(map).enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body()!= null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public void guardarPreferencias(List<Integer> listaPreferencias) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userToken", Context.MODE_PRIVATE);

        Map<String,Object> map = new HashMap<>();
        map.put("usuarioID",sharedPreferences.getString("id",""));
        map.put("preferencias",listaPreferencias);
        apiRequest.guardarPreferencias(map).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful() && response.body() != null){
                    Utility.logD();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Categoria>> getLasCategorias() {
        MutableLiveData<List<Categoria>> mutableLiveData = new MutableLiveData<>();
        Map<String,String> map = new HashMap<>();
        apiRequest.categoriasActivas()
                .enqueue(new Callback<List<Categoria>>() {
                    @Override
                    public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                        if (response.isSuccessful() && response.body()!= null){
                            mutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Categoria>> call, Throwable t) {

                    }
                });
        return mutableLiveData;
    }
}
