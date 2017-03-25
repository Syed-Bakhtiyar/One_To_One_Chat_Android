package com.example.bakhtiyar.simplechatapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListOfChatting extends AppCompatActivity {

    Bundle bundle;

    StorageReference storageReference;

    String uid;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    ListView listView;

    public static final int RC_SIGN_IN = 1;

    private static final int RC_PHOTO_PICKER =  2;

    SentReadMsgsAdapter sentReadMsgsAdapter;

    ArrayList<SentReadMsgs> arrayList;

    Button button;

    String msg;

    EditText editText;

    FirebaseAuth firebaseAuth;

    FirebaseStorage firebaseStorage;

//    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//    Date date = new Date();
//
//    String dttm =dateFormat.format(date);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_chatting);

        bundle = getIntent().getExtras();

        uid = bundle.getString("id");

        myClass.uid = "";

        myClass.uid = uid;


//        Intent t = new Intent(ListOfChatting.this,PushService.class);
//
//
//        startService(t);


        //  firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth  = FirebaseAuth.getInstance();

        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference().child("chat_photos");



        editText = (EditText) findViewById(R.id.messageEditText);

        button = (Button) findViewById(R.id.sendButton);



        Log.d("uid", uid);

        arrayList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.messageListView);

        sentReadMsgsAdapter = new SentReadMsgsAdapter(arrayList,this);

        listView.setAdapter(sentReadMsgsAdapter);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().trim().length()>0){
                    button.setEnabled(true);
                }else {
                    button.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                msg = editText.getText().toString().trim();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                Date date = new Date();

                String dttm =dateFormat.format(date);


                SentReadMsgs sentReadMsgs = new SentReadMsgs( msg, "sender", firebaseAuth.getCurrentUser().getDisplayName(),dttm,databaseReference.push().getKey(),null,true);

                databaseReference.child(uid).child(firebaseAuth.getCurrentUser().getUid()).child(sentReadMsgs.getKey()).setValue(sentReadMsgs);

                sentReadMsgs.setFlag(false);

                databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child(uid).child(sentReadMsgs.getKey()).setValue(sentReadMsgs);

                editText.setText("");

            }
        });

        findViewById(R.id.mPhotoPickerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

            }
        });

        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



                SentReadMsgs sentReadMsgs = dataSnapshot.getValue(SentReadMsgs.class);
                arrayList.add(sentReadMsgs);

                sentReadMsgsAdapter.notifyDataSetChanged();




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



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("req", requestCode+" onActivityResult: "+RC_PHOTO_PICKER + resultCode +" = "+RESULT_OK);

        if(requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){

            Uri uri = data.getData();

            StorageReference photoref = storageReference.child(uri.getLastPathSegment());

            photoref.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    Date date = new Date();

                    String dttm =dateFormat.format(date);


                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    SentReadMsgs sentReadMsgs = new SentReadMsgs( null, "sender", firebaseAuth.getCurrentUser().getDisplayName(),dttm,databaseReference.push().getKey(),downloadUrl.toString(),true);

                    databaseReference.child(uid).child(firebaseAuth.getCurrentUser().getUid()).child(sentReadMsgs.getKey()).setValue(sentReadMsgs);

                    sentReadMsgs.setFlag(false);

                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child(uid).child(sentReadMsgs.getKey()).setValue(sentReadMsgs);



                }
            });


        }
    }


//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        finish();
//    }
}
