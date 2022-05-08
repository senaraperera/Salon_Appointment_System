package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class show_saloon_list extends AppCompatActivity {

    public static final String EXTRA = "com.example.salonappointmentsystem.EXTRA_TEXT";

    RecyclerView rv;
    ArrayList<salon> list;
    DatabaseReference fireDB;
    salonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saloon_list);

        rv = findViewById(R.id.salonRecyclerView);
        fireDB = FirebaseDatabase.getInstance().getReference("Salon");
        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new salonAdapter(this, list);
        rv.setAdapter(adapter);

        fireDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    salon sal = dataSnapshot.getValue(salon.class);
                    list.add(sal);
                    sal.setId(dataSnapshot.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}