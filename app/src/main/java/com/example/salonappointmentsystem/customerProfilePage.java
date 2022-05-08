package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customerProfilePage extends AppCompatActivity {

    Customer cusObj;
    FirebaseAuth cusAuth;
    DatabaseReference db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile_page);

//        if (cusAuth.getUid() == null){
//            startActivity(new Intent(getApplicationContext(), customer_login.class));
//        }


        db = FirebaseDatabase.getInstance().getReference();
        cusAuth = FirebaseAuth.getInstance();
        db.child("Customer")
//                .child(cusAuth.getUid())
                .child("RXz0hKvMKeOajxqKCJMBLeD3Sqv1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            //task.getResult().get
                            Customer customer = task.getResult().getValue(Customer.class);
                            cusObj = customer;
                            sayHi(customer);
                        }else{
                            task.getException().printStackTrace();
                        }
                    }
                });
    }

    public void sayHi(Customer customer) {
        TextView name = (TextView) findViewById(R.id.cpHelloName);
        TextView app = (TextView) findViewById(R.id.cpHelloName);
        TextView pen = (TextView) findViewById(R.id.cpHelloName);

        name.setText("Hi, " + customer.getName());
//        app.setText(customer.getAddress());
//        pen.setText(customer.getPhone());
    }

    public void editProfile(View view){
        startActivity(new Intent(getApplicationContext(), customerEditDetails.class));
        Toast.makeText(getApplicationContext(), "Edit Data!", Toast.LENGTH_SHORT).show();
    }

    public void deleteProfile(View view){
        startActivity(new Intent(getApplicationContext(), show_saloon_list.class));
        Toast.makeText(getApplicationContext(), "Yay! Data Updated!", Toast.LENGTH_SHORT).show();
    }

    public void showAppointments(View view){
        startActivity(new Intent(getApplicationContext(), manage_appointment.class));
        Toast.makeText(getApplicationContext(), "Show Appointments!", Toast.LENGTH_SHORT).show();
    }

    public void showPens(View view){
        startActivity(new Intent(getApplicationContext(), CustomerPenalty.class));
        Toast.makeText(getApplicationContext(), "Yay! Data Updated!", Toast.LENGTH_SHORT).show();
    }

    public void logOut(View view){

        Toast.makeText(getApplicationContext(), "Logging out...", Toast.LENGTH_SHORT).show();
        cusAuth.signOut();
        startActivity(new Intent(getApplicationContext(), customer_login.class));
    }
}