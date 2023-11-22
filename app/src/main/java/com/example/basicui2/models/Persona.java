package com.example.basicui2.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Persona implements Serializable {
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    // Constructor
    public Persona(String rut, String nombre, String apellido, String email, String telefono) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    public Persona(String telefono, String nombre){
        this.telefono =telefono;
        this.nombre =nombre;
    }
    public Persona(){}

    // Getters y Setters
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
