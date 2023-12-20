package com.example.basicui2.models;

import java.io.Serializable;

public class MaquinaReciclaje implements Serializable {
    private String codigo;
    private String ubicacion;
    private int nivelGas;
    private ArduinoData dispositivo;

    // Constructor
    public MaquinaReciclaje(String codigo, String ubicacion) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.nivelGas = 0;
        this.dispositivo = new ArduinoData();
    }
    public MaquinaReciclaje(){}

    public ArduinoData getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(ArduinoData dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getNivelGas() {
        return nivelGas;
    }

    public void setNivelGas(int nivelGas) {
        this.nivelGas = nivelGas;
    }
}
