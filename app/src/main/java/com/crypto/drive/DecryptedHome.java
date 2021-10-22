package com.crypto.drive;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DecryptedHome extends Fragment {

    ImageView decry_text,decry_image;
    public DecryptedHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_decrypted_home, container, false);

        Common.Fragment="DecryptedHome";



        getActivity().setTitle("Decrypted Home");


        decry_text=view.findViewById(R.id.decrypted_home_text);
        decry_image=view.findViewById(R.id.decrypted_home_image);
        decry_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DecryTextList nextFrag= new DecryTextList();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

                return false;
            }
        });

        decry_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DecryImageList nextFrag= new DecryImageList();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                // startActivity(new Intent(getApplicationContext(),DecryptedHome.class));
                return false;
            }
        });

        return view;
    }



}
