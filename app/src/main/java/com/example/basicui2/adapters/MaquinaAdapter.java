package com.example.basicui2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.basicui2.MainActivity;
import com.example.basicui2.R;
import com.example.basicui2.controller.AdminController;
import com.example.basicui2.controller.MaquinaController;
import com.example.basicui2.models.MaquinaReciclaje;

import java.util.ArrayList;

public class MaquinaAdapter extends ArrayAdapter<MaquinaReciclaje>{
        public MaquinaAdapter(Context context, ArrayList<MaquinaReciclaje> dev) {
            super(context,0, dev);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MaquinaReciclaje dev = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.bat_item, parent, false);
            }

            TextView quan = convertView.findViewById(R.id.tv_telefono);
            TextView bat = convertView.findViewById(R.id.tv_nombre);
            Button delete = convertView.findViewById(R.id.btn_opcion);

            delete.setOnClickListener(v -> {
                MainActivity.databaseReference.child("Maquina").child(dev.getCodigo()).removeValue();
                //MaquinaController.eliminarMaquina(dev.getCodigo());
                notifyDataSetChanged();
            });

            String type= dev.getCodigo();
            String qty= dev.getUbicacion();

            bat.setText(type);
            quan.setText(qty);

            return convertView;
        }
}
