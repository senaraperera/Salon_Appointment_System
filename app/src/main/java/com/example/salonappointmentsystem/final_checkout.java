package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class final_checkout extends AppCompatActivity {

    EditText date;
    TextView price, salonId, serviceId;
    Button checkout;
    Appointment appointObj;
    DatabaseReference fireDB;
    Appointment appObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_checkout);

        date = findViewById(R.id.date);
        price = findViewById(R.id.price);
        salonId = findViewById(R.id.chksalonName);
        serviceId = findViewById(R.id.chkServiceName);
        checkout = findViewById(R.id.checkoutBtn2);

        appObj = new Appointment();
    }

    //this will clear the form once u press the checkoutbtn
    public void clearControls(){
        date.setText("");
        price.setText("");
        salonId.setText("");
        serviceId.setText("");
    }

    public void Checkout(View view){
        fireDB = FirebaseDatabase.getInstance().getReference().child("Appointment");

        try{
            if(TextUtils.isEmpty(date.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter a date", Toast.LENGTH_SHORT).show();
            }else{
//                appointObj.setAppDate(date.getText().toString().trim());
                appointObj.setCusID("customerId");
                appointObj.setSalonID("salonId.getText().toString().trim()");
                appointObj.setServiceID("serviceId.getText().toString().trim()");
                appointObj.setAmount("price.getText().toString().trim()");

                fireDB.push().setValue(appObj); //insert a appointment to db

                Toast.makeText(getApplicationContext(), "Appointment created", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Cannot create appointment", Toast.LENGTH_SHORT).show();
        }
    }
}