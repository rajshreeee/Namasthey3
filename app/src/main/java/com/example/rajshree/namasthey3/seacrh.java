package com.example.rajshree.namasthey3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class seacrh extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String userID;
    private String search1;
    private String a1 , a2;
    private TextView name;
    private DatabaseReference mDatabaseRefFriends;
    private FirebaseDatabase mFirebase;
    private ArrayList<String> arrayList;
    private ArrayList<String> array1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seacrh);
        Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Main");
        arrayList=new ArrayList<String>();
        array1 = new ArrayList<>();
        String[] array;
        Intent ep1= getIntent();
        search1 = ep1.getStringExtra("search_value");
        a1 = "c";
        a2= "d";


        final ListView resultsListView = (ListView) findViewById(R.id.results_listview);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
//        Toast.makeText(getApplicationContext(),search,Toast.LENGTH_LONG).show();
        //Firebase Database Reference
        mFirebase=FirebaseDatabase.getInstance();
        mDatabaseRefFriends=mFirebase.getReference().child("Friends").child(userID);

        mDatabaseRefFriends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childs: dataSnapshot.getChildren()) {
                    if ( search1.equals(String.valueOf(childs.child("name").getValue()))) {
            a1 = "a";
            a2 = "a";

//              int i = 1;
//              array1.set(i, String.valueOf(childs.child("name").getValue()));
//
//
//                    Toast.makeText(getApplicationContext(), array1.get(i), Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), array1.get(i), Toast.LENGTH_LONG).show();
//                    i = i + 1;
//                }

                       // Toast.makeText(getApplicationContext(), search1, Toast.LENGTH_LONG).show();
                        HashMap<String, String> nameAddresses = new HashMap<>();
                        nameAddresses.put("Name", search1);

                        List<HashMap<String, String>> listItems = new ArrayList<>();


                        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), listItems, R.layout.list_item,
                                new String[]{"First Line", "Second Line"},
                                new int[]{R.id.text1, R.id.text2});


                        Iterator it = nameAddresses.entrySet().iterator();
                        while (it.hasNext()) {
                            HashMap<String, String> resultsMap = new HashMap<>();
                            Map.Entry pair = (Map.Entry) it.next();
                            resultsMap.put("First Line", pair.getKey().toString());
                            resultsMap.put("Second Line", pair.getValue().toString());
                            listItems.add(resultsMap);
                        }


                        resultsListView.setAdapter(adapter);

                    }
//                    else if( a != "a") {
//
//
//
//                    }

                                   }

        if( a1.equals(a2)){
            Toast.makeText(getApplicationContext(), search1, Toast.LENGTH_LONG).show();

        }
        else {
            Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                        Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();

        }
                }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

}
