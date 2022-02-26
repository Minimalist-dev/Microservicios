package com.example.Sistema.modelo;

import java.util.ArrayList;


public class Modelo {
    private String nombre;
    private String correo;

    public static ArrayList<Object> compartir = new ArrayList<Object>();

    public Modelo() {
    }

    public Modelo(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Modelo{" + "nombre=" + nombre + ", correo=" + correo + '}';
    }

    public static void 
    paraTercero(Object objeto) {
        compartir.add(objeto);
    }
}
