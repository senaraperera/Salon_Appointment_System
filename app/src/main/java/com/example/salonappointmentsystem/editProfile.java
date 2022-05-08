package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editProfile extends AppCompatActivity {

    public void redirectToProfile(View view){
        startActivity(new Intent(getApplicationContext(), salonProfile.class));
        Toast.makeText(getApplicationContext(), "Services", Toast.LENGTH_SHORT).show();
    }

    DatabaseReference dbRef;
    salon salOb ;
    FirebaseAuth salAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        dbRef = FirebaseDatabase.getInstance().getReference();
         salAuth = FirebaseAuth.getInstance();
        salAuth.getUid();

        dbRef.child("Salon").child(salAuth.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            //task.getResult().get
                            salon salonn = task.getResult().getValue(salon.class);
                            salOb = salonn;
                            setData(salonn);
                        }else{
                            task.getException().printStackTrace();
                        }
                    }
                });
        Toast.makeText(getApplicationContext(), "Works", Toast.LENGTH_SHORT).show();
    }

    EditText editName,editDescription,editLocation,editDay, editTime;

    public void setData(salon salonn) {
        editName = (EditText) findViewById(R.id.editTextTextPersonName);
        editDescription = (EditText) findViewById(R.id.editTextTextPersonName4);
        editLocation = (EditText) findViewById(R.id.editTextTextPersonName5);
        editDay = (EditText) findViewById(R.id.editTextTextPersonName6);
        editTime = (EditText) findViewById(R.id.editTextTextPersonName7);

        editName.setText(salonn.getNameOfSalon());
        editDescription.setText(salonn.getDescription());
        editLocation.setText(salonn.getLocation());
        editDay.setText(salonn.getDay());
        editTime.setText(salonn.getTime());
    }
    public void saveData(View view){
        salOb.setNameOfSalon(editName.getText().toString().trim());
        salOb.setDescription(editDescription.getText().toString().trim());
        salOb.setLocation(editLocation.getText().toString().trim());
        salOb.setDay(editDay.getText().toString().trim());
        salOb.setTime(editTime.getText().toString().trim());

        dbRef.child("Salon").child(salAuth.getUid()).setValue(salOb).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Yay! Data Updated!", Toast.LENGTH_SHORT).show();
                }else{
                    task.getException().printStackTrace();
                }
            }
        });
    }
}