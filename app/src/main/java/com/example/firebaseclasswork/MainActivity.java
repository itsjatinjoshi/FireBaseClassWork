package com.example.firebaseclasswork;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvName;
    ArrayList<Github> vals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        vals = new ArrayList<>();

        tvName = findViewById(R.id.tvName);

        FirebaseApp.initializeApp(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("items");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String nm = dataSnapshot.child("0").child("name").getValue(String.class);
                String nm = dataSnapshot.child("0").child("name").getValue().toString();
                tvName.setText(nm);

                Iterable<DataSnapshot> childs = dataSnapshot.getChildren();

                for(DataSnapshot snap : childs)
                {
                    //System.out.println("Name : "+snap.child("name").getValue().toString());

                    Github git = snap.getValue(Github.class);

                    //  System.out.println(git.getId()+" "+git.getFull_name()+" "+git.getWatchers());

                    vals.add(git);


                }


                System.out.println("Arraylist Size :"+vals.size());
                for (int i=0;i<vals.size();i++)
                {

                    System.out.println(vals.get(i).getId()+" "+vals.get(i).getFull_name()+" "+vals.get(i).getWatchers());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Write a message to the database
      //  FirebaseDatabase database = FirebaseDatabase.getInstance();
      //  DatabaseReference myRef = database.getReference("message");

      //  myRef.setValue("Hello, World!");
    }
}
