package com.ali.si2.Modelos;

public class Garantia {
    private int id;
    private int tiempo;

    public Garantia(int id, int tiempo) {
        this.id = id;
        this.tiempo = tiempo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}
