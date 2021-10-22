package com.crypto.drive.Utility;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crypto.drive.Base64;
import com.crypto.drive.R;

import java.io.IOException;
import java.util.ArrayList;

public class FileAdapter extends ArrayAdapter<Files> {

    Activity context;
    ArrayList<Files> rest_List;

    public FileAdapter(Activity context, ArrayList<Files> rest_List) {
        super(context, R.layout.custom_view_files, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_view_files, null, true);

        TextView image_id = (TextView) view.findViewById(R.id.cust_image_id);
        TextView image_name = (TextView) view.findViewById(R.id.cust_image_cname);

//       ImageView const_img = (ImageView) view.findViewById(R.id.cust_construct_img);
        ImageView const_img = (ImageView) view.findViewById(R.id.cust_image_img);
        // LinearLayout const_img = (LinearLayout) view.findViewById(R.id.cust_construct_img);

//        const_img.getLayoutParams().height=100;
//        const_img.getLayoutParams().width=100;
//        const_img.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        ImageView fimage = (ImageView) view.findViewById(R.id.cust_dish_image);
        image_id.setText(rest_List.get(position).getFid());
        image_name.setText(rest_List.get(position).getName());

        if(rest_List.get(position).getStatus().equals("0")) {

            String base = rest_List.get(position).getImage();
            // String base = rest_List.get(position).getPicture();

            try {
                byte[] imageAsBytes = Base64.decode(base.getBytes());

                const_img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
//
                // const_img.setBackground(new BitmapDrawable(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)));
            } catch (IOException e) {

                e.printStackTrace();
            }

        }else{

            const_img.setImageResource(R.drawable.encrypted_image);

        }


        return view;
    }


}
