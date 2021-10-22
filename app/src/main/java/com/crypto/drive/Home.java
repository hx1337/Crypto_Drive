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
public class Home extends Fragment {

    ImageView folderdec,folderenc;
    public Home() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        Common.Fragment="home";

        getActivity().setTitle(" Home");

        folderdec=view.findViewById(R.id.home_folderdec);
        folderenc=view.findViewById(R.id.home_folderenc);


        folderdec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DecryptedHome nextFrag= new DecryptedHome();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

                return false;
            }
        });

        folderenc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                EncryptedHome nextFrag= new EncryptedHome();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

               // startActivity(new Intent(getContext(),EncryptedHome.class));
                return false;
            }
        });




        return view;
    }

}
