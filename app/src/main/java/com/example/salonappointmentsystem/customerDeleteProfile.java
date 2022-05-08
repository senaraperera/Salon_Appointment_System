package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class customerDeleteProfile extends AppCompatActivity {

    FirebaseAuth cusAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_delete_profile);

        cusAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void deleteUser(View view){
        user.delete();

        startActivity(new Intent(getApplicationContext(), manage_appointment.class));
        Toast.makeText(getApplicationContext(), "Show Appointments!", Toast.LENGTH_SHORT).show();
    }
}


