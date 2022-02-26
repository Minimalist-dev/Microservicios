package com.example.Sistema.modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "SistemaMysql")//Asigna un nombre a de tabla: la tabla será "sistema_spring"
public class GetAndSet {
    
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String marca;
    private Double precio;
    private String fecha;

    public GetAndSet() {
        
    }

    public GetAndSet(int id, String nombre, String marca, Double precio, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.fecha = fecha;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "GetAndSet{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", fecha=" + fecha + '}';
    }

}
