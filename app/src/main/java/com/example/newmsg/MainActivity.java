package com.example.newmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public FirebaseUser mUser;
    public FirebaseDatabase mDatabase;
    public DatabaseReference mDatabaseReference;
    public TextView t1,t2;
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn.findViewById(R.id.button1);
        t1.findViewById(R.id.textView);
        t2.findViewById(R.id.textView2);

    }

    @Override
    protected void onStart() {
        super.onStart();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,ChatActivity.class);
                startActivity(it);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
    }
}