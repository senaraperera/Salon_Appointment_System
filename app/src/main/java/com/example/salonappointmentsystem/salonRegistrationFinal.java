package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class salonRegistrationFinal extends AppCompatActivity {



    EditText salOName, salPhone,salEmail, salPassword, salConfirmPassword,salName, salLocation, salDescription, sDay, sTime;
    Button btn_submit;
    salon salOb;
    DatabaseReference dbRef;
    FirebaseAuth salAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_registration_new);



        salEmail = findViewById(R.id.editTextTextPersonName23);
        salPassword = findViewById(R.id.editTextTextPersonName15);
        salConfirmPassword = findViewById(R.id.editTextTextPersonName19);

        salOName = findViewById(R.id.editTextTextPersonName11);
        salPhone = findViewById(R.id.editTextTextPersonName14);

        salName = findViewById(R.id.editTextTextPersonName20);
        salLocation = findViewById(R.id.editTextTextPersonName21);
        salDescription = findViewById(R.id.editTextTextPersonName22);
        sDay = findViewById(R.id.editTextTextPersonName24);
        sTime = findViewById(R.id.editTextTextPersonName25);

        btn_submit = findViewById(R.id.button20);

        salOb = new salon();
        salAuth = FirebaseAuth.getInstance();

    }
    public void Cancel1(View view){
        startActivity(new Intent(getApplicationContext(), salonLogin.class));
        Toast.makeText(getApplicationContext(), "Services", Toast.LENGTH_SHORT).show();
    }
    public void ClearControls(){
        salOName.setText(" ");
        salPhone.setText(" ");
        salPassword.setText(" ");
        salConfirmPassword.setText("");
        salName.setText(" ");
        salLocation.setText(" ");
        salDescription.setText(" ");
        sDay.setText(" ");
        sTime.setText(" ");
    }

    public void CreateData(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Salon");

        try{
            if(TextUtils.isEmpty(salOName.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter Owner name", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(salPhone.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your Phone", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(salPassword.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(salConfirmPassword.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please confirm your Password", Toast.LENGTH_SHORT).show();}
            else if(TextUtils.isEmpty(salName.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter Salon Name", Toast.LENGTH_SHORT).show();}
            else if(TextUtils.isEmpty(salLocation.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter Salon Location", Toast.LENGTH_SHORT).show();}
            else if(TextUtils.isEmpty(salDescription.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter salon Description", Toast.LENGTH_SHORT).show();}
            else if(TextUtils.isEmpty(sDay.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter available Day/s", Toast.LENGTH_SHORT).show();}
            else if(TextUtils.isEmpty(sTime.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter available time/s", Toast.LENGTH_SHORT).show();}
            else {

                String email = salEmail.getText().toString().trim();
//                String email ="inuri@gmail.com";
//                String password ="12335678";
                String password = salPassword.getText().toString().trim();

                salAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            salOb.setNameOfOwner(salOName.getText().toString().trim());
                            salOb.setPhone(Integer.parseInt(salPhone.getText().toString().trim()));
                            salOb.setNameOfSalon(salName.getText().toString().trim());
                            salOb.setLocation(salLocation.getText().toString().trim());
                            salOb.setDescription(salDescription.getText().toString().trim());
                            salOb.setDay(sDay.getText().toString().trim());
                            salOb.setTime(sTime.getText().toString().trim());


                            dbRef.child(salAuth.getUid()).setValue(salOb);


                            Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                            ClearControls();
                            startActivity(new Intent(salonRegistrationFinal.this, salonLogin.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }


                });
            }







            //This is to send to the login page
            //startActivity(new Intent(getApplicationContext(), customer_login.class));

        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "invalid Number format", Toast.LENGTH_SHORT).show();
        }
    }

}