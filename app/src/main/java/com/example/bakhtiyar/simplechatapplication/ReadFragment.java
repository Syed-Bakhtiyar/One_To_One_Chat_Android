package com.example.bakhtiyar.simplechatapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {


    View v;


    public ReadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_read, container, false);


        v.findViewById(R.id.imgname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                alertDialog.setIcon(R.mipmap.ic_launcher);

                alertDialog.setTitle("Developer");

                alertDialog.setMessage("Syed Muhammad Bakhtiyar");

                alertDialog.show();


            }
        });


        return v;
    }

}
