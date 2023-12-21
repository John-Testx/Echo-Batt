package com.example.basicui2.models;

import java.io.Serializable;

public class ArduinoData implements Serializable {
    private String nivelGas;
    private String batDimension;

    public ArduinoData() {
        nivelGas="0";
        batDimension="0";
    }

    public String getNivelGas() {
        return nivelGas;
    }

    public void setNivelGas(String nivelGas) {
        this.nivelGas = nivelGas;
    }

    public String getBatDimension() {
        return batDimension;
    }

    public void setBatDimension(String batDimension) {
        this.batDimension = batDimension;
    }
}