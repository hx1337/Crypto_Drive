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

public class newpass extends AppCompatActivity {
    Button action;
    EditText PASS,CPASS,PHONE;
    String mypass,mycpass,myphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_newpass);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        Toolbar toolbar=new Toolbar(this);
        toolbar.setVisibility(View.INVISIBLE);

        TextView btn2 = findViewById(R.id.textViewSignUp);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(newpass.this, Registration.class));
            }
        });
        PASS = findViewById(R.id.inputPassword);
        CPASS = findViewById(R.id.inputConformPassword);
        PHONE = findViewById(R.id.forgetphno);

        action = findViewById(R.id.btnressetpass);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Validate();

            }


        });


    }



    private void Validate() {

        mypass = PASS.getText().toString();
        mycpass = CPASS.getText().toString();
        //  myphone = PHONE.getText().toString();

        Intent intent = getIntent();
        myphone = intent.getStringExtra("message");


        if (mypass.isEmpty()) {
            PASS.requestFocus();
            PASS.setError("Enter new password");
        } if (mycpass.isEmpty()) {
            CPASS.requestFocus();
            CPASS.setError("Confirm new password");
        }if (!mypass.trim().equals(mycpass)){

            Toast.makeText(getApplicationContext()," Password does not match ",  Toast.LENGTH_SHORT).show();

        }
        else {
            verify();

        }
    }

    public void verify() {

        Toast.makeText(getApplicationContext(),"Success" ,  Toast.LENGTH_SHORT).show();

}}