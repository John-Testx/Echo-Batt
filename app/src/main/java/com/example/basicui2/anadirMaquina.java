package com.example.basicui2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.basicui2.controller.AdminController;
import com.example.basicui2.controller.MaquinaController;
import com.example.basicui2.models.Administrador;
import com.example.basicui2.models.MaquinaReciclaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class anadirMaquina extends AppCompatActivity {

    EditText etCodigo, etUbicacion;
    Button buttonCrear;
    MaquinaReciclaje maquina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_maquina);

        etCodigo = findViewById(R.id.etCodigo);
        etUbicacion = findViewById(R.id.etUbicacion);
        buttonCrear = findViewById(R.id.btnCrear);

        buttonCrear = findViewById(R.id.btnCrear);

        Bundle e = getIntent().getExtras();

        if (e!=null){
            if (e.getString("add") != null){

                buttonCrear.setVisibility(View.VISIBLE);
                buttonCrear.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String codigo = Integer.toString(UUID.randomUUID().hashCode());
                        String ubicacion = etUbicacion.getText().toString();

                        MaquinaReciclaje nuevaMaquina = new MaquinaReciclaje(codigo,ubicacion);

                        Intent respuestaIntent = new Intent(getApplicationContext(),Maquinas.class);
                        Bundle args = new Bundle();
                        args.putSerializable("key_one", nuevaMaquina);

                        respuestaIntent.putExtra("Maquina",args);

                        finish();
                        startActivity(respuestaIntent);

                    }
                });
            }
        }
    }


    private void getMaquina(String codigo){
        MainActivity.databaseReference.child("Maquina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot item: snapshot.getChildren()){
                    if (item.getKey().equals(codigo)){
                        maquina = item.getValue((MaquinaReciclaje.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}