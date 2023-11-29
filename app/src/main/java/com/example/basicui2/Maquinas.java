package com.example.basicui2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.basicui2.adapters.AdminAdapter;
import com.example.basicui2.adapters.MaquinaAdapter;
import com.example.basicui2.controller.AdminController;
import com.example.basicui2.controller.MaquinaController;
import com.example.basicui2.models.Administrador;
import com.example.basicui2.models.MaquinaReciclaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Maquinas extends AppCompatActivity {

    ArrayList<MaquinaReciclaje> maquinaArrayList;
    ListView lvCounter;
    private MaquinaAdapter l;

    String ubicMaquina;
    ArrayList<MaquinaReciclaje> maquinas = new ArrayList<>();
    MaquinaReciclaje maquinaFound;
    public boolean searching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquinas);

        lvCounter = findViewById(R.id.lvCounter);

        Button add = findViewById(R.id.btn_add_admin);
        SearchView buscaMaquina=  findViewById(R.id.svBuscaMaquina);

        //Button find = findViewById(R.id.btn_find_admin);
        //EditText buscar = findViewById(R.id.etBuscar);

        MaquinaController.fill();
        maquinaArrayList = new ArrayList<>(MaquinaController.findAll());
        ArrayList<MaquinaReciclaje> lo = new ArrayList<>(maquinaArrayList);
        MaquinaAdapter list= new MaquinaAdapter(this,maquinaArrayList);
        lvCounter.setAdapter(list);
        list.notifyDataSetChanged();

        maquinaList();

        l = (MaquinaAdapter) lvCounter.getAdapter();

        Bundle newMaquina = getIntent().getBundleExtra("Maquina");

        if (newMaquina != null) {
            Toast.makeText(this,"Maquina has been created",Toast.LENGTH_SHORT).show();
            MaquinaReciclaje maquina = (MaquinaReciclaje) newMaquina.getSerializable("key_one");
            MainActivity.databaseReference.child("Maquina").child(maquina.getCodigo()).setValue(maquina);
            MaquinaController.agregarMaquina(maquina);
            list.notifyDataSetChanged();
        }

        buscaMaquina.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String ubicacion) {
                searching=true;
                ubicMaquina =  ubicacion;
                maquinaList();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.isEmpty()){

                    searching=false;
                    maquinas.clear();
                    l.clear();
                    maquinaList();
                    l.notifyDataSetChanged();

                }

                return false;
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), anadirMaquina.class);
                intent.putExtra("add","maquina");
                finish();
                startActivity(intent);
            }
        });

    }
    private void maquinaList(){
        MainActivity.databaseReference.child("Maquina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MaquinaAdapter adapt = (MaquinaAdapter)lvCounter.getAdapter();
                adapt.clear();

                if(searching){
                    for(DataSnapshot item: snapshot.getChildren()){
                        MaquinaReciclaje a = item.getValue(MaquinaReciclaje.class);

                        if (a != null && a.getUbicacion() != null &&
                                a.getUbicacion().equals(ubicMaquina) || a.getUbicacion().startsWith(ubicMaquina.substring(0, 3))) {

                            maquinaFound = a;
                            maquinas.add(a);
                            Toast.makeText(getApplicationContext(),
                                    String.format("Location in %s has been Found. ",maquinaFound.getUbicacion()),Toast.LENGTH_SHORT).show();

                        }

                    }

                    l.clear();
                    //l.add(maquinaFound);
                    l.addAll(maquinas);
                    lvCounter.setAdapter(l);
                    l.notifyDataSetChanged();

                }
                if (!searching) {
                    for (DataSnapshot item : snapshot.getChildren()) {
                        MaquinaReciclaje a = item.getValue((MaquinaReciclaje.class));
                        maquinaArrayList.add(a);
                    }

                    MaquinaAdapter list = new MaquinaAdapter(getApplicationContext(), maquinaArrayList);
                    lvCounter.setAdapter(list);
                    list.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void exitSummary(View v){
        finish();
    }
}