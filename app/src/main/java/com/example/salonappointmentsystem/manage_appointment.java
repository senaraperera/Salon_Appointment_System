package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    DatabaseReference db;
    FirebaseAuth cusAuth;
    String ID;
    Appointment app;

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

        db = FirebaseDatabase.getInstance().getReference().child("Appointment").child(ID);
        readDB = FirebaseDatabase.getInstance().getReference("Appointment").child(ID);

        readDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    app = snapshot.getValue(Appointment.class);
                    price.setText(snapshot.child("amount").getValue().toString());
                    style.setText(snapshot.child("serviceID").getValue().toString());
                    date.setText(snapshot.child("appDate").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(), "You do not have appointments",
                            Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public void update(View view){
        Toast.makeText(getApplicationContext(), ID,Toast.LENGTH_SHORT).show();
        app.setAppDate(date.getText().toString().trim());
        db.setValue(app);
    }

    public void deleteApp(View view){
        DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("Appointment");
        delref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    db.removeValue();
                    startActivity(new Intent(getApplicationContext(), customer_login.class));
                    Toast.makeText(getApplicationContext(), "Appointment deleted successfully",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "No source to display",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}