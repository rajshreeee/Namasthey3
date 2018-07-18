package com.example.rajshree.namasthey3;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private RecyclerView recyclerView2;
    private RecyclerView.Adapter adapter2;

    DatabaseReference myRef = database.getReference().child("chat");
    DatabaseReference myRef2;
    DatabaseReference mMessageDatabase;
    DatabaseReference mUsersDatabase;
    private FirebaseAuth mAuth;
    private String userId;
    private FirebaseRecyclerAdapter<Conversation, Con2> mRVAdapter;
    private Context context;
    private String name;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       ViewGroup rootView2 = (ViewGroup) inflater.inflate(R.layout.fragment_blank, container, false);


        recyclerView2 = (RecyclerView) rootView2.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

        SharedPreferences sharedPref = getContext().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        name= sharedPref.getString("username","");
    myRef2 = myRef.child(name);

mMessageDatabase = FirebaseDatabase.getInstance().getReference().child("messages").child(name);

        setRetainInstance(true);
        return rootView2;

    }


    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerOptions personsOptions2 = new FirebaseRecyclerOptions.Builder<Conversation>().setQuery(myRef2, Conversation.class).build();
        mRVAdapter = new FirebaseRecyclerAdapter<Conversation, BlankFragment.Con2>(personsOptions2) {
            @Override
            protected void onBindViewHolder(@NonNull final BlankFragment.Con2 holder, int position, @NonNull Conversation model) {
             final String list_user_name = getRef(position).getKey();
              final String username2 = getRef(position).getKey();
             holder.setName(list_user_name);

                Query lastMsgQuery = mMessageDatabase.child(list_user_name).limitToLast(1);

                lastMsgQuery.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String data = dataSnapshot.child("message").getValue().toString();
                        holder.setMessage(data);

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
            public BlankFragment.Con2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.blank_row, parent, false);

                return new BlankFragment.Con2(view);
            }
        };

        recyclerView2.setAdapter(mRVAdapter);

        mRVAdapter.startListening();


    }

    @Override
    public void onStop() {
        super.onStop();
        mRVAdapter.stopListening();


    }



    public static class Con2 extends RecyclerView.ViewHolder{
        View mView;


        public Con2(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setMessage(String message){
            TextView textView = (TextView) mView.findViewById(R.id.textViewHead2);
            textView.setText(message);
        }
        public void setName(String name){
            TextView textView = (TextView) mView.findViewById(R.id.textViewName2);
            textView.setText(name);
        }
    }

}




