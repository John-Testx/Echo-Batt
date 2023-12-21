package com.example.basicui2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicui2.adapters.MaquinaAdapter;
import com.example.basicui2.models.ArduinoData;
import com.example.basicui2.models.MaquinaReciclaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class DeviceInfo extends AppCompatActivity {

    public TextView nivelGas;
    public TextView batDimension;
    public TextView batType;
    public String codigoMaquina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        batType = findViewById(R.id.tv_bat_type);
        nivelGas= findViewById(R.id.tv_nivelgas);
        batDimension = findViewById(R.id.tv_dimension);

        getDispositivo();
        setArduinoData();

    }


    public void getDispositivo(){
        Bundle e = getIntent().getExtras();

        if (e!=null) {
            if (e.getSerializable("info") != null) {
                codigoMaquina = e.getString("info");
            }
        };
    }

    private void setArduinoData(){
        MainActivity.databaseReference.child("Maquina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item: snapshot.getChildren()){
                    MaquinaReciclaje a = item.getValue(MaquinaReciclaje.class);

                    if (a != null && a.getCodigo().equals(codigoMaquina)) {
                        String d = a.getDispositivo().getBatDimension();
                        String g = a.getDispositivo().getNivelGas();
                        calcularBateria(a.getDispositivo().getBatDimension());
                        batDimension.setText(d);
                        nivelGas.setText(g);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void calcularBateria(String i){
        float value = Float.parseFloat(i);
        if (value>3 && value<=5){
            batType.setText("AAA");
        }
        if (value>9 && value<=11){
            batType.setText("AA");
        }
        if (value>14 && value<=16){
            batType.setText("C-Type");
        }
    }
    

}