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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class EncryTextList extends Fragment {

    ListView list;
    String id[],key[],text[];
    String KEY,ID,ENCR_TEXT,DECR_TEXTID;



    public EncryTextList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_encry_text_list, container, false);

        list=view.findViewById(R.id.encry_textlist_list);




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {

                ID=id[position].trim();
                ENCR_TEXT=text[position].toString().trim();
                KEY=key[position].toString().trim();


                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                //alertDialogBuilder.setTitle ("Decrypt Texts !");
                alertDialogBuilder.setTitle( Html.fromHtml("<font color='#ff0000'>Decrypt Texts !</font>"));
                alertDialogBuilder.setMessage( Html.fromHtml("<font color='#2543EB'>Click  `Decrypt` to decode and move file to secure text file list .`Cancel` for </font>"));
                //alertDialogBuilder.setMessage("`Click  `Decrypt` to decode and move file to secure text file list .`Cancel` for ");
                alertDialogBuilder.setPositiveButton("Decrypt",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                try {
                                    DECR_TEXTID= Decryption.decrypt(ENCR_TEXT,KEY);

                                   new async_Decrypt_text_Update_Action().execute();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }



                            }
                        });

                alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });


        new async_getEncry_text_list().execute();

        return view;
    }







    public class async_getEncry_text_list extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd=new ProgressDialog(getContext());
            pd.setCancelable(false);
            pd.setMessage(" going on..");
            pd.setTitle("Please wait..");
            pd.show();

        }
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);
            SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
            final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
            pdat.add(new BasicNameValuePair("key","getEncry_text_list" ));
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

                    String   arr[] = result.trim().split("#");
                    id = arr[0].trim().split("&");
                    text = arr[1].trim().split("&");
                    key = arr[2].trim().split("&");
                    String listdata[] = arr[3].trim().split("&");

                    ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.cust_list, listdata);
                    list.setAdapter(adapter);

                } else {
                    Toast.makeText(getContext(), "no data..!", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e)
            {
                Toast.makeText(getContext(), e+"", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public class async_Decrypt_text_Update_Action extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd=new ProgressDialog(getContext());
            pd.setCancelable(false);
            pd.setMessage(" Decrypting  ..");
            pd.setTitle("Please wait..");
            pd.show();

        }
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str="";
            //Log.d("inside inback leejo","inside inback");
            List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);

            pdat.add(new BasicNameValuePair("key","Decrypt_text_Update_Action" ));
            pdat.add(new BasicNameValuePair("id",ID));
            pdat.add(new BasicNameValuePair("dec_text",DECR_TEXTID));

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

                    EncryTextList nextFrag= new EncryTextList();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();

                } else {
                    Toast.makeText(getContext(), " failed..!", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e)
            {
                Toast.makeText(getContext(), e+"", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
