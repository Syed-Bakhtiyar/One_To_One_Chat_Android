package com.example.bakhtiyar.simplechatapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuthGlobal;

    FirebaseAuth.AuthStateListener fiAuthStateListener;

    String email, password;

    FirebaseUser firebaseUser;

    EditText txt_email, txt_password;

    Button login;

    ProgressDialog progressDialog;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




///////////////

//        fiAuthStateListener

        firebaseAuthGlobal = FirebaseAuth.getInstance();

        firebaseUser = firebaseAuthGlobal.getCurrentUser();

        if(firebaseUser != null){
            startActivity(new Intent( MainActivity.this,ProfileActivity.class));

        }


        login = (Button) findViewById(R.id.btn_login);

        textView = (TextView) findViewById(R.id.link_signup);

        txt_email = (EditText) findViewById(R.id.input_email);

        txt_password = (EditText) findViewById(R.id.input_password);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                login() ;


            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Signup_activity.class));

            }
        });





    }

    public void login(){


        email = txt_email.getText().toString().trim();

        password = txt_password.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this, "Please insert email or password", Toast.LENGTH_SHORT).show();

            return;
        }
            validation();



    }

    public void validation(){

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Wait");

        progressDialog.show();


        firebaseAuthGlobal.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    progressDialog.dismiss();

                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));


                }else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Not Success", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

}
