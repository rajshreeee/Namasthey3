package com.example.rajshree.namasthey3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addContact extends AppCompatActivity {
    private Button next;
    private EditText phone_no;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        next = findViewById(R.id.next);
        phone_no = findViewById(R.id.phone_no);
    }

    public void nextClick(View view){
        Intent nextIntent = new Intent(this,contactReview.class);
        nextIntent.putExtra("phonenumber",phone_no.getText().toString());
        startActivity(nextIntent);
    }
}
