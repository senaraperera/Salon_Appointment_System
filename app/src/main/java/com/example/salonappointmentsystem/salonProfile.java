package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class salonProfile extends AppCompatActivity {

    EditText salOName, salPhone, salPassword, salConfirmPassword,salName, salLocation, salDescription, sDay, sTime;
    Button btn_edit;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_profile);
    }

    public void onClick(View view){

    }


}