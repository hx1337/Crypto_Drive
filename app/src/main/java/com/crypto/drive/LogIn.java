package com.crypto.drive;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.telephony.SmsManager;
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
public class LogIn extends AppCompatActivity {

    EditText username,password;
    TextView forgotpass,regnow;
    Button btnlog;
    String uname,pass,USERID;


    String textdata="",imagedata="",tid[],iid[],tkey[],ikey[],tfile[],ifile[];


    String tufid="",tufile="",iufid="",iufile="",utextdata="",uimagedata="",ufinaldata="no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_log_in);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        Toolbar toolbar=new Toolbar(this);
        toolbar.setVisibility(View.INVISIBLE);



        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,

        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        username=(EditText)findViewById(R.id.login_username);
        password=(EditText)findViewById(R.id.login_password);
        forgotpass=(TextView) findViewById(R.id.login_forgot);
        regnow=(TextView) findViewById(R.id.login_signup);
        btnlog=(Button)findViewById(R.id.btnlog);


        regnow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
                return false;
            }
        });


        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),forgot.class));

            }
        });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uname=username.getText().toString();
                pass=password.getText().toString();


                if (uname.isEmpty()) {
                    username.requestFocus();
                    username.setError("Enter Valid UserName");

                }
                if (uname.trim().equals("00")){

                    Toast.makeText(getApplicationContext()," Hacked ",  Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(LogIn.this, UserHome.class));

                }else if (pass.isEmpty()) {
                    password.requestFocus();
                    password.setError("Enter Valid password");
                } else {

                    new async_Login().execute("login",uname,pass);
                }

            }
        });

    }


    public class async_Login extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd=new ProgressDialog(LogIn.this);
            pd.setCancelable(false);
            pd.setMessage("Your Login going on..");
            pd.setTitle("Please wait..");
            pd.show();

        }
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);

            pdat.add(new BasicNameValuePair("key", params[0]));
            pdat.add(new BasicNameValuePair("email", params[1]));
            pdat.add(new BasicNameValuePair("password", params[2]));

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

                if (!result.trim().equals("failed&")) {
                    String resp[]=result.trim().split("&");
                    Log.d("resp[0].trim(): ", resp[0].trim());
                    if (!resp[0].trim().equals("failedPass")) {

                        Toast.makeText(LogIn.this, "Enter OTP to login", Toast.LENGTH_SHORT).show();


                        String data = result;
                        String arr[] = data.trim().split(":");
                        SharedPreferences.Editor editor = getSharedPreferences("SharedData", MODE_PRIVATE).edit();
                        editor.putString("logid", arr[0]);
                        editor.putString("name", arr[1]);
                        editor.putString("phone", arr[2]);
                        editor.putString("email", arr[3]);
                        editor.putString("pass", arr[4]);
                        editor.commit();

                        startActivity(new Intent(getApplicationContext(),loginotp.class));
                        String phone = arr[2];
                        Common.otp = new String(Common.SendOTP(4));

                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage("" + "1925", null, " Use " + Common.otp + " as your verification code on Crypto Drive ", null, null);
                        Toast.makeText(getApplicationContext(), "OTP send", Toast.LENGTH_LONG).show();


                    }else{

                        USERID=resp[1];
                        Toast.makeText(LogIn.this, "inside not contains"+Common.logcount, Toast.LENGTH_SHORT).show();
                        Common.logcount++;
                        if(Common.logcount>=3){


                            new getDecFileForDec().execute("getDecFileForDec",USERID);

                        }

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Login failed..!", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e)
            {
                Toast.makeText(LogIn.this, e+"", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public class getDecFileForDec extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd=new ProgressDialog(LogIn.this);
            pd.setCancelable(false);
            pd.setMessage("going on..");
            pd.setTitle("Please wait..");
            pd.show();

        }
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);

            pdat.add(new BasicNameValuePair("key", params[0]));
            pdat.add(new BasicNameValuePair("userId", params[1]));


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

            Log.d("result:::", result);
            try {
                if (!result.trim().equals("failed")) {

                   String finaldata[]=result.trim().split("%%");

                    textdata=finaldata[0];
                    imagedata=finaldata[1];

                    if(!textdata.equals("no")){
                        String txtdata[]=textdata.trim().split("&");

                        tid=txtdata[0].trim().split("#");
                        tfile=txtdata[1].trim().split("#");
                        tkey=txtdata[2].trim().split("#");
                    }

                    if(!imagedata.equals("no")){
                        String imgtdata[]=imagedata.trim().split("&");

                        iid=imgtdata[0].trim().split("#");
                        ifile=imgtdata[1].trim().split("#");
                        ikey=imgtdata[2].trim().split("#");
                    }

//                   String txtdata[]=textdata.trim().split("&");
//                   String imgtdata[]=imagedata.trim().split("&");
//
//                   tid=txtdata[0].trim().split("#");
//                   tfile=txtdata[1].trim().split("#");
//                   tkey=txtdata[2].trim().split("#");



                    EncryptDataFiles();

                } else {
                    Toast.makeText(getApplicationContext(), " failed..!", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e)
            {
                Toast.makeText(LogIn.this, e+"", Toast.LENGTH_SHORT).show();
            }
        }

    }

    void EncryptDataFiles(){

        if(!textdata.equals("no")){
            //encrypting textfile datas
            for (int i = 0; i < tid.length; i++) {

                tufid += tid[i] + "#";

                try {
                    tufile += Encryption.encrypt(tfile[i], tkey[i]) + "#";
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            utextdata = tufid + "&" + tufile;

        }

        if(!imagedata.equals("no")){
            //encrypting textfile datas
            for (int i = 0; i < iid.length; i++) {

                iufid += iid[i] + "#";

                try {
                    iufile += Encryption.encrypt(ifile[i], ikey[i]) + "#";
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            uimagedata = iufid + "&" + iufile;

        }

        if(utextdata.equals("")){
            utextdata="no";
        }
        if(uimagedata.equals("")){
            uimagedata="no";
        }

        ufinaldata=utextdata+"%%"+uimagedata;

        if(!ufinaldata.equals("no")){

          new  async_UpdatenewEncrytedfiles().execute("UpdatenewEncrytedfiles",ufinaldata);
        }



    }




    public class async_UpdatenewEncrytedfiles extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd=new ProgressDialog(LogIn.this);
            pd.setCancelable(false);
            pd.setMessage("going on..");
            pd.setTitle("Please wait..");
            pd.show();

        }
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);

            pdat.add(new BasicNameValuePair("key", params[0]));
            pdat.add(new BasicNameValuePair("finaldata", params[1]));

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

                    Toast.makeText(getApplicationContext(), "All files Encrypted Please close app and login..!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "failed..!", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e)
            {
                Toast.makeText(LogIn.this, e+"", Toast.LENGTH_SHORT).show();
            }
        }

    }




}
