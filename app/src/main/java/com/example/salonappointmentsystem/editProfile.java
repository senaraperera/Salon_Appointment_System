//package com.example.salonappointmentsystem;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class editProfile extends AppCompatActivity {
//
//    TextView salOName, salPhone,  salName, salLocation, salDescription, sDay, sTime;
//    Button btn_edit;
//    DatabaseReference dbRef;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_profile);
//
//        salOName = findViewById(R.id.textView31);
//        salPhone = findViewById(R.id.textView34);
//
//
//        salName = findViewById(R.id.textView30);
//        salLocation = findViewById(R.id.textView33);
//        salDescription = findViewById(R.id.textView32);
//        sDay = findViewById(R.id.textView35);
//        sTime = findViewById(R.id.textView4);
//
//        salon salOb = new salon();
//
//
//
//        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Salon");
//        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.hasChild("-N1WrSCghPD0niw-i-Eb")) {
//                    try {
//                        salOb.setNameOfOwner(salOName.getText().toString().trim());
//                        salOb.setPhone(Integer.parseInt(salPhone.getText().toString().trim()));
//                        salOb.setNameOfSalon(salName.getText().toString().trim());
//                        salOb.setLocation(salLocation.getText().toString().trim());
//                        salOb.setDescription(salDescription.getText().toString().trim());
//                        salOb.setDay(sDay.getText().toString().trim());
//                        salOb.setTime(sTime.getText().toString().trim());
//
//
//                        dbRef = FirebaseDatabase.getInstance().getReference().child("Salon").child("-N1WrSCghPD0niw-i-Eb");
//                        dbRef.setValue(salOb);
////                        clearControls();
//
//                        Toast.makeText(getApplicationContext(), "Data updated Succesfully", Toast.LENGTH_SHORT).show();
//                    } catch (NumberFormatException e) {
//                        Toast.makeText(getApplicationContext(), "Invalid contact Number", Toast.LENGTH_SHORT).show();
//
//                    }
//                } else
//                    Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//            private void clearControls() {
//                salOName.setText(" ");
//                salPhone.setText(" ");
//                salName.setText(" ");
//                salLocation.setText(" ");
//                salDescription.setText(" ");
//                sDay.setText(" ");
//                sTime.setText(" ");
//            }
//        });
//    }
//
//
//}


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
//import com.google.firebase.storage.FirebaseStorage;

public class editProfile extends AppCompatActivity {

    EditText salOName, salPhone,  salName, salLocation, salDescription, sDay, sTime;
    Button btn_edit;
    DatabaseReference dbRef;

    salon salOb ;


    FirebaseAuth salAuth;
//    DatabaseReference db;


    public void redirectToProfile(View view){
        startActivity(new Intent(getApplicationContext(), salonProfile.class));
        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        dbRef = FirebaseDatabase.getInstance().getReference();
         salAuth = FirebaseAuth.getInstance();
        salAuth.getUid();

        dbRef.child("Salon")
                .child(salAuth.getUid())
//                .child("-N1Tle5dLqaWin0kzr7O")
                .get()
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

    EditText editName;
    EditText editDescription;
    EditText editLocation;
    EditText editDay;
    EditText editTime;

//    EditText editAddress;


    public void setData(salon salonn) {

        editName = (EditText) findViewById(R.id.editTextTextPersonName);
        editDescription = (EditText) findViewById(R.id.editTextTextPersonName4);
        editLocation = (EditText) findViewById(R.id.editTextTextPersonName5);
        editDay = (EditText) findViewById(R.id.editTextTextPersonName6);
        editTime = (EditText) findViewById(R.id.editTextTextPersonName7);
//        editDescription = (EditText) findViewById(R.id.editTextTextPersonName4);

//        editAddress = (EditText) findViewById(R.id.cusRegAddress);

        editName.setText(salonn.getNameOfSalon());
////        editAddress.setText(salonn.getAddress());
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

//        cusObj.setAddress(editAddress.getText().toString());
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