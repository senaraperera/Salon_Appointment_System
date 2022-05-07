package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class salonRegistration_page2 extends AppCompatActivity {

    //Register salon details
    EditText salName, salLocation, salDescription, sDay, sTime;
    Button btn_submit;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_registration_page2);

        salName = findViewById(R.id.editTextTextPersonName9);
        salLocation = findViewById(R.id.editTextTextPersonName16);
        salDescription = findViewById(R.id.editTextTextPersonName12);
        sDay = findViewById(R.id.editTextTextPersonName17);
        sTime = findViewById(R.id.editTextTextPersonName18);


        salon salOb = getIntent().getParcelableExtra("salon");

    }




}