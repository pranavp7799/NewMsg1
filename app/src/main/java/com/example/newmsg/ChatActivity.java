package com.example.newmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    FirebaseUser currentUser;
    DatabaseReference mDatabaseReference;
    public TextView post,mTitle;
    public EditText msg_edit;
    public String mesg,id,curr_sender,curr_receiver,curr_receiver_email;
    public String hostId;
    public String sendername;

    List<Chat> mChatlist;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        currentUser= FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Chats");

        recyclerView=findViewById(R.id.msg_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        msg_edit=findViewById(R.id.msg_edit);
        post=findViewById(R.id.post_text);
        hostId="JyCWEwmm5Lg09QHGzRWvq20IRYJ2";
        //String title = getIntent().getStringExtra("Title");
        sendername="jha@gmail.com";
        mTitle=findViewById(R.id.activity_comment_text_view_title);
        mTitle.setText(sendername);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        hostId="JyCWEwmm5Lg09QHGzRWvq20IRYJ2";
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChatlist.clear();
                for (DataSnapshot ChatSnap : dataSnapshot.getChildren()) {
                    Chat chat = ChatSnap.getValue(Chat.class);
                    //Add only if sender or receiver
                    if ((chat.getSender().equals(currentUser.getUid()) && chat.getReceiver().equals(hostId)) ||
                            (chat.getReceiver().equals(currentUser.getUid()) && chat.getSender().equals(hostId))) {
                        mChatlist.add(chat);
                    }
                }
                ChatAdapter chatAdapter = new ChatAdapter(ChatActivity.this, mChatlist);
                recyclerView.setAdapter(chatAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void sendMessage(){
        if(!msg_edit.getText().toString().trim().equals("")) {
            id = mDatabaseReference.push().getKey();
            curr_sender=currentUser.getEmail();
            //curr_receiver_email=getIntent().getStringExtra("HostEmail");
            curr_receiver_email="jha@gmail.com";
            mesg=msg_edit.getText().toString().trim();
            Chat chat = new Chat(curr_sender, curr_receiver_email, mesg);
            mDatabaseReference.child(id).setValue(chat)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            msg_edit.setText("");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}