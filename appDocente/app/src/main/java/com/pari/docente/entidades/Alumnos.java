package com.pari.docente.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Alumnos {
    public Integer codigo;
    public String nombre;
    public String apellido;
    public String correo;

    public Alumnos(Integer codigo, String nombre, String apellido, String correo, String dato, Bitmap imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.dato = dato;
        this.imagen = imagen;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
        try{
            byte[] bytecode= Base64.decode(dato,Base64.DEFAULT);
            imagen= BitmapFactory.decodeByteArray(bytecode, 0,bytecode.length);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    private String dato;
    private Bitmap imagen;

    public Alumnos() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
