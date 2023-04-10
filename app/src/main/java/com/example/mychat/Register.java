package com.example.mychat;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mychat.R;
import com.example.mychat.chat;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class Register extends AppCompatActivity {
    EditText usrnm;
    Button b1;
    chat chat1;
    ArrayList<String> msglist;
    ArrayAdapter<String> adapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usrnm = findViewById(R.id.username);
        b1 = findViewById(R.id.button3);
        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://mychat-342bb-default-rtdb.firebaseio.com/chat");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat chat = new chat("arushi", usrnm.getText().toString());
                ref.push().setValue(chat);
                usrnm.setText("");
            }

        });
        chat1 = new chat();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mref = firebaseDatabase.getReference("msgs");
        msglist = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.id.username, R.id.textView, msglist);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                msglist.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    chat1 = ds.getValue(chat.class);
                    msglist.add(chat1.getUsername() + ":\n" + "  " + chat1.getPassword());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

  }
}