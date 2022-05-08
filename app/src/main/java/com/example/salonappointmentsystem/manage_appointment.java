package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class manage_appointment extends AppCompatActivity {

    Button update, delete;
    TextView price, style;
    EditText date;
    DatabaseReference readDB;
    FirebaseAuth cusAuth;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_appointment);

        cusAuth = FirebaseAuth.getInstance();
        ID = cusAuth.getCurrentUser().getUid();

        update = findViewById(R.id.updateDate);
        delete = findViewById(R.id.delAppointment);
        price = findViewById(R.id.appData);
        style = findViewById(R.id.appData1);
        date = findViewById(R.id.editTextDate);

        readDB = FirebaseDatabase.getInstance().getReference("Appointment").child(ID);

        readDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    price.setText(snapshot.child("amount").getValue().toString());
                    style.setText(snapshot.child("serviceID").getValue().toString());
                    date.setText(snapshot.child("appDate").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(), "You do not have appointments", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}