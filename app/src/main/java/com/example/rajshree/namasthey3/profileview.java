package com.example.rajshree.namasthey3;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import  android.support.design.widget.FloatingActionButton;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class profileview extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    String TAG = "profile_view";
    private ValueEventListener mprofileListener;
    private ListView mListView;
    private FirebaseAuth mAuth;
    private String name;
     private String interest;
    private String userID;
    private DatabaseReference mDatabaseRefFriends;
    private FirebaseDatabase mFirebase;

  private FloatingActionButton editFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileview);
        Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Proile");
        final ListView resultsListView = (ListView) findViewById(R.id.results_listview);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
editFab = (FloatingActionButton) findViewById(R.id.editFab);
 editFab.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                 startActivity(new android.content.Intent(getApplicationContext(),profiletodb.class ));
            }
        });
       // String email = String.valueOf(childs.child("email").getValue());
  //      Toast.makeText(getApplicationContext(),userID, Toast.LENGTH_LONG).show();
        mFirebase = FirebaseDatabase.getInstance();
        mDatabaseRefFriends = mFirebase.getReference().child("users");
        mDatabaseRefFriends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childs : dataSnapshot.getChildren()) {
                    String retri_userid=childs.getKey();
                    //Toast.makeText(getApplicationContext(), userID, Toast.LENGTH_LONG).show();
                    Log.i(TAG,"hhhh");
                    if (retri_userid.equals(userID)) {
                        name = String.valueOf(childs.child("name").getValue());

                        interest = String.valueOf(childs.child("interests").getValue());
                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();

                        HashMap<String, String> nameAddresses = new HashMap<>();
                        nameAddresses.put("Name", name);
                        nameAddresses.put("Interest", interest);





                        List<HashMap<String, String>> listItems = new ArrayList<>();


                        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), listItems, R.layout.list_item,
                                new String[]{"First Line", "Second Line"},
                                new int[]{R.id.text1, R.id.text2});


                        Iterator it = nameAddresses.entrySet().iterator();
                        while (it.hasNext())
                        {
                            HashMap<String, String> resultsMap = new HashMap<>();
                            Map.Entry pair = (Map.Entry)it.next();
                            resultsMap.put("First Line", pair.getKey().toString());
                            resultsMap.put("Second Line", pair.getValue().toString());
                            listItems.add(resultsMap);
                        }


                        resultsListView.setAdapter(adapter);




                    }




                    String email = String.valueOf(childs.child(userID).child("").getValue());

                    //String email = String.valueOf(childs.child("email").getValue());
                   // Toast.makeText(getApplicationContext(),email, Toast.LENGTH_LONG).show();
                }

                //  StringBuilder builder=new StringBuilder();

//                  for (String i : arrayList) {
//                      //  Toast.makeText(getApplicationContext(),i,Toast.LENGTH_LONG).show();
//                      if (i.equals(search1)) {
//
//                          Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
//                          break;
//                      }
//                      Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
//
//                  }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//
//            ArrayList<String> array  = new ArrayList<>();
//            array.add(uInfo.getName());
//            array.add(uInfo.getInterests());
//
//
//            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
//            mListView.setAdapter(adapter);


    }
}