package com.example.salonappointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class final_checkout extends AppCompatActivity {

    EditText eDate;
    TextView price, salonId, serviceId, salName, txtDate;
    Button checkout;
    private String salId;
    DatabaseReference fireDB;
    Appointment appObj;
    FirebaseAuth cusAuth;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_checkout);

        salName = findViewById(R.id.chksalonName);
        eDate = findViewById(R.id.date);
        price = findViewById(R.id.price);
        salonId = findViewById(R.id.chksalonName);
        serviceId = findViewById(R.id.chkServiceName);
        checkout = findViewById(R.id.checkoutBtn2);
        txtDate = findViewById(R.id.dateLabel);
        appObj = new Appointment();
        cusAuth = FirebaseAuth.getInstance();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        final_checkout.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = day+"/"+month+"/"+year;
                txtDate.setText(date);
            }
        };

        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        final_checkout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        eDate.setText(date);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });
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
        eDate.setText("");
        price.setText("");
        salonId.setText("");
        serviceId.setText("");
        Intent intent = new Intent( getApplicationContext(), customerProfilePage.class );
        startActivity(intent);
    }

    public boolean checkDateEmpty(){
        String val = eDate. getText().toString().trim();
        if(val.isEmpty()){
            eDate.setError("The date field is empty");
            return false;
        }else{
            eDate.setError(null);
            return true;
        }
    }

    public void Checkout(View view){
        if(!checkDateEmpty()){
            return;
        }
        fireDB = FirebaseDatabase.getInstance().getReference().child("Appointment");

        try{
            Log.i("ddddd",cusAuth.getCurrentUser().getUid());
            if(TextUtils.isEmpty(eDate.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter a date",
                        Toast.LENGTH_SHORT).show();
            }else{
                appObj.setAppDate(eDate.getText().toString().trim());
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