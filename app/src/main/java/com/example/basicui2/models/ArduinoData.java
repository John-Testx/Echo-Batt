package com.example.basicui2.models;

import java.io.Serializable;

public class ArduinoData implements Serializable {
    private int nivelGas;
    private int batDimension;

    public ArduinoData() {
        nivelGas=0;
        batDimension=0;
    }

    public int getNivelGas() {
        return nivelGas;
    }

    public void setNivelGas(int nivelGas) {
        this.nivelGas = nivelGas;
    }

    public int getBatDimension() {
        return batDimension;
    }

    public void setBatDimension(int batDimension) {
        this.batDimension = batDimension;
    }
}
