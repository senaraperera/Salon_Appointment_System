package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class final_checkout extends AppCompatActivity {

    EditText date;
    TextView price, salonId, serviceId, salName;
    Button checkout;
    private String salId;
    DatabaseReference fireDB;
    Appointment appObj;
    FirebaseAuth cusAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_checkout);

        salName = findViewById(R.id.chksalonName);
        date = findViewById(R.id.date);
        price = findViewById(R.id.price);
        salonId = findViewById(R.id.chksalonName);
        serviceId = findViewById(R.id.chkServiceName);
        checkout = findViewById(R.id.checkoutBtn2);
        appObj = new Appointment();
        cusAuth = FirebaseAuth.getInstance();

        //getsalonid and name from intent
        Intent intent = getIntent();
        String text = intent.getStringExtra(show_saloon_list.EXTRA);
        salId = intent.getStringExtra(salonAdapter.EXTRAID);
        salName.setText(text);
        //check if customer has logged in
        if(cusAuth.getCurrentUser() == null){
            Toast.makeText(getApplicationContext(), "Login to your account first",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //this will clear the form once u press the checkoutbtn
    public void clearControls(){
        date.setText("");
        price.setText("");
        salonId.setText("");
        serviceId.setText("");
        Intent intent = new Intent( getApplicationContext(), customerProfilePage.class );
        startActivity(intent);
    }

    public void Checkout(View view){
        fireDB = FirebaseDatabase.getInstance().getReference().child("Appointment");

        try{
            Log.i("ddddd",cusAuth.getCurrentUser().getUid());
            if(TextUtils.isEmpty(date.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter a date",
                        Toast.LENGTH_SHORT).show();
            }else{
                appObj.setAppDate(date.getText().toString().trim());
                appObj.setSalonID(salId);
                appObj.setServiceID(serviceId.getText().toString().trim());
                appObj.setAmount(price.getText().toString().trim());

                fireDB.child(cusAuth.getUid()).setValue(appObj); //insert a appointment to db

                Toast.makeText(getApplicationContext(), "Appointment created",
                        Toast.LENGTH_SHORT).show();
                clearControls();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Cannot create appointment",
                    Toast.LENGTH_SHORT).show();
        }
    }
}