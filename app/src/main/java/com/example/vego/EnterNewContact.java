package com.example.vego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EnterNewContact extends AppCompatActivity {
    //object for database helper
    DatabaseHelperForContacts dbHelperForContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entering_new_contact_page);
        // Initialize dbHelperForContacts within the onCreate() method
        dbHelperForContacts = new DatabaseHelperForContacts(this);

    }

    public void addContact(View view) {
        //getting values from the edit text
        int id= dbHelperForContacts.getAllContacts().size()+1;
        String name = ((EditText) findViewById(R.id.editTextName)).getText().toString();
        String number = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String nick_name = ((EditText) findViewById(R.id.editTextNickName)).getText().toString();
        String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        String notes = ((EditText) findViewById(R.id.editTextMultiLine)).getText().toString();

        //contact object
        Contact contact = new Contact(id,name,number, nick_name, address, notes);
        long inserted = dbHelperForContacts.addContact(contact);
        if(inserted !=-1){
            Toast.makeText(this, "Contact added successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new  Intent(this,WorkContactList.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
        }


    }
}