package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

public class show_saloon_list extends AppCompatActivity {

    DatabaseReference firedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saloon_list);
    }
}