package com.crypto.drive;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.crypto.drive.Utility.FileAdapter;
import com.crypto.drive.Utility.Files;

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
public class DecryImageList extends Fragment {

    Dialog dialog;

    ArrayList<Files> arrayList;
    FileAdapter adpater;
    ListView image_list;
    SharedPreferences sp;

    String KEY,ID,DECR_IMAGE;

    public DecryImageList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_decry_image_list, container, false);

        Common.Fragment="DecryImageList";

        image_list=view.findViewById(R.id.listview_dec_images);

        arrayList=new ArrayList<Files>();
        adpater=new FileAdapter((Activity) getContext(), arrayList);

        image_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ID=arrayList.get(position).getFid();
                DECR_IMAGE=arrayList.get(position).getImage();
                KEY=arrayList.get(position).getKey();
                showCustomDialog();

            }
        });


        new async_getDecreypted_images().execute();
        return view;
    }
        protected void showCustomDialog() {
        // TODO Auto-generated method stub
        // final Dialog dialog = new Dialog(User_View_Recipie_Details.this);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_decry_imageview);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        final ImageView dialog_imgage=dialog.findViewById(R.id.cus_dialog_decry_image);



            try {
                byte[] imageAsBytes = Base64.decode(DECR_IMAGE.getBytes());

                dialog_imgage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
//
                // const_img.setBackground(new BitmapDrawable(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)));
            } catch (IOException e) {

                e.printStackTrace();
            }


        dialog.show();
    }
//// edt start

//    public static String parseBase64(String base64) {
//
//        try {
//            Pattern pattern = Pattern.compile("((?<=base64,).*\\s*)",Pattern.DOTALL|Pattern.MULTILINE);
//            Matcher matcher = pattern.matcher(base64);
//            if (matcher.find()) {
//                return matcher.group().toString();
//            } else {
//                return "";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return "";
//    }
//
//
//    private void downloadFileFromBase64(String fileContent) {
//        try {
//            Date now = new Date();
//            android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
//
//            File file = new File(Environment.getExternalStorageDirectory()+ "/" + now + ".png");
//            if(!file.exists()){
//                file.mkdirs();
//                if (fileContent != null) {
//                    String attachment = parseBase64(fileContent);
//                    byte[] byteArr = android.util.Base64.decode(attachment, android.util.Base64.DEFAULT);
//                    File f = new File(file.getAbsolutePath(),"sample.png");
//                    f.createNewFile();
//                    FileOutputStream fo = new FileOutputStream(f);
//                    fo.write(byteArr);
//                    fo.close();
//                    Toast.makeText(getContext(), "File downloaded", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }}catch (Exception e){
//            e.printStackTrace();
//        }
//    }
////// edt end

    public class async_getDecreypted_images extends AsyncTask<String, String, String>
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
            pdat.add(new BasicNameValuePair("key", "getDecreypted_images"));
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


                    Gson gson=new Gson();
                    Files[] beans=gson.fromJson(result, Files[].class);

                    for(Files ListBean:beans){
                        Files bean=new Files();

                        bean.setFid(ListBean.getFid());
                        bean.setName(ListBean.getName());
                        bean.setKey(ListBean.getKey());
                        bean.setImage(ListBean.getImage());
                        bean.setStatus(ListBean.getStatus());


                        arrayList.add(bean);
                        image_list.setAdapter(adpater);
                    }






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
