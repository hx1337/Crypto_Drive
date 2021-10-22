package com.crypto.drive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class UploadText extends AppCompatActivity {

    EditText name,details,key;
    Button btnup;
    String NAME,DETAILS,KEY,ENC_DETAILS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_text);
        name= findViewById(R.id.uploadtext_name);
        details= findViewById(R.id.uploadtext_details);
       // key= findViewById(R.id.uploadtext_key);
        btnup= findViewById(R.id.uploadtext_btnup);

         btnup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 NAME=name.getText().toString().trim();
                 DETAILS=details.getText().toString().trim();
                 KEY= String.valueOf(SecureKeyGeneratior.Genaratekey(8));


                 if(NAME.isEmpty()){
                     name.requestFocus();
                     name.setError("name");
                 }else if(DETAILS.isEmpty()){
                     details.requestFocus();
                     details.setError("Details");
                 }else if(KEY.isEmpty()){
                     Toast.makeText(UploadText.this, "Key SecureKeyGeneratior faield", Toast.LENGTH_SHORT).show();;
                 }else{
                     try {
                         ENC_DETAILS= Encryption.encrypt(DETAILS,KEY);
                     } catch (Exception e) {
                         e.printStackTrace();
                     }

                     new async_UploadText().execute("UploadText",NAME,ENC_DETAILS,KEY);
                 }

             }
         });
    }

    public class async_UploadText extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd=new ProgressDialog(UploadText.this);
            pd.setCancelable(false);
            pd.setMessage("Encrypting....");
            pd.setTitle("Please wait..");
            pd.show();

        }
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("SharedData", MODE_PRIVATE);
            final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
            pdat.add(new BasicNameValuePair("key", params[0]));
            pdat.add(new BasicNameValuePair("name", params[1]));
            pdat.add(new BasicNameValuePair("text", params[2]));
            pdat.add(new BasicNameValuePair("enckey",params[3]));
            pdat.add(new BasicNameValuePair("uid",uid));




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
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),UserHome.class));

                } else {
                    Toast.makeText(getApplicationContext(), " failed..!", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e)
            {
                Toast.makeText(UploadText.this, e+"", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
