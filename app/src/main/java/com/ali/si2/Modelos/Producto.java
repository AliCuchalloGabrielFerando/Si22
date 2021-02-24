package com.ali.si2.Modelos;

import org.w3c.dom.Text;

import java.io.Serializable;

public class Producto  implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private String url_imagen;
    private String url_3d;
    private int calificacion;
    private int cantidad;
    private int garantia_id;

    public Producto(int id, String nombre, String descripcion, float precio, String url_imagen, String url_3d, int calificacion, int cantidad, int garantia_id) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.url_imagen = url_imagen;
        this.url_3d = url_3d;
        this.calificacion = calificacion;
        this.cantidad = cantidad;
        this.garantia_id = garantia_id;
    }

    public Producto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getUrl_3d() {
        return url_3d;
    }

    public void setUrl_3d(String url_3d) {
        this.url_3d = url_3d;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getGarantia_id() {
        return garantia_id;
    }

    public void setGarantia_id(int garantia_id) {
        this.garantia_id = garantia_id;
    }
}
