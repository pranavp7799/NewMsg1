package com.example.newmsg;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    public Activity context;
    private List<Chat> mChatlist;
    FirebaseUser currentUser;


    ChatAdapter(Activity context,List<Chat> mChatlist){

        this.context=context;
        this.mChatlist=mChatlist;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==1) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_chat_right, parent, false);
            //if sender==current user, set layout gravity right, else left;

            return new ChatViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_chat_left, parent, false);
            //if sender==current user, set layout gravity right, else left;
            TextView sndr;
            return new ChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {

        final Chat chat=mChatlist.get(position);
        holder.msg.setText(chat.getMessage());
        holder.name.setText(chat.getSender());

    }

    @Override
    public int getItemCount() {
        return mChatlist.size();
    }

    public  class ChatViewHolder extends RecyclerView.ViewHolder{
        public TextView name,msg;
        public ChatViewHolder(@NonNull View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.sender);
            msg=itemView.findViewById(R.id.msg);

        }
    }

    public int getItemViewType(int position){
        if(mChatlist.get(position).getSender().equals(currentUser.getEmail())){
            return 1;
        } else {
            return 0;
        }
    }
}