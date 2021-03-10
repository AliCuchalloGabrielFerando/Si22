package com.ali.si2.Modelos;

public class Tarjeta {
    int id;
    String fecha;
    int numero;
    int cvv;
    int usuario_id;

    public Tarjeta() {
    }

    public Tarjeta(int id, String fecha, int numero, int cvv, int usuario_id) {
        this.id = id;
        this.fecha = fecha;
        this.numero = numero;
        this.cvv = cvv;
        this.usuario_id = usuario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
}
