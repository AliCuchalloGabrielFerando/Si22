package com.ali.si2.VistaModelo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.ali.si2.Modelos.Pais;
import com.ali.si2.Repositorio.RepoUser;

import java.util.List;
import java.util.Map;

public class VMUser extends ViewModel {
    RepoUser repoUser;
    public void initRepo(Context context){
        repoUser=new RepoUser(context);
    }
    public LiveData<Boolean> login(Map<String,String> map){
     return repoUser.login(map);
    }
    public LiveData<Boolean> logout(){
        return repoUser.logout();
    }

    public LiveData<Boolean> crearUsuario(Map<String, String> map) {
        return repoUser.crearUsuario(map);
    }


    public LiveData<String> recuperar(Map<String, String> map) {

        return  repoUser.recuperar(map);
    }

    public LiveData<List<Pais>> getPaises() {
        return repoUser.getPaises();
    }
}
