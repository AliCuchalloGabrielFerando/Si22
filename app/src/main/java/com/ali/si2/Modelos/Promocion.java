package com.ali.si2.Modelos;

public class Promocion {
    private int id;
    private String nombre;
    private String descripcion;
    private byte descuento;

    public Promocion(int id, String nombre, String descripcion, byte descuento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
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

    public byte getDescuento() {
        return descuento;
    }

    public void setDescuento(byte descuento) {
        this.descuento = descuento;
    }
}
