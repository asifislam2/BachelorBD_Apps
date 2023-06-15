package com.bachelor.bachelorbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registation extends AppCompatActivity {

    HashMap<String, String> hashMap = new HashMap<>();
    TextInputEditText ed_name,ed_phone,ed_email,ed_pass,ed_con_pass;
    AppCompatButton SIGNUP_BUTTON;
    TextView sign_in_button;
    FirebaseAuth firebaseAuth;

    LinearLayout mother_lay;
    ProgressBar prograssone;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);


        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);
        ed_email = findViewById(R.id.ed_email);
        ed_pass = findViewById(R.id.ed_pass);
        ed_con_pass = findViewById(R.id.ed_con_pass);
        SIGNUP_BUTTON = findViewById(R.id.SIGNUP_BUTTON);
        sign_in_button = findViewById(R.id.sign_in_button);

        prograssone = findViewById(R.id.prograssone);
        mother_lay = findViewById(R.id.mother_lay);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth = FirebaseAuth.getInstance();



        SIGNUP_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prograssone.setVisibility(View.VISIBLE);

                String NAME = ed_name.getText().toString().trim();
                String PHONE = ed_phone.getText().toString().trim();
                String EMAIL = ed_email.getText().toString().trim();
                String PASS = ed_pass.getText().toString().trim();
                String CON_PASS = ed_con_pass.getText().toString().trim();

                if (NAME.equals("")){
                    Toast.makeText(Registation.this, "empty Name Filed", Toast.LENGTH_SHORT).show();
                } else if (PHONE.equals("")) {
                    Toast.makeText(Registation.this, "empty Phone Filed", Toast.LENGTH_SHORT).show();
                }else if (EMAIL.equals("")){
                    Toast.makeText(Registation.this, "empty Email Filed", Toast.LENGTH_SHORT).show();
                } else if (PASS.equals("")) {
                    Toast.makeText(Registation.this, "empty Pass Filed", Toast.LENGTH_SHORT).show();
                } else if (CON_PASS.equals("")) {
                    Toast.makeText(Registation.this, "empty con pass Filed", Toast.LENGTH_SHORT).show();
                }else {

                    mother_lay.setVisibility(View.GONE);

                    firebaseAuth.createUserWithEmailAndPassword(EMAIL, PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                prograssone.setVisibility(View.GONE);

                                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                String USER = firebaseUser.getUid();

                                hashMap = new HashMap<>();
                                hashMap.put("name", NAME);
                                hashMap.put("phone", PHONE);
                                hashMap.put("email", EMAIL);
                                hashMap.put("pass", PASS);
                                hashMap.put("con_pass", CON_PASS);
                                hashMap.put("userID", USER);
                                databaseReference.child(USER).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isComplete()){
                                            Intent intent = new Intent(Registation.this, Dashbord.class);
                                            startActivity(intent);
                                            finish();
                                        }else {
                                            Intent intent = new Intent(Registation.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });


                            }else {
                                Intent intent = new Intent(Registation.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {



                        }
                    });
                }

            }
        });


    }
}