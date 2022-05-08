package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class salonProfile extends AppCompatActivity {

    TextView salOName, salPhone,  salName, salLocation, salDescription, sDay, sTime;
    Button btn_edit;
    DatabaseReference dbRef;
    FirebaseAuth salAuth;

    public void editProfile(View view){
        startActivity(new Intent(getApplicationContext(), editProfile.class));
        Toast.makeText(getApplicationContext(), "Edit Data!", Toast.LENGTH_SHORT).show();
    }

    public void ClearControls(){
        salOName.setText(" ");
        salPhone.setText(" ");
        salName.setText(" ");
        salLocation.setText(" ");
        salDescription.setText(" ");
        sDay.setText(" ");
        sTime.setText(" ");
    }

    public void deleteAcc(View view){
        salAuth = FirebaseAuth.getInstance();
        user = 
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Salon");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(salAuth.getUid())){
                    dbRef = FirebaseDatabase.getInstance().getReference().child("Salon").child(salAuth.getUid());
                    dbRef.removeValue();
                    ClearControls();

                    startActivity(new Intent(getApplicationContext(), customer_login.class));
                    Toast.makeText(getApplicationContext(), "Data deleted Successfully", Toast.LENGTH_SHORT).show();


                }else
                    Toast.makeText(getApplicationContext(), "No Source To Display", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_profile);

        salOName = findViewById(R.id.textView17);
        salPhone = findViewById(R.id.textView20);


        salName = findViewById(R.id.textView16);
        salLocation = findViewById(R.id.textView19);
        salDescription = findViewById(R.id.textView18);
        sDay = findViewById(R.id.textView21);
        sTime = findViewById(R.id.textView2);

        salAuth = FirebaseAuth.getInstance();


        dbRef = FirebaseDatabase.getInstance().getReference().child("Salon").child(salAuth.getUid());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                if(dataSnapshot.hasChildren()){

                    salOName.setText(dataSnapshot.child("nameOfOwner").getValue().toString());
                    salPhone.setText(dataSnapshot.child("phone").getValue().toString());
                    salName.setText(dataSnapshot.child("nameOfSalon").getValue().toString());
                    salLocation.setText(dataSnapshot.child("location").getValue().toString());
                    salDescription.setText(dataSnapshot.child("description").getValue().toString());
                    sDay.setText(dataSnapshot.child("day").getValue().toString());
                    sTime.setText(dataSnapshot.child("time").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(), "No Source To Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }
        });



    }


}