package com.example.basicui2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.basicui2.adapters.AdminAdapter;
import com.example.basicui2.controller.AdminController;
import com.example.basicui2.models.Administrador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class anadirAdministrador extends AppCompatActivity {

    EditText etNombre, etTelefono;
    Button buttonCrear, btnUpdate;
    Administrador admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_administrador);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        buttonCrear = findViewById(R.id.btnCrear);
        btnUpdate = findViewById(R.id.btnUpdate);

        Bundle e = getIntent().getExtras();

        if (e!=null){
            if (e.getString("update") != null){
                String nombre= e.getString("update");
                getAdmin(nombre);

                if (admin!=null){
                    etNombre.setText(admin.getNombre());
                    etTelefono.setText(admin.getTelefono());
                    buttonCrear.setVisibility(View.GONE);
                    btnUpdate.setVisibility(View.VISIBLE);
                }

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nombre = etNombre.getText().toString();
                        String telefono = etTelefono.getText().toString();
                        admin.setNombre(nombre);
                        admin.setTelefono(telefono);
                        Intent respuestaIntent = new Intent(getApplicationContext(),Administradores.class);
                        Bundle args = new Bundle();
                        args.putSerializable("uAdmin",admin);
                        respuestaIntent.putExtra("Update",args);

                        finish();
                        startActivity(respuestaIntent);
                    }
                });
            }else if (e.getString("add") != null){

                buttonCrear.setVisibility(View.VISIBLE);
                btnUpdate.setVisibility(View.GONE);

                buttonCrear.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String nombre = etNombre.getText().toString();
                        String telefono = etTelefono.getText().toString();
                        String id = Integer.toString(UUID.randomUUID().hashCode());

                        Administrador nuevoAdministrador = new Administrador(id,nombre, telefono);

                        Intent respuestaIntent = new Intent(getApplicationContext(),Administradores.class);
                        Bundle args = new Bundle();
                        args.putSerializable("key_one", nuevoAdministrador);

                        respuestaIntent.putExtra("Administrador",args);

                        finish();
                        startActivity(respuestaIntent);

                    }
                });
            }
        }

        /* COPY
        * String nombre= e.getString("update");
                Administrador x = AdminController.findAdmin(nombre);
                etNombre.setText(x.getNombre());
                etTelefono.setText(x.getTelefono());
                buttonCrear.setVisibility(View.GONE);
                btnUpdate.setVisibility(View.VISIBLE);
        *
        * */

    }

    private void getAdmin(String id){
        MainActivity.databaseReference.child("Administrador").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot item: snapshot.getChildren()){
                    if (item.getKey().equals(id)){
                        admin = item.getValue((Administrador.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}