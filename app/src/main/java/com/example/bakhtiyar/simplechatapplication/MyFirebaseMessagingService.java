package com.example.bakhtiyar.simplechatapplication;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Bakhtiyar on 12/21/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private DatabaseReference mDatabase;

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "NotificationMessage data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "NotificationMessage Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        FirebaseDatabase.getInstance().getReference().child("Notificatons").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("TOG", "" + dataSnapshot.getValue());

                for (DataSnapshot data: dataSnapshot.getChildren()){


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
