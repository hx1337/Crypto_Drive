package com.crypto.drive;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginotp extends AppCompatActivity {
    Button action;
    EditText OTP;
    String myotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loginotp);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        Toolbar toolbar=new Toolbar(this);
        toolbar.setVisibility(View.INVISIBLE);
        TextView btn = findViewById(R.id.textViewResendotp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(loginotp.this, loginotp.class));
            }
        });

        TextView btn2 = findViewById(R.id.textViewSignUp);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(loginotp.this, Registration.class));
            }
        });


        OTP = findViewById(R.id.inputotp);
        action = findViewById(R.id.btnloginverify);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Validate();

                }
            }


        });


    }

    private void Validate() {

        myotp = OTP.getText().toString();
        if (myotp.isEmpty()) {
            OTP.requestFocus();
            OTP.setError(Common.otp);
        }  else {
            verify();
        }
    }

    public void verify() {
        if (myotp.trim().equals(Common.otp)) {

            Toast.makeText(getApplicationContext(), "OTP verified", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),UserHome.class));

        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid OTP , Try again ", Toast.LENGTH_SHORT).show();
        }

    }


}