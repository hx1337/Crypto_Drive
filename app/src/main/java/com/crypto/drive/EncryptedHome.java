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
public class EncryptedHome extends Fragment {

    ImageView encry_text,encry_image;
    public EncryptedHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_encrypted_home, container, false);

        Common.Fragment="EncryptedHome";

        getActivity().setTitle("Encrypted Home");

        encry_text=view.findViewById(R.id.encrypted_home_text);
        encry_image=view.findViewById(R.id.encrypted_home_image);

        encry_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                EncryTextList nextFrag= new EncryTextList();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();


                return false;
            }
        });

        encry_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                EncryImageList nextFrag= new EncryImageList();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

                return false;
            }
        });

        return view;

    }






}
