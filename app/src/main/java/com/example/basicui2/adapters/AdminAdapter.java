package com.example.basicui2.adapters;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;

import com.example.basicui2.MainActivity;
import com.example.basicui2.anadirAdministrador;
import com.example.basicui2.controller.AdminController;
import com.example.basicui2.R;
import com.example.basicui2.models.Administrador;

import java.util.ArrayList;

public class AdminAdapter extends ArrayAdapter<Administrador> {

    static final int ADMIN_REQUEST = 1;  // editar

    public AdminAdapter(Context context, ArrayList<Administrador> admin) {
        super(context,0, admin);
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        Administrador admin = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bat_item, parent, false);
        }

        TextView quan = convertView.findViewById(R.id.tv_telefono);
        TextView bat = convertView.findViewById(R.id.tv_nombre);
        Button delete = convertView.findViewById(R.id.btn_opcion);
        Button update = convertView.findViewById(R.id.btn_update);

        delete.setOnClickListener(v -> {
            MainActivity.databaseReference.child("Administrador").child(admin.getId()).removeValue();
            //AdminController.eliminarAdmin(admin.getId());
            notifyDataSetChanged();
        });

        update.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), anadirAdministrador.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("update",admin.getId());
            getContext().startActivity(i);

        });


        String type= admin.toString();
        String qty= admin.getTelefono();

        bat.setText(type);
        quan.setText(qty);

        return convertView;
    }

}
