package com.crypto.drive;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class DecryTextList extends Fragment {
    Dialog dialog;
    ListView list;
    String id[],key[],text[];
    String TEXT;

    public DecryTextList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_decry_text_list, container, false);

        Common.Fragment="DecryTextList";

        list=view.findViewById(R.id.decry_textlist_list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {


                TEXT=text[position].trim();
                showCustomDialog();

            }
        });



        new async_getDecry_text_list().execute();

        return view;
    }







    public class async_getDecry_text_list extends AsyncTask<String, String, String>
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
            pdat.add(new BasicNameValuePair("key","getDecry_text_list" ));
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

    protected void showCustomDialog() {
        // TODO Auto-generated method stub
        // final Dialog dialog = new Dialog(User_View_Recipie_Details.this);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_view_decry_text);

        //final String pid=Pid;
        final Button ok = (Button) dialog.findViewById(R.id.cus_dlg_view_decrytext_btnadd);
        final EditText details = (EditText) dialog.findViewById(R.id.cus_dlg_view_decrytext_details);


        details.setText(TEXT);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });


        dialog.show();
    }
}
