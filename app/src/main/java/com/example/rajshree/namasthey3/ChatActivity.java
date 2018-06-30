package com.example.rajshree.namasthey3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference();
    private FirebaseAuth mAuth;
    private String userId;



    private TextView contact_name;
    private Button send_message;
private EditText chat_message;
private RecyclerView mMessagesList;
private final List<Messages> messagesList = new ArrayList<>();
private LinearLayoutManager mlinearlayout;
private MessageAdapter mAdapter;

private String name;
private String chatperson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        contact_name = findViewById(R.id.contact_name);
        send_message = findViewById(R.id.send_message);
        chat_message = findViewById(R.id.chat_message);
        mMessagesList = findViewById(R.id.messagesList);
        mlinearlayout = new LinearLayoutManager(this);
mAdapter = new MessageAdapter(messagesList);
        mMessagesList.setHasFixedSize(true);
        mMessagesList.setLayoutManager(mlinearlayout);
        mMessagesList.setAdapter(mAdapter);
       chatperson = getIntent().getStringExtra("contactusername");
        SharedPreferences sharedPref = getSharedPreferences("mypref", Context.MODE_PRIVATE);
         name= sharedPref.getString("username","");
        contact_name.setText(getIntent().getStringExtra("contactusername"));

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
loadMessages();
        myRef.child("chat").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(chatperson)){

                    Map chatAddMap = new HashMap();
                    chatAddMap.put("timestamp", ServerValue.TIMESTAMP);

                    Map chatUserMap = new HashMap();
                    chatUserMap.put("chat/"+name+"/"+chatperson, chatAddMap);
                    chatUserMap.put("chat/"+chatperson+"/"+name, chatAddMap);

                    myRef.updateChildren(chatUserMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

      send_message.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendmessage();
          }
      });
    }

    private void loadMessages() {

        myRef.child("messages").child(name).child(chatperson).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Messages message = dataSnapshot.getValue(Messages.class);
                messagesList.add(message);
                mAdapter.notifyDataSetChanged();
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

    private void sendmessage() {

        String message = chat_message.getText().toString();
        if(!TextUtils.isEmpty(message)){

            String current_user_ref = "messages/"+name+"/"+chatperson;
            String chat_user_ref = "messages/"+chatperson+"/"+name;


            DatabaseReference user_message_push =myRef.child("messages").child(name).child(chatperson).push();
String push_id = user_message_push.getKey();
        Map messageMap = new HashMap();
        messageMap.put("message", message);
        messageMap.put("timestamp", ServerValue.TIMESTAMP);
        messageMap.put("from",userId);

        Map messageUserMap = new HashMap();
        messageUserMap.put(current_user_ref+"/"+push_id,messageMap);
            messageUserMap.put(chat_user_ref+"/"+push_id,messageMap);
            chat_message.setText("");

            myRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                }
            });


        }
    }
}
