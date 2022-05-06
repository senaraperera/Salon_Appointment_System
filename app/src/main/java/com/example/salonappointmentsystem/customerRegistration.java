package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class customerRegistration extends AppCompatActivity {

    //Register Customer section
    EditText cusName, cusEmail, cusPhone, cusPassword;
    Button registerCusButton;
    Customer cusObj;
    DatabaseReference dbReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        //Register Customer section
        cusName = findViewById(R.id.regName);
        cusEmail = findViewById(R.id.regEmail);
        cusPhone = findViewById(R.id.regPhone);
        cusPassword = findViewById(R.id.regPassword);
        registerCusButton = findViewById(R.id.regRegisterButton);
        cusObj = new Customer();

    }

    public void ClearControls(){
        cusName.setText("");
        cusEmail.setText("");
        cusPhone.setText("");
        cusPassword.setText("");
    }
}