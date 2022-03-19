package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class customer_checkout1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_checkout1);

        Spinner mySpinner1 = (Spinner) findViewById(R.id.category_Spinner);

        //get data
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(customer_checkout1.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //allow adapter to show the data inside spinner
        mySpinner1.setAdapter(categoryAdapter);
    }
}