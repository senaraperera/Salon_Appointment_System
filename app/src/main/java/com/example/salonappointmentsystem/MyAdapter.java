package com.example.salonappointmentsystem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<servicesTable> list;

    public MyAdapter(Context context, ArrayList<servicesTable> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.services,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
           servicesTable services = list.get(position);
           holder.serviceName.setText(services.getServiceName());
           holder.servicePrice.setText(services.getServicePrice());
           holder.serviceDuration.setText(services.getDuration());
           holder.serviceID.setText(services.getService_ID());



           holder.btnEditService.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   final DialogPlus dialogPlus= DialogPlus.newDialog(holder.serviceID.getContext())
                           .setContentHolder(new ViewHolder(R.layout.service_update_popup))
                           .setExpanded(true,1200)
                           .create();

//                   dialogPlus.show();

                   String key = services.getUnique();

                   View view2 = dialogPlus.getHolderView();
                   EditText servName = view2.findViewById(R.id.txtName);
                   EditText servPrice = view2.findViewById(R.id.txtPrice);
                   EditText servDuration = view2.findViewById(R.id.txtDuration);


                   Button btnUpdateService = view2.findViewById(R.id.serviceUpdate);


                   servName.setText(services.getServiceName());
                   servPrice.setText(services.getServicePrice());
                   servDuration.setText(services.getDuration());

                   dialogPlus.show();

                   btnUpdateService.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           list.clear();
                           Map<String,Object> map = new HashMap<>();
                           map.put("serviceName",servName.getText().toString());
                           map.put("servicePrice",servPrice.getText().toString());
                           map.put("duration",servDuration.getText().toString());






                           FirebaseDatabase.getInstance().getReference().child("Services").child(services.getUnique()).updateChildren(map)
                                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void unused) {
                                   Toast.makeText(holder.serviceID.getContext(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
                                   dialogPlus.dismiss();

                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(holder.serviceName.getContext(),"Errore while updating",Toast.LENGTH_SHORT).show();
                                   dialogPlus.dismiss();
                               }
                           });


                       }
                   });




               }
           });

        holder.btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder builder = new AlertDialog.Builder(holder.serviceID.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't recovered");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.clear();
                        FirebaseDatabase.getInstance().getReference().child("Services").child(services.getUnique()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.serviceName.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName,servicePrice,serviceDuration,serviceID;

        Button btnEditService,btnDeleteService;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.serviceName);
            servicePrice = itemView.findViewById(R.id.servicePrice);
            serviceDuration = itemView.findViewById(R.id.serviceDuration);
            serviceID = itemView.findViewById(R.id.serviceID);

            btnEditService = (Button)itemView.findViewById(R.id.btnEditService);
            btnDeleteService = (Button)itemView.findViewById(R.id.btnDeleteService);

        }
    }
}
