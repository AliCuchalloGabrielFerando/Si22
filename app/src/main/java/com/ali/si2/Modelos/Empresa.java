package com.ali.si2.Modelos;

public class Empresa {
    private int id;
    private String nombre;
    private int nit;
    private String image_url;

    public Empresa(int id, String nombre, int nit, String image_url) {
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.image_url = image_url;
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

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
