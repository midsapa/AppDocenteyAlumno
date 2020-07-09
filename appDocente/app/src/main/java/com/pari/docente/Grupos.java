package com.pari.docente;

public class Grupos {
    private int id;
    private String nombre;
    private String seccion;
    private String asunto;

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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Grupos() {
        this.id = id;
        this.nombre = nombre;
        this.seccion = seccion;
        this.asunto = asunto;
    }
}