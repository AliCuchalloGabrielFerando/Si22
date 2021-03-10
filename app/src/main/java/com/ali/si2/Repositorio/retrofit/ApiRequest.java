package com.ali.si2.Repositorio.retrofit;

import com.ali.si2.Modelos.Categoria;
import com.ali.si2.Modelos.Marca;
import com.ali.si2.Modelos.Pais;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Modelos.ProductoCarrito;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

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
    @POST("obtenerporcategoria")
    Call<List<Producto>>productoPorCategoria(@Body Map<String,String> body);
    @POST("categoriasconproductos")
    Call<List<Categoria>>categoriasActivas();

    @POST("suscategorias")
    Call<List<Categoria>>categoriasActivadas();

    @POST("categoriasdeusuario")
    Call<List<Categoria>>categoriasDeUsuario(@Body Map<String,String> body);

    @POST("guardarcategoriasdeusuario")
    Call<Boolean>guardarPreferencias(@Body Map<String,Object>body);

    @POST("obtenersimilares")
    Call<List<Producto>>productosSimilares(@Body Map<String,String> body);

    @POST("obtenermasvendido")
    Call<JsonObject>productoMasVendido();
    //faltan estos->>

    @POST("datosextradeproducto")
    Call<JsonObject>datosExtraDeProducto(@Body Map<String,String> body);
    //
    @POST("productoalcarrito")
    Call<JsonObject> agregarACarrito(@Body Map<String,String> body);
    //
    @POST("productosdecarrito")
    Call<List<ProductoCarrito>>productosDeCarrito(@Body Map<String,String> body);
    //
    @POST("eliminarproducto")
    Call<Boolean>eliminarProducto(@Body Map<String,String> Body);
    //
    @POST("actualizarcompraproducto")
    Call<Boolean>actualizarCompraProducto(@Body Map<String,String> Body);
    //

    @POST("buscarproductos")
    Call<List<Producto>> buscarProductos(@Body Map<String,String> body);

    @POST("buscarproductosfiltrados")
    Call<List<Producto>> buscarProductosFiltrados(@Body Map<String,String> body);

    @POST("calificarproducto")
    Call<JsonObject>calificarProducto(@Body Map<String,String> body);

//marca awui
    @GET("datalogin")
    Call<List<Pais>> datalogin();

    @POST("send-mail")
    Call<JsonObject> validar(@Body Map<String, String> body);

    @POST("store/token")
    Call<JsonObject> guardarToken(@Body Map<String,String> body);

    @POST("login")
    Call<JsonObject> login(@Body Map<String, String> body);
    @POST("register")
    Call<JsonObject> crearCuenta(@Body Map<String, String> body);
    @POST("forgot-password")
    Call<JsonObject>recuperar(@Body Map<String,String> body);

    @POST("logout")
    Call<JsonObject> logout();


}
