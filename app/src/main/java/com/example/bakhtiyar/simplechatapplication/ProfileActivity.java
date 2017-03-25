package com.example.bakhtiyar.simplechatapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();




        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    // User is signed in

                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Intent call = new Intent(ProfileActivity.this, MainActivity.class);
                    call.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(call);
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



        Fragment adp = new Fragment(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

//            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        // pager.setOffscreenPageLimit(0);

//            tabLayout.setupWithViewPager(pager);
        pager.setAdapter(adp);


    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            logOut();
            //Toast.makeText(TodoList_Mainactivity.this, "You Pressed The Setting", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
