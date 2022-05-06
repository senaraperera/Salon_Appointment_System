package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customerRegistration extends AppCompatActivity {

    //Register Customer section
    EditText cusName, cusEmail, cusPhone, cusPassword, cusConfirmPassword;
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
        cusConfirmPassword = findViewById(R.id.regConfirmPassword);
        registerCusButton = findViewById(R.id.regRegisterButton);
        cusObj = new Customer();

    }

    public void ClearControls(){
        cusName.setText("");
        cusEmail.setText("");
        cusPhone.setText("");
        cusPassword.setText("");
        cusConfirmPassword.setText("");
    }

    public void CreateData(View view){
        dbReg = FirebaseDatabase.getInstance().getReference().child("Customer");

        try{
            if(TextUtils.isEmpty(cusName.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(cusEmail.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your Email", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(cusPhone.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your Phone", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(cusPassword.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
            }else{
                cusObj.setName(cusName.getText().toString().trim());
                cusObj.setEmail(cusEmail.getText().toString().trim());
                cusObj.setPhone(Integer.parseInt(cusPhone.getText().toString().trim()));
                cusObj.setPassword(cusPassword.getText().toString().trim());

                dbReg.push().setValue(cusObj);

                Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                ClearControls();
                //This is to send to the login page
                startActivity(new Intent(getApplicationContext(), customer_login.class));
            }
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "invalid Number format", Toast.LENGTH_SHORT).show();
        }
    }

















}