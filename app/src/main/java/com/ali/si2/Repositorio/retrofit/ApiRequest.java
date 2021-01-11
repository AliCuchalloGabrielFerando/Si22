package com.ali.si2.Repositorio.retrofit;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {
    /*   @GET("instrumentos")
       Call<List<Instrumento>> getInstrumentos();

       @GET("promociones")
       Call<List<Promocion>> getPromociones();

       @GET("cursos")
       Call<List<Curso>> getCursos();

       @GET("cursos/{codigo}")
       Call<Curso> getMaterias(@Path("codigo") int codigo );

       @GET("docentes")
       Call<List<Docente>> getDocentes();

       @GET("administrativos")
       Call<List<Administrativo>> getAdministrativos();

       @GET("prestamos")
       Call<List<Prestamo>> getPrestamos();

       @GET("devoluciones")
       Call<List<Devolucion>> getDevoluciones();

       //@Headers({"Content-type: application/json;charset=UTF-8"})
       @GET("almacenes")
       Call<List<Almacen>> getAlmacenes();

       @GET("grupos/{materia_sigla}")
       Call<List<Grupo>> getGrupos(@Path("materia_sigla") String sigla);
   */


    @POST("login")
    Call<JsonObject> login(@Body Map<String, String> body);
    @POST("register")
    Call<JsonObject> crearCuenta(@Body Map<String, String> body);

    @POST("logout")
    Call<JsonObject> logout();
}
