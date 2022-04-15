package com.example.roomdatabasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

// Initialize variable


    FloatingActionButton btnAddContact;
    RecyclerView recyclerView;

    List<MainData> dataList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddContact = findViewById( R.id.btnAddContact );
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
            }
        });

        //Assign variable

        recyclerView=findViewById(R.id.recycler_view);

        // initialize database
        database=RoomDB.getInstance(this);

        // store database value in data list

        dataList=database.mainDao().getAll();

        //Initialize linear layout manager
        linearLayoutManager=new LinearLayoutManager(this);

        // Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize adapter
        adapter=new MainAdapter(MainActivity.this,dataList);

        // set adapter

        recyclerView.setAdapter(adapter);

    }
}
