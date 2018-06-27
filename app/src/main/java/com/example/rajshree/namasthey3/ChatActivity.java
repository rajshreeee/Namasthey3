package com.example.rajshree.namasthey3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {


    private TextView contact_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        contact_name = findViewById(R.id.contact_name);
        contact_name.setText(getIntent().getStringExtra("contactusername"));
    }
}
