package com.example.basicui2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.basicui2.DeviceInfo;
import com.example.basicui2.MainActivity;
import com.example.basicui2.R;
import com.example.basicui2.anadirAdministrador;
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.maquina_item, parent, false);
            }

            TextView code = convertView.findViewById(R.id.tv_codigo);
            TextView location = convertView.findViewById(R.id.tv_ubicacion_maquina);
            Button delete = convertView.findViewById(R.id.btn_option_delete);
            Button deviceInfo= convertView.findViewById(R.id.btn_get_device_info);

            delete.setOnClickListener(v -> {
                MainActivity.databaseReference.child("Maquina").child(dev.getCodigo()).removeValue();
                //MaquinaController.eliminarMaquina(dev.getCodigo());
                notifyDataSetChanged();
            });

            deviceInfo.setOnClickListener(v -> {
                Intent i = new Intent(getContext(), DeviceInfo.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("info",dev.getCodigo());
                getContext().startActivity(i);
            });

            String type= dev.getCodigo();
            String qty= dev.getUbicacion();

            code.setText(type);
            location.setText(qty);

            return convertView;
        }
}
