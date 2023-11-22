package com.example.basicui2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicui2.models.Administrador;
import com.example.basicui2.models.SuperAdministrador;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity{

    private EditText etName;
    private EditText etPwd;

    FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;

    private String user;
    private String pwd;

    //private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= findViewById(R.id.etSend);
        etPwd= findViewById(R.id.etSend2);

        startFireBase();
    }

    public void login(View view){
        try {

            user= etName.getText().toString();
            pwd = etPwd.getText().toString();

            SuperAdministrador x=
                    new SuperAdministrador("","john","","","","0","john","xxx");

            if(user.equalsIgnoreCase(x.getUsername()) && pwd.equalsIgnoreCase(x.getPassword())){
                Intent i = new Intent(this, Menu.class);
                this.finish();
                startActivity(i);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void startFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


}