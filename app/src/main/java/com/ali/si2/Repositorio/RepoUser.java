package com.ali.si2.Repositorio;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ali.si2.Repositorio.retrofit.ApiRequest;
import com.ali.si2.Repositorio.retrofit.RetrofitRequest;
import com.google.gson.JsonObject;


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
    public MutableLiveData<Boolean> login(Map<String,String> map){
        MutableLiveData<Boolean> datos=new MutableLiveData<>();
        apiRequest.login(map)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()&&response.body()!=null) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("userToken", Context.MODE_PRIVATE);
                            String token = response.body().get("token").getAsString();
//                            String user = response.body().getAsJsonObject("user").toString();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", token);
                           // editor.putString("user", user);
                            editor.commit();
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
}
