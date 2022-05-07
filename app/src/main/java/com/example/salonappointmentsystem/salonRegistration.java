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


public class salonRegistration extends AppCompatActivity {

    //Register owner details
    EditText salOName, salPhone, salPassword, salConfirmPassword;
    Button btn_next;
    salon salOb;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_registration);

        salOName = findViewById(R.id.editTextTextPersonName5);
        salPhone = findViewById(R.id.editTextTextPersonName6);
        salPassword = findViewById(R.id.editTextTextPersonName7);
        salConfirmPassword = findViewById(R.id.editTextTextPersonName8);
        btn_next = findViewById(R.id.button4);

        salOb = new salon();

        Intent intent = new Intent(salonRegistration.this, salonRegistration_page2.class);
        intent.putExtra("salon", salOb);
        startActivity(intent);

    }
        public void ClearControls(){
            salOName.setText(" ");
            salPhone.setText(" ");
            salPassword.setText(" ");
            salConfirmPassword.setText("");
        }

        public void CreateData(View view){

            dbRef = FirebaseDatabase.getInstance().getReference().child("Salon");

            try{
                if(TextUtils.isEmpty(salOName.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(salPhone.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please enter your Phone", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(salPassword.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
                }else{
                    salOb.setNameOfOwner(salOName.getText().toString().trim());
                    salOb.setPhone(Integer.parseInt(salPhone.getText().toString().trim()));
                    salOb.setPassword(salPassword.getText().toString().trim());

                    //dbRef.push().setValue(salOb);

                    Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                    ClearControls();
                    //This is to send to the login page
                    //startActivity(new Intent(getApplicationContext(), customer_login.class));
                }
            }catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(), "invalid Number format", Toast.LENGTH_SHORT).show();
            }
        }

}