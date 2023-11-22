package com.example.basicui2.controller;

import com.example.basicui2.models.Administrador;

import java.util.ArrayList;

public class AdminController {
    private static ArrayList<Administrador> listaAdmin = new ArrayList<>();

    public static void addAdmin(Administrador admin){
        listaAdmin.add(admin);
    }

    public static Administrador findAdmin(String nombre){
        for(Administrador c : listaAdmin){
            if(c.getNombre().equalsIgnoreCase(nombre)){
                return c;
            }
        }
        return null;
    }

    public static void agregarAdmin(Administrador admin) {
        listaAdmin.add(admin);
    }

    public static ArrayList<Administrador> findAll(){
        return listaAdmin;
    }

    public static void eliminarAdmin(String adminId) {
        for (Administrador admin : listaAdmin) {
            if (admin.getId().equals(adminId)) {
                listaAdmin.remove(admin);
                break;
            }
        }
    }

    public static void fill(){

        if(listaAdmin.size() == 0){
            addAdmin(new Administrador("rut","admin1","apellido",
                    "email","978892282","0","username","password"));
            addAdmin(new Administrador("rut","admin2","apellido",
                    "email","978892282","1","username","password"));
            addAdmin(new Administrador("rut","admin3","apellido","email",
                    "978892282","2","username","password"));

        }

    }

    public ArrayList<Administrador> obtenerAdmins() {
        return listaAdmin;
    }

}
