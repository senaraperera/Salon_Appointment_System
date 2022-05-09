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

    public void ReturnToLogin(View view){
        startActivity(new Intent(getApplicationContext(), salonLogin.class));
        Toast.makeText(getApplicationContext(), "Services", Toast.LENGTH_SHORT).show();
    }


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


        /* VALIDATIONS */

        

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

        if(!validateSalonOwnerName() | !validateEmail() | !validatePassword() | !validatePhone()   | !validateSalonName() | !validateLocation() | !validateDescription() | !validateDay() | !validateTime() ){
            return;
        }
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
    //VALIDATIONS FOR THE INPUT FIELDS=============================================================================
    public boolean validateSalonOwnerName(){
        String val = salOName.getText().toString().trim();
        if(val.isEmpty()){
            salOName.setError("You cannot leave the name empty");
            return false;
        }else{
            salOName.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = salEmail.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            salEmail.setError("You cannot leave the email empty");
            return false;
        } else if (!val.matches(checkEmail)){
            salEmail.setError("Invalid Email");
            return false;
        }else{
            salEmail.setError(null);
//            cusName.setErrorEnabled
            return true;
        }
    }

    private boolean validatePhone() {
        String val = salPhone.getText().toString().trim();
        String checkphone = "[0-9]+";
        if (val.isEmpty()) {
            salPhone.setError("You cannot leave the phone empty");
            return false;
        } else if (!val.matches(checkphone)){
            salPhone.setError("Invalid phone. Enter numbers only");
            return false;
        }else{
            salPhone.setError(null);
//            cusName.setErrorEnabled
            return true;
        }
    }

    private boolean validatePassword() {
        String val = salPassword.getText().toString().trim();
        String val2 = salConfirmPassword.getText().toString().trim();
        String checkPassword = "^" + ".{8,}+";
        if (val.isEmpty()) {
            salPassword.setError("You cannot leave the password empty");
            return false;
        } else if(val == val2){
            salPassword.setError("Password does not match");
            return false;
            }else if(!val.matches(checkPassword)){
            salPassword.setError("Must contain 8 minimum characters");
            return false;
        }else{
            salPassword.setError(null);
//            cusName.setErrorEnabled

            return true;
        }
    }

    public boolean validateSalonName(){
        String val = salName.getText().toString().trim();
        if(val.isEmpty()){
            salName.setError("You cannot leave the Salon name empty");
            return false;
        }else{
            salName.setError(null);
            return true;
        }
    }
    public boolean validateLocation(){
        String val = salLocation.getText().toString().trim();
        if(val.isEmpty()){
            salLocation.setError("You cannot leave the location empty");
            return false;
        }else{
            salLocation.setError(null);
            return true;
        }
    }
    public boolean validateDescription(){
        String val = salDescription.getText().toString().trim();
        if(val.isEmpty()){
            salDescription.setError("You cannot leave the description empty");
            return false;
        }else{
            salDescription.setError(null);
            return true;
        }
    }
    public boolean validateDay(){
        String val = sDay.getText().toString().trim();
        if(val.isEmpty()){
            sDay.setError("You cannot leave the available days empty");
            return false;
        }else{
            sDay.setError(null);
            return true;
        }
    }
    public boolean validateTime(){
        String val = sTime.getText().toString().trim();
        if(val.isEmpty()){
            sTime.setError("You cannot leave the available times empty");
            return false;
        }else{
            sTime.setError(null);
            return true;
        }
    }




}