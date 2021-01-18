package com.ali.si2.Modelos;

public class Ciudad {
    private  int id;
    private  String nombre;
    private int pais_id;

    public Ciudad(int id, String nombre, int pais_id) {
        this.id = id;
        this.nombre = nombre;
        this.pais_id = pais_id;
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

    public int getPais_id() {
        return pais_id;
    }

    public void setPais_id(int pais_id) {
        this.pais_id = pais_id;
    }
}
