package com.crypto.drive;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {


    LinearLayout layout_profile,layout_changpass;
    EditText name,phone,email,pass,oldpass,newpass;
    Button btnchange;
    String OLDPASS,NEWPASS;
    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        Common.Fragment="profile";

        SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
        final String suid = prefs.getString("logid", "No logid");
        final String sname = prefs.getString("name", "No name");
        final String sphone = prefs.getString("phone", "No phone");
        final String semail = prefs.getString("email", "No email");
        final String spass = prefs.getString("pass", "No pass");


        name=view.findViewById(R.id.profile_name);
        phone=view.findViewById(R.id.profile_phone);
        email=view.findViewById(R.id.profile_email);
        pass=view.findViewById(R.id.profile_password);
        layout_profile=view.findViewById(R.id.profile_layout_profile);
        layout_changpass=view.findViewById(R.id.profile_layout_changpass);
        oldpass=view.findViewById(R.id.profile_changepass_oldpass);
        newpass=view.findViewById(R.id.profile_changepass_newpass);
        btnchange=view.findViewById(R.id.profile_changepass_btnchange);

        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OLDPASS=oldpass.getText().toString();
                NEWPASS=newpass.getText().toString();

                if(OLDPASS.isEmpty()  || !OLDPASS.equals(spass)){
                    oldpass.requestFocus();
                    oldpass.setError("incorrect password");
                }else if(NEWPASS.isEmpty()){
                    newpass.requestFocus();
                    newpass.setError("New Passwors");
                }else{
                   new async_ChangePassword().execute();
                }
            }
        });

        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle( Html.fromHtml("<font color='#ff0000'>Change Password !</font>"));
                alertDialogBuilder.setMessage( Html.fromHtml("<font color='#2543EB'>Click  `Change` to redirect  change password form .`Cancel` for </font>"));
                //alertDialogBuilder.setMessage("`Click  `Decrypt` to decode and move file to secure text file list .`Cancel` for ");
                alertDialogBuilder.setPositiveButton("Change",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                arg0.cancel();
                             layout_profile.setVisibility(View.GONE);
                             layout_changpass.setVisibility(View.VISIBLE);



                            }
                        });

                alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        layout_profile.setVisibility(View.VISIBLE);
                        layout_changpass.setVisibility(View.GONE);
                        dialog.cancel();
                    }
                });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



                return false;
            }
        });

        name.setText(sname);
        phone.setText(sphone);
        email.setText(semail);

        return view;
    }

    public class async_ChangePassword extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd=new ProgressDialog(getContext());
            pd.setCancelable(false);
            pd.setMessage("Sending OTP ....");
            pd.setTitle("Please wait..");
            pd.show();

        }
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(2);
            SharedPreferences prefs = getActivity().getSharedPreferences("SharedData", MODE_PRIVATE);
            final   String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
            pdat.add(new BasicNameValuePair("key", "ChangePassword"));
            pdat.add(new BasicNameValuePair("newPassword",NEWPASS));
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

                    Toast.makeText(getContext(), "Password changed", Toast.LENGTH_LONG).show();
                    Profile nextFrag= new Profile();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();


                } else {
                    Toast.makeText(getContext(), "failed..!", Toast.LENGTH_LONG).show();
                    email.requestFocus();
                    email.setError("Error");
                }
            }catch (Exception e)
            {
                Toast.makeText(getContext(), e+"", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
