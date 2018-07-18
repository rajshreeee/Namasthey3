package com.example.rajshree.namasthey3;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseListOptions;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;


public class LstFragment extends Fragment{



    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton addFab;

    DatabaseReference myRef = database.getReference().child("Friends").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    private FirebaseAuth mAuth;
    private String userId;
    private FirebaseRecyclerAdapter<Contact, Con> mPeopleRVAdapter;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.contact_fragment,container,false);

        addFab = rootView.findViewById(R.id.addFab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(getContext(),addContact.class ));
            }
        });
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();



       setRetainInstance(true);
        return rootView;


    }

    @Override
    public void onStart() {
        super.onStart();

      /*  FirebaseRecyclerAdapter<Contact, ConViewHolder> ContactRecyclerViewAdapter = new FirebaseRecyclerAdapter<Contact, ConViewHolder>
                ( Contact.class, R.layout.contactrow, ConViewHolder.class, myRef) {



        }
    }*/
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<Contact>().setQuery(myRef, Contact.class).build();
        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Contact, Con>(personsOptions) {
            @Override
            protected void onBindViewHolder(@NonNull Con holder, int position, @NonNull Contact model) {
                holder.setDate(model.getDate());
                holder.setName(model.getName());
                final String username2 = getRef(position).getKey();

holder.mView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("contactusername",username2);
        getActivity().startActivity(intent);
    }
});


            }

            @NonNull
            @Override
            public Con onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.contactrow, parent, false);

                return new Con(view);
            }
        };

        recyclerView.setAdapter(mPeopleRVAdapter);

        mPeopleRVAdapter.startListening();


    }

    @Override
    public void onStop() {
        super.onStop();
        mPeopleRVAdapter.stopListening();


    }

    public LstFragment() {
    }

public static class Con extends RecyclerView.ViewHolder{
        View mView;


    public Con(View itemView) {
        super(itemView);
        mView = itemView;
    }
    public void setDate(String date){
        TextView textView = (TextView) mView.findViewById(R.id.textViewHead);
        textView.setText(date);
    }
    public void setName(String name){
        TextView textView = (TextView) mView.findViewById(R.id.textViewName);
        textView.setText(name);
    }
}

}


