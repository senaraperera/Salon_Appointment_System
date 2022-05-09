package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class manage_appointment extends AppCompatActivity {

    Button update, delete;
    TextView price, style, lblDate;
    EditText eDate;
    DatabaseReference readDB;
    DatabaseReference db;
    FirebaseAuth cusAuth;
    String ID;
    Appointment app;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_appointment);

        cusAuth = FirebaseAuth.getInstance();
        ID = cusAuth.getCurrentUser().getUid();

        lblDate = findViewById(R.id.lblDate);
        eDate = findViewById(R.id.editTextDate);
        update = findViewById(R.id.updateDate);
        delete = findViewById(R.id.delAppointment);
        price = findViewById(R.id.appData);
        style = findViewById(R.id.appData1);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        lblDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        manage_appointment.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = day+"/"+month+"/"+year;
                lblDate.setText(date);
            }
        };

        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        manage_appointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        eDate.setText(date);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        db = FirebaseDatabase.getInstance().getReference().child("Appointment").child(ID);
        readDB = FirebaseDatabase.getInstance().getReference("Appointment").child(ID);

        readDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    app = snapshot.getValue(Appointment.class);
                    price.setText(snapshot.child("amount").getValue().toString());
                    style.setText(snapshot.child("serviceID").getValue().toString());
                    eDate.setText(snapshot.child("appDate").getValue().toString());
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
        app.setAppDate(eDate.getText().toString().trim());
        db.setValue(app).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Your new appointment date is updated", Toast.LENGTH_SHORT).show();
                }else{
                    task.getException().printStackTrace();
                }
            }
        });
    }

    public void deleteApp(View view){
        DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("Appointment");
        delref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    db.removeValue();
                    startActivity(new Intent(getApplicationContext(), customerProfilePage.class));
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