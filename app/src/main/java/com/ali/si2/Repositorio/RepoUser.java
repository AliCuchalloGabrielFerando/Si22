package com.ali.si2.Repositorio;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Modelos.Pais;
import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoUser {
    private ApiRequest apiRequest;
    private Context context;

    public RepoUser(Context context) {
        this.apiRequest = RetrofitRequest.getRetrofitInstance(context).create(ApiRequest.class);
        this.context=context;
    }
    public MutableLiveData<String> login(Map<String,String> map){
        MutableLiveData<String> datos=new MutableLiveData<>();
        apiRequest.login(map)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()&&response.body()!=null) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("userToken", Context.MODE_PRIVATE);
                            if(!response.body().get("error").isJsonNull()){
                                datos.setValue(response.body().get("error").getAsString());
                            }else {
                                if (!response.body().get("verification").isJsonNull()) {
                                    Log.d("Por aqui", "si paso");


                                    datos.setValue("verificado");
                                    // User usuario=new Gson().fromJson(user,User.class);
                                    // datos.setValue(usuario);
                                } else {
                                    datos.setValue("No verificado");
                                }
                                String token = response.body().get("token").getAsString();
//                            String user = response.body().getAsJsonObject("user").toString();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", token);
                                // editor.putString("user", user);
                                editor.commit();
                            }
                            RetrofitRequest.delete();
                            apiRequest = RetrofitRequest.getRetrofitInstance(context).create(ApiRequest.class);
                        }else{
                            datos.setValue(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        datos.setValue(t.getMessage());
                    }
                });
        return datos;
    }

    public MutableLiveData<Boolean> logout() {
        MutableLiveData<Boolean> datos=new MutableLiveData<>();
        apiRequest.logout()
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()&&response.body()!=null) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("userToken", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.remove("token");
                            // editor.remove("user");
                            editor.commit();
                            RetrofitRequest.delete();
                            datos.setValue(true);

                        }else{
                            datos.setValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        datos.setValue(false);
                    }
                });
        return datos;
    }

    public MutableLiveData<Boolean> crearUsuario(Map<String, String> map) {
        MutableLiveData<Boolean> datos=new MutableLiveData<>();
        apiRequest.crearCuenta(map)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()&&response.body()!=null) {
                           /* SharedPreferences sharedPreferences = context.getSharedPreferences("userToken", Context.MODE_PRIVATE);
                            String token = response.body().get("token").getAsString();
//                            String user = response.body().getAsJsonObject("user").toString();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", token);
                            // editor.putString("user", user);
                            editor.commit();*/
                            // User usuario=new Gson().fromJson(user,User.class);
                            // datos.setValue(usuario);
                            datos.setValue(true);
                            RetrofitRequest.delete();
                            apiRequest = RetrofitRequest.getRetrofitInstance(context).create(ApiRequest.class);
                        }else{
                            datos.setValue(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        datos.setValue(false);
                    }
                });
        return datos;
    }


    public MutableLiveData<String> recuperar(Map<String, String> map) {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        apiRequest.recuperar(map)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()&&response.body()!=null) {
                            mutableLiveData.setValue(response.body().get("respuesta").getAsString());

                        }else{
                            mutableLiveData.setValue("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mutableLiveData.setValue("fallo");
                    }
                });
        return mutableLiveData;
    }

    public MutableLiveData<List<Pais>> getPaises() {
        MutableLiveData<List<Pais>> mutableLiveData = new MutableLiveData<>();
        apiRequest.datalogin()
                .enqueue(new Callback<List<Pais>>() {
                    @Override
                    public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                        if(response.isSuccessful()&&response.body()!=null) {
                            List<Pais> listaPaises= new ArrayList<>();
                            listaPaises = response.body();
                            mutableLiveData.setValue(listaPaises);

                        }else{
                            mutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Pais>> call, Throwable t) {
                        mutableLiveData.setValue(null);
                    }
                });
        return mutableLiveData;
    }

    public MutableLiveData<Boolean> validar() {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        apiRequest.validar()
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()&&response.body()!=null) {

                            mutableLiveData.setValue(true);

                        }else{
                            mutableLiveData.setValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mutableLiveData.setValue(false);
                    }
                });
        return mutableLiveData;
    }
}