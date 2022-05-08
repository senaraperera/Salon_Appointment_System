package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class customerEditDetails extends AppCompatActivity {

    private EditText regCusName, regCusEmail, regCusPhone, regCusAddress ,  regCusPassword, regCusConfirmPassword;
    private Button refRegisterCusButton;
    Customer cusObj;
    FirebaseAuth cusAuth;
    DatabaseReference db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_details);

        db = FirebaseDatabase.getInstance().getReference();
        cusAuth = FirebaseAuth.getInstance();
//        cusAuth.getUid();

        db.child("Customer")
                .child(cusAuth.getUid())
//                .child("TGTJ4DbGgpS8rISZI55a00cMAsE2")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            //task.getResult().get
                            Customer customer = task.getResult().getValue(Customer.class);
                            cusObj = customer;
                            setData(customer);
                        }else{
                            task.getException().printStackTrace();
                        }
                    }
                });

        Toast.makeText(getApplicationContext(), "Works", Toast.LENGTH_SHORT).show();
    }

    EditText editName;
    EditText editPhone;
    EditText editPassword;
    EditText editAddress;


    public void setData(Customer customer) {
        editName = (EditText) findViewById(R.id.cusRegName);
        editPhone = (EditText) findViewById(R.id.cusRegPhone);
        editPassword = (EditText) findViewById(R.id.cusRegPassword);
        editAddress = (EditText) findViewById(R.id.cusRegAddress);

        editName.setText(customer.getName());
        editAddress.setText(customer.getAddress());
        editPhone.setText(customer.getPhone());
    }

    public void saveData(View view){
          cusObj.setName(editName.getText().toString().trim());
          cusObj.setPhone(editPhone.getText().toString());
          cusObj.setAddress(editAddress.getText().toString());
          db.child("Customer").child(cusAuth.getUid()).setValue(cusObj).addOnCompleteListener(new OnCompleteListener<Void>() {
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