package com.crypto.drive;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgot extends AppCompatActivity {
    String  myphone;
    EditText PHONE;
    Button action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        Toolbar toolbar=new Toolbar(this);
        toolbar.setVisibility(View.INVISIBLE);
        TextView btn = findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(forgot.this, Registration.class));
            }
        });

        PHONE = findViewById(R.id.forgetphno);
        action = findViewById(R.id.btnsendotp);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Validate();
            }
        });


    }

    private void Validate() {

        myphone = PHONE.getText().toString();
        if (myphone.isEmpty()) {
            PHONE.requestFocus();
            PHONE.setError("Enter phone no to reset password");
        }
        else {

            myphone = PHONE.getText().toString();
            Intent intent = new Intent(getApplicationContext(),resetotp.class);
            intent.putExtra("message",myphone);
            startActivity(intent);
     Common.otp = new String(Common.SendOTP(4));
     SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage("" + myphone, null,  Common.otp + " is your password reset code of Crypto Drive account", null, null);
            Toast.makeText(getApplicationContext(), "We have messaged your password reset OTP !", Toast.LENGTH_SHORT).show();


        }}
    }




