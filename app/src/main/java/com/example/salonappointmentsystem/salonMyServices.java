package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class salonMyServices extends AppCompatActivity {
      public static final String SERVICE_ID  = "com.example.salonappointmentsystem.SERVICE_ID";
      RecyclerView recyclerView;
      ArrayList<servicesTable> list;
      DatabaseReference databaseReference;
      MyAdapter adapter;
      servicesTable services;
      FloatingActionButton floatingActionButton;
       CardView cardview ;
       FirebaseAuth salID;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_my_services);
        salID = FirebaseAuth.getInstance();

          recyclerView = findViewById(R.id.recycleView);
          databaseReference = FirebaseDatabase.getInstance().getReference("Services").child(salID.getUid());
          list = new ArrayList<>();
          recyclerView.setLayoutManager(new LinearLayoutManager(this));
          adapter= new MyAdapter(this,list);
          recyclerView.setAdapter(adapter);


           floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

          floatingActionButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  startActivity(new Intent(getApplicationContext(),salon_add_services.class));



              }



          });









        databaseReference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {

                  for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                      servicesTable services = dataSnapshot.getValue(servicesTable.class);
                      services.setUnique(dataSnapshot.getKey());
                      list.add(services);

                  }

                  adapter.notifyDataSetChanged();
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });


    }





}