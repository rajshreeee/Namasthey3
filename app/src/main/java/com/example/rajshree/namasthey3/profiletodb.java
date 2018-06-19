package com.example.rajshree.namasthey3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rajshree.namasthey3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profiletodb extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    EditText p_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiletodb);
        p_name = findViewById(R.id.editText);
    }

    public void saveClick(View view) {

        myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(p_name.getText().toString().trim());
        myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone_no").setValue(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.academics:
                if (checked)
                    myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").setValue("academics");
                    break;

            case R.id.sports:
                if (checked)
                    myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").setValue("sports");
                    break;
                    case R.id.beauty:
                if(checked)
                    myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").setValue("beauty");
                    break;

        }
    }
}
