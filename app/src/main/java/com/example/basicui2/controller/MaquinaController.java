package com.example.basicui2.controller;

import com.example.basicui2.models.Administrador;
import com.example.basicui2.models.MaquinaReciclaje;

import java.util.ArrayList;

public class MaquinaController {
    private static ArrayList<MaquinaReciclaje> listaMaquina = new ArrayList<>();

    public static void addAdmin(MaquinaReciclaje machine){
        listaMaquina.add(machine);
    }

    public static MaquinaReciclaje findMachine(String code){
        for(MaquinaReciclaje c : listaMaquina){
            if(c.getCodigo().equalsIgnoreCase(code)){
                return c;
            }
        }
        return null;
    }

    public static void agregarMaquina(MaquinaReciclaje admin) {
        listaMaquina.add(admin);
    }

    public static ArrayList<MaquinaReciclaje> findAll(){
        return listaMaquina;
    }

    public static void eliminarMaquina(String adminId) {
        for (MaquinaReciclaje admin : listaMaquina) {
            if (admin.getCodigo().equals(adminId)) {
                listaMaquina.remove(admin);
                break;
            }
        }
    }

    public static void fill(){

        if(listaMaquina.size() == 0){
            addAdmin(new MaquinaReciclaje("978892282","Santiago"));
            addAdmin(new MaquinaReciclaje("832401234","Puerto Montt"));
            addAdmin(new MaquinaReciclaje("123","Arica"));

        }

    }

    public ArrayList<MaquinaReciclaje> obtenerMaquinas() {
        return listaMaquina;
    }
}
