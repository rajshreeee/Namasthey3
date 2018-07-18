package com.example.rajshree.namasthey3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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
    Button save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiletodb);
        p_name = findViewById(R.id.editText);
        save = findViewById(R.id.save);
        p_name.addTextChangedListener(profileWatcher);
    }

    private TextWatcher profileWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!p_name.getText().toString().trim().isEmpty()){
                save.setBackgroundResource(R.drawable.message_text_bg);
                save.setEnabled(true);
            }
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void saveClick(View view) {
        Toast.makeText(getApplicationContext(),"Your information is saved",Toast.LENGTH_LONG).show();

        myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(p_name.getText().toString().trim());
        myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone_no").setValue(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());

        SharedPreferences sharedPref = getSharedPreferences("mypref", Context.MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPref.edit();
editor.putString("username",p_name.getText().toString().trim());
editor.apply();

Toast.makeText(this,p_name.getText().toString().trim(),Toast.LENGTH_LONG).show();
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
