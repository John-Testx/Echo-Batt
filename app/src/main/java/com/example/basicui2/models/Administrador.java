package com.example.basicui2.models;

import java.io.Serializable;

public class Administrador extends Persona implements Serializable {
    private String id;
    private String username;
    private String password;

    // Constructor
    public Administrador(String rut , String nombre, String apellido, String email, String telefono,
                         String id, String username, String password) {
        super(rut, nombre, apellido, email, telefono);
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Administrador(String id, String nombre, String telefono){
        super(telefono,nombre);
        this.id=id;
    }

    public Administrador(){}

    @Override
    public String toString() {
        return this.getNombre();
    }

    public String getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
