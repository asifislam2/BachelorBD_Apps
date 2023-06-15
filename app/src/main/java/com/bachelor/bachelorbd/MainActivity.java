package com.bachelor.bachelorbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView sign_up;

    AppCompatButton login_button;
    FirebaseAuth firebaseAuth;

    TextInputEditText ed_email_log,ed_pass_log;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_up = findViewById(R.id.sign_up);
        login_button = findViewById(R.id.login_button);
        ed_email_log = findViewById(R.id.ed_email_log);
        ed_pass_log = findViewById(R.id.ed_pass_log);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Registation.class);
                startActivity(intent);


            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String LOG_EMAIL = ed_email_log.getText().toString().trim();
                String LOG_PASS = ed_pass_log.getText().toString().trim();

                if (LOG_EMAIL.equals("")){
                    Toast.makeText(MainActivity.this, "Empty Email Fields", Toast.LENGTH_SHORT).show();
                } else if (LOG_PASS.equals("")) {
                    Toast.makeText(MainActivity.this, "Empty Pass Fields", Toast.LENGTH_SHORT).show();
                }else {

                    if (firebaseAuth !=null){
                        Intent intent = new Intent(MainActivity.this, Dashbord.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(MainActivity.this, Registation.class);
                        startActivity(intent);
                        finish();
                    }

                }


            }
        });


    }
}