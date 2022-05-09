package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customerDeleteProfile extends AppCompatActivity {

    FirebaseAuth cusAuth;
    FirebaseUser user;
    DatabaseReference dbReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_delete_profile);

        cusAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        dbReg = FirebaseDatabase.getInstance().getReference().child("Customer");
    }

    public void deleteUser(View view){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(cusAuth.getUid())){

                    dbReg = FirebaseDatabase.getInstance().getReference().child("Customer").child(cusAuth.getUid());
                    dbReg.removeValue();


                    startActivity(new Intent(getApplicationContext(), customer_login.class));
                    Toast.makeText(getApplicationContext(), "Data deleted Successfully", Toast.LENGTH_SHORT).show();


                }else
                    Toast.makeText(getApplicationContext(), "No data to be displayed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        user.delete();
        Toast.makeText(getApplicationContext(), "Deleting user", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), customer_login.class));
    }
}


