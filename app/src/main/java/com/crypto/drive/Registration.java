package com.crypto.drive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {

    EditText name,phone,email,password;
    TextView singup;
    Button btnreg;
    String NAME,PHONE,EMAIL,PASSWORD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registration);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        Toolbar toolbar=new Toolbar(this);
        toolbar.setVisibility(View.INVISIBLE);

        name=(EditText)findViewById(R.id.reg_name);
        phone=(EditText)findViewById(R.id.reg_phone);
        email=(EditText)findViewById(R.id.reg_email);
        password=(EditText)findViewById(R.id.reg_password);
        singup=(TextView)findViewById(R.id.reg_signup);
        btnreg=(Button)findViewById(R.id.reg_btnreg);



        singup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(new Intent(getApplicationContext(),LogIn.class));
                return false;
            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                NAME=name.getText().toString().trim();
                PHONE=phone.getText().toString().trim();
                EMAIL=email.getText().toString().trim();
                PASSWORD=password.getText().toString().trim();

                if (NAME.isEmpty()) {
                    name.requestFocus();
                    name.setError("Enter Valid UserName)");
                } else if (PHONE.isEmpty()) {
                    phone.requestFocus();
                    phone.setError("Enter Valid password)");
                } else if (EMAIL.isEmpty()) {
                    email.requestFocus();
                    email.setError("Enter Valid password)");
                } else if (PASSWORD.isEmpty()) {
                    password.requestFocus();
                    password.setError("Enter Valid password)");
                } else {
                    new async_register().execute("register",NAME,PHONE,EMAIL,PASSWORD);
                }




            }
        });


    }


    public class async_register extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd=new ProgressDialog(Registration.this);
            pd.setCancelable(false);
            pd.setMessage("Your Registration going on..");
            pd.setTitle("Please wait..");
            pd.show();

        }
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);

            pdat.add(new BasicNameValuePair("key", params[0]));
            pdat.add(new BasicNameValuePair("name", params[1]));
            pdat.add(new BasicNameValuePair("mobile", params[2]));
            pdat.add(new BasicNameValuePair("email",params[3]));
            pdat.add(new BasicNameValuePair("password",params[4]));




            HttpClient client=new DefaultHttpClient();
            HttpPost mypdat=new HttpPost(Common.URL_PATTERN);
            try
            {
                mypdat.setEntity(new UrlEncodedFormEntity(pdat));
                //Log.d("post data","post data");
            }
            catch (UnsupportedEncodingException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try
            {
                HttpResponse re=client.execute(mypdat);
                HttpEntity entity=re.getEntity();
                str= EntityUtils.toString(entity);
                int status=re.getStatusLine().getStatusCode();
                if(status==200)
                {
                    return str;
                }
            }
            catch (ClientProtocolException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } return null;
        }

        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            pd.dismiss();

            Log.d("result: ", result);
            try {
                if (!result.trim().equals("failed")) {
                    Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_LONG).show();
                   startActivity(new Intent(getApplicationContext(),LogIn.class));

                } else {
                    Toast.makeText(getApplicationContext(), "Registration failed..!", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e)
            {
                Toast.makeText(Registration.this, e+"", Toast.LENGTH_SHORT).show();
            }
        }

    }





}
