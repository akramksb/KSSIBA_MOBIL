package com.example.roomdatabasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {

    private Button btnEdit;
    private FloatingActionButton btnHome;
    private EditText firstName, lastName, job, phone, email;

    MainData current_contact;
    RoomDB database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);

        database=RoomDB.getInstance(this);

        firstName = findViewById( R.id.editTextFistName2 );
        lastName = findViewById( R.id.editTextLastName2 );
        job = findViewById( R.id.editTextJob2);
        phone = findViewById( R.id.editTextPhone2 );
        email = findViewById( R.id.editTextEmail2 );

        btnEdit = findViewById( R.id.btnEdit );
        btnHome = findViewById( R.id.btnHome );

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        current_contact = database.mainDao().findByID(id);

        firstName.setText( current_contact.getFirst_name() );
        lastName.setText( current_contact.getLast_name() );
        job.setText( current_contact.getJob() );
        phone.setText( current_contact.getPhone() );
        email.setText( current_contact.getEmail() );



        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get string from edit text
                String sFirstName = firstName.getText().toString().trim();
                String sLastName = lastName.getText().toString().trim();
                String sJob = job.getText().toString().trim();
                String sPhone = phone.getText().toString().trim();
                String sEmail = email.getText().toString().trim();

                // check condition
                if((!sFirstName.equals("") || !sLastName.equals("")) && !phone.equals("") )
                 {
                    // when text is not empty
                    // initialize main data


                    //Set text on main data
                     current_contact.setFirst_name( sFirstName );
                     current_contact.setLast_name( sLastName );
                     current_contact.setJob( sJob );
                     current_contact.setPhone( sPhone );
                     current_contact.setEmail( sEmail );
                    //Insert text in database
                    database.mainDao().update(current_contact);

                     Intent i = new Intent(getApplicationContext(), MainActivity.class);
                     startActivity(i);
                }
            }
        });


    }
}
