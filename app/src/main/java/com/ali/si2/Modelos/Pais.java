package com.ali.si2.Modelos;

import java.util.List;

public class Pais {
    private int id;
    private String nombre;
    private List<Ciudad> ciudades;

    public Pais(int id, String nombre, List<Ciudad> ciudades) {
        this.id = id;
        this.nombre = nombre;
        this.ciudades = ciudades;
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

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
}
