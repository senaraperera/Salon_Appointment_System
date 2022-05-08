package com.example.salonappointmentsystem;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class salon_add_services extends AppCompatActivity {

    EditText servName,servPrice,servDuration;
    Button btn_addServ;
    DatabaseReference dbRef;
    servicesTable services;
    String ID = "salon";
    private ImageView serviceView;
    ActivityResultLauncher<String> mGetContent;
    Uri imageID;
    String imageUri;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_add_services);
        servName= findViewById(R.id.servName);
        servPrice = findViewById(R.id.servPrice);
        servDuration=findViewById(R.id.servDuration);
        btn_addServ = findViewById(R.id.buttonAddServ);
        serviceView = findViewById(R.id.serviceView);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        services = new servicesTable();

       mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
           @Override
           public void onActivityResult(Uri result) {
               serviceView.setImageURI(result);
               imageID = result;
               uploadPicture();


           }

       });

       serviceView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mGetContent.launch("image/*");
           }
       });





    }

    private void uploadPicture() {

        final String randomKey = UUID.randomUUID().toString();
        StorageReference serviceImage = storageReference.child("images/").child(randomKey);

        serviceImage.putFile(imageID)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                              Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                              serviceImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                  @Override
                                  public void onSuccess(Uri uri) {
                                      imageUri = uri.toString();
                                  }
                              });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                           Toast.makeText(getApplicationContext(),"Failed To Upload",Toast.LENGTH_SHORT).show();
                    }
                });

    }


    private void clearControls(){
        servName.setText("");
        servPrice.setText("");
        servDuration.setText("");
        serviceView.setImageURI(null);
    }




    public void addServices(View view) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Services");

        try {
            if(TextUtils.isEmpty(servName.getText().toString()))
                Toast.makeText(getBaseContext(),"Please Enter Name",Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(servPrice.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please Enter Price",Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(servDuration.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please Enter Duration",Toast.LENGTH_SHORT).show();
            else {
                services.setService_ID();
                services.setServiceName(servName.getText().toString().trim());
                services.setServicePrice(servPrice.getText().toString().trim());
                services.setDuration(servDuration.getText().toString().trim());
                services.setImage(imageUri);


                //insert to the database
               dbRef.child(ID).push().setValue(services);



                Toast.makeText(getApplicationContext(),"Data Inserted Successfully",Toast.LENGTH_SHORT).show();


                clearControls();
            }
        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Invalid format",Toast.LENGTH_SHORT).show();
        }
    }

}