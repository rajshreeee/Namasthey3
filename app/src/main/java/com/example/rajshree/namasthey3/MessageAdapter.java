package com.example.rajshree.namasthey3;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by rajshree on 1/29/18.
 */

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private List <Messages> mMessagelist;
    private Context context;
    private FirebaseAuth mAuth;
    private String userId;


    public MessageAdapter(List<Messages> mMessagelist) {
        this.mMessagelist = mMessagelist;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_row, parent, false);
        return new ViewHolder(v);
        //SharedPreferences sharedPref = get
        //name= sharedPref.getString("username","");


    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position)   {

        mAuth = FirebaseAuth.getInstance();

       Messages c =mMessagelist.get(position);

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

     String from_user = c.getFrom();

        if(from_user.equals(userId)){
        holder.messageText.setBackgroundResource(R.drawable.message_text_bg2);
        holder.messageText.setTextColor(Color.BLACK);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        holder.messageText.setLayoutParams(lp);

        }else{

            holder.messageText.setBackgroundResource(R.drawable.message_text_bg);
            holder.messageText.setTextColor(Color.BLACK);


        }
       holder.messageText.setText(c.getMessage());


    }

    @Override
    public int getItemCount() {
        return mMessagelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView messageText;

        public ImageView imageView;

public RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);

           messageText = (TextView) itemView.findViewById(R.id.msgtext);
rl = itemView.findViewById(R.id.messagelayout);
         

        }


    }
}