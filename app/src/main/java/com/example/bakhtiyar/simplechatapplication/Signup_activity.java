package com.example.bakhtiyar.simplechatapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_activity extends AppCompatActivity {

    FirebaseAuth firebaseAuthGlobal;

    FirebaseAuth.AuthStateListener fiAuthStateListener;

    DatabaseReference databaseReference;

    FirebaseDatabase firebaseDatabase;

    String name, email, password;

    EditText txt_name, txt_email, txt_password;

    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);


        firebaseAuthGlobal = FirebaseAuth.getInstance();

        fiAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if(firebaseUser !=null){

                    // do something when user login

                }

            }
        };

        signup = (Button) findViewById(R.id.sign_btn_login);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();

            }
        });



    }

    public void signup(){

        txt_name = (EditText) findViewById(R.id.sign_input_name);

        txt_email = (EditText) findViewById(R.id.sign_input_email);

        txt_password = (EditText) findViewById(R.id.sign_input_password);


        name = txt_name.getText().toString().trim();

        email = txt_email.getText().toString().trim();

        password = txt_password.getText().toString().trim();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(Signup_activity.this, "Please insert name, email, password", Toast.LENGTH_SHORT).show();

            return;
        }
        else {

            validation(name,email,password);

        }


    }

    public void validation(final String temp_name, final String temp_email, String temp_password){

        firebaseAuthGlobal.createUserWithEmailAndPassword(temp_email,temp_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    FirebaseUser user = firebaseAuthGlobal.getCurrentUser();

                    firebaseDatabase = FirebaseDatabase.getInstance();

                    databaseReference = firebaseDatabase.getReference().child("Users");

                    UserInfo userInfo = new UserInfo(temp_name,temp_email,user.getUid());

                    databaseReference.child(userInfo.getUuid()).setValue(userInfo);

                    Toast.makeText(Signup_activity.this, "Create Successfully", Toast.LENGTH_SHORT).show();


                }
                else {

                    Toast.makeText(Signup_activity.this, "Not Successful", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}
