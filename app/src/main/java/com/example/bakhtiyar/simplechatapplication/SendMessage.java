package com.example.bakhtiyar.simplechatapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendMessage extends Fragment {

    ListView listView;

    View view;

    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;

    ArrayList<UserInfo> info;

    UserAdapter userAdapter;

    DatabaseReference databaseReference;

    public SendMessage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_send_message, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Users");;

        listView = (ListView) view.findViewById(R.id.list);

        info = new ArrayList<>();

        userAdapter = new UserAdapter(info,getContext());


        listView.setAdapter(userAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                UserInfo us = dataSnapshot.getValue(UserInfo.class);

                    info.add(us);
                    userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(getActivity().getApplicationContext(),ListOfChatting.class);

                intent.putExtra("id",info.get(i).getUuid());
                Log.d("Id", info.get(i).getUuid());
                startActivity(intent);
            }
        });






        return view;
    }

}
