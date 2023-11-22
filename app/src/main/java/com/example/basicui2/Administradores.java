package com.example.basicui2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.basicui2.adapters.AdminAdapter;
import com.example.basicui2.controller.AdminController;
import com.example.basicui2.models.Administrador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Administradores extends AppCompatActivity {

    ArrayList<Administrador> adminArrayList;
    ListView lvCounter;
    private AdminAdapter l;

    String nombreAdmin;
    Administrador adminFound;
    public boolean searching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administradores);

        SearchView buscaReg=  findViewById(R.id.svbuscaRegistro);
        lvCounter = findViewById(R.id.lvCounter);
        Button add = findViewById(R.id.btn_add_admin);


        AdminController.fill();
        adminArrayList = new ArrayList<>(AdminController.findAll());
        ArrayList<Administrador> lo = new ArrayList<>(adminArrayList);
        AdminAdapter list= new AdminAdapter(this,adminArrayList);
        lvCounter.setAdapter(list);
        list.notifyDataSetChanged();

        adminList();

        l = (AdminAdapter) lvCounter.getAdapter();

        Bundle newAdmin = getIntent().getBundleExtra("Administrador");
        Bundle updateAdmin = getIntent().getBundleExtra("Update");

        if (newAdmin != null) {

            Toast.makeText(this,"Admin has been created",Toast.LENGTH_SHORT).show();
            Administrador admin = (Administrador) newAdmin.getSerializable("key_one");
            MainActivity.databaseReference.child("Administrador").child(admin.getId()).setValue(admin);
            //AdminController.addAdmin(admin);
            list.notifyDataSetChanged();

        }

        if (updateAdmin!=null){

            Administrador admin = (Administrador) updateAdmin.getSerializable("uAdmin");
            Toast.makeText(this,"Admin has been modified",Toast.LENGTH_SHORT).show();
            MainActivity.databaseReference.child("Administrador").child(admin.getId()).setValue(admin);
            list.notifyDataSetChanged();

        }

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), anadirAdministrador.class);
                intent.putExtra("add","admin");
                finish();
                startActivity(intent);
            }

        });

        buscaReg.setOnQueryTextListener(new SearchView.OnQueryTextListener() {



            @Override
            public boolean onQueryTextSubmit(String nombre) {
                searching=true;
                nombreAdmin= nombre;
                adminList();


                //Administrador r = AdminController.findAdmin(nombre);
                //Log.d("AdminSearch", "Query submitted: " + nombreAdmin);

                /*if (adminFound!=null){

                    l.clear();
                    l.add(adminFound);
                    lvCounter.setAdapter(l);
                    l.notifyDataSetChanged();
                }*/

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.isEmpty()){

                    searching=false;
                    l.clear();
                    adminList();
                    l.notifyDataSetChanged();

                }

                return false;
            }
        });

    }

    private void adminList(){
        MainActivity.databaseReference.child("Administrador").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AdminAdapter adapt = (AdminAdapter)lvCounter.getAdapter();
                adapt.clear();

                if(searching){
                    for(DataSnapshot item: snapshot.getChildren()){
                        Administrador a = item.getValue(Administrador.class);

                        if (a != null && a.getNombre() != null && a.getNombre().equals(nombreAdmin)) {

                            adminFound = a;

                            //Log.d("AdminSearch", "Query submitted: " + adminFound);

                            Toast.makeText(getApplicationContext(),
                                    String.format("Admin %s has been Found. ",adminFound.getNombre()),Toast.LENGTH_SHORT).show();

                            l.clear();
                            l.add(adminFound);
                            lvCounter.setAdapter(l);
                            l.notifyDataSetChanged();

                            return;

                        }

                    }
                }

                if(!searching){
                    for(DataSnapshot item: snapshot.getChildren()){
                        Administrador a = item.getValue((Administrador.class));
                        adminArrayList.add(a);
                    }

                    AdminAdapter list= new AdminAdapter(getApplicationContext(),adminArrayList);
                    lvCounter.setAdapter(list);
                    list.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    public void exitCounter(View v){
        finish();
    }

}