package com.example.salonappointmentsystem;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class appointmentAdapter extends RecyclerView.Adapter<appointmentAdapter.ViewHolder> {
    Context context;
    ArrayList<Appointment> list;

    public appointmentAdapter(Context context, ArrayList<Appointment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.appoint_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
             Appointment appointment = list.get(position);
             holder.name.setText(appointment.getServiceID());
             holder.price.setText(appointment.getAmount());
             holder.date.setText(appointment.getAppDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , price ,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.appointService);
            price = itemView.findViewById(R.id.appointPrice);
            date = itemView.findViewById(R.id.appointDate);
        }
    }
}
