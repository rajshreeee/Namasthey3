package com.example.rajshree.namasthey3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class contactReview extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    String TAG = "contactReview";
    private ValueEventListener mprofileListener;
    private ListView mListView;
    private FirebaseAuth mAuth;
    private String userId;
    private FirebaseAuth.AuthStateListener mAuthListener;
final String CurrentDate = DateFormat.getDateTimeInstance().format(new Date());

    profileinfofromdb uInfo = new profileinfofromdb();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_review);
        mListView = (ListView) findViewById(R.id.listviewcr);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();


        myRef.orderByChild("phone_no").equalTo(getIntent().getStringExtra("phonenumber")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            uInfo.setName(ds.getValue(profileinfofromdb.class).getName()); //set the name







            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getName());



            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);


        }

    }
    public void DoneClick(View view){
        Toast.makeText(getApplicationContext(),uInfo.getName()+" " +"is now in your contacts" ,Toast.LENGTH_LONG).show();
        Map FreindsMap = new HashMap();
        FreindsMap.put("Friends/"+userId+"/"+uInfo.getName()+"/"+"/date",CurrentDate);
        FreindsMap.put("Friends/"+userId+"/"+uInfo.getName()+"/"+"/name",uInfo.getName());
database.getReference().updateChildren(FreindsMap);


    }

}