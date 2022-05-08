package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class customerRegistration extends AppCompatActivity {

    //Register Customer section
    EditText cusName, cusEmail, cusPhone, cusAddress , cusPassword, cusConfirmPassword;
    Button registerCusButton;
    Customer cusObj;
    DatabaseReference dbReg;
    FirebaseAuth cusAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        //Register Customer section
        cusName = findViewById(R.id.cusRegName);
        cusEmail = findViewById(R.id.cusRegEmail);
        cusAddress = findViewById(R.id.cusRegAddress);
        cusPhone = findViewById(R.id.cusRegPhone);
        cusPassword = findViewById(R.id.cusRegPassword);
        cusConfirmPassword = findViewById(R.id.cusRegConfirmPassword);
        registerCusButton = findViewById(R.id.regRegisterButton);
        cusObj = new Customer();

        cusAuth = FirebaseAuth.getInstance();

        TextView loginNav = findViewById(R.id.regSignInButton);
        loginNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Works", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(customerRegistration.this, customer_login.class));
            }
        });
    }


    public void ClearControls(){
        cusName.setText("");
        cusEmail.setText("");
        cusAddress.setText("");
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
            }else if(TextUtils.isEmpty(cusAddress.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter your Address", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(cusPassword.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
            }else{
                String email = cusEmail.getText().toString().trim();
                String password = cusPassword.getText().toString().trim();

                cusAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            cusObj.setName(cusName.getText().toString().trim());
                            cusObj.setAddress(cusAddress.getText().toString().trim());
                            cusObj.setPhone(cusPhone.getText().toString().trim());
                            dbReg.child(cusAuth.getUid()).setValue(cusObj);
                            Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                            ClearControls();
                            startActivity(new Intent(customerRegistration.this, customer_login.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "invalid Number format", Toast.LENGTH_SHORT).show();
        };
    }




}