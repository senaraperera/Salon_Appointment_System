package com.example.salonappointmentsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class salonAdapter extends RecyclerView.Adapter<salonAdapter.MyViewHolder> {
    Context context;
    ArrayList<salon> list;
    public static final String EXTRA = "com.example.salonappointmentsystem.EXTRA_TEXT";
    public static final String EXTRAID = "com.example.salonappointmentsystem.EXTRA_id";


    public salonAdapter(Context context, ArrayList<salon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.selonentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        salon sal = list.get(position);
        holder.name.setText(sal.getNameOfSalon());
        holder.addr.setText(sal.getLocation());
        holder.openHrs.setText(sal.getTime());
        try{holder.telNo.setText(sal.getPhone().toString());}
        catch(Exception e){
            e.printStackTrace();
        }
        holder.setSalon(sal);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, openHrs, addr, telNo;
        private salon salon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.salName);
            addr = itemView.findViewById(R.id.salAddress);
            openHrs = itemView.findViewById(R.id.salOpenHrs);
            telNo = itemView.findViewById(R.id.saTel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TextView txt = (TextView) findViewById(R.id.salName);
                    String text = name.getText().toString();

                    Intent intent = new Intent(view.getContext(), final_checkout.class);
                    intent.putExtra(EXTRA, text);
                    intent.putExtra(EXTRAID, getSalon().getId());
                    view.getContext().startActivity(intent);
                }
            });
        }

        public com.example.salonappointmentsystem.salon getSalon() {
            return salon;
        }

        public void setSalon(com.example.salonappointmentsystem.salon salon) {
            this.salon = salon;
        }
    }
}
