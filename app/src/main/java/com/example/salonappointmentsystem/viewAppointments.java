package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class viewAppointments extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Appointment> lists;
    DatabaseReference databaseReference;
    appointmentAdapter adapt;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

           recyclerView =  findViewById(R.id.viewAppoints);
           databaseReference = FirebaseDatabase.getInstance().getReference("Appointment");
           lists= new ArrayList<>();
           recyclerView.setLayoutManager(new LinearLayoutManager(this));
           adapt = new appointmentAdapter(this ,lists);
           recyclerView.setAdapter(adapt);

           databaseReference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                       Appointment appointment =dataSnapshot.getValue(Appointment.class);
                       lists.add(appointment);
                   }
                   adapt.notifyDataSetChanged();
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

    }


}