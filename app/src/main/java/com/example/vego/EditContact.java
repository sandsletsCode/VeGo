package com.example.vego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {
    DatabaseHelperForContacts dbHelperForContacts;
    int id;
    Contact contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        dbHelperForContacts = new DatabaseHelperForContacts(this);
        //gets the if from intent extra
        id=getIntent().getIntExtra("id",0);
        contact= dbHelperForContacts.getContactInfor(id);
        initContactDetails();

    }

    private void initContactDetails() {
        //getting the references of editText input view
        EditText name  = findViewById(R.id.editTextName);
        EditText number  = findViewById(R.id.editTextPhone);
        EditText nickname  = findViewById(R.id.editTextNickName);
        EditText address  = findViewById(R.id.editTextAddress);
        EditText notes  = findViewById(R.id.editTextMultiLine);
        contact = dbHelperForContacts.getContactInfor(id);
        name.setText(contact.getName());
        number.setText(contact.getNumber());
        nickname.setText(contact.getNick_name());
        address.setText(contact.getAddress());
        notes.setText(contact.getNotes());
        Button saveButton = findViewById(R.id.btn_save);
        saveButton.setVisibility(View.GONE);

    }

    public void saveContact(View view) {
        String name = ((EditText) findViewById(R.id.editTextName)).getText().toString();
        String number = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String nick_name = ((EditText) findViewById(R.id.editTextNickName)).getText().toString();
        String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        String notes = ((EditText) findViewById(R.id.editTextMultiLine)).getText().toString();

        //contact object
        Contact contact = new Contact(id,name,number, nick_name, address, notes);
        dbHelperForContacts.onUpgrade(contact);
        if(dbHelperForContacts.onUpgrade(contact)==-1){
            Toast.makeText(this, "something went wrong!", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(getApplicationContext(), "contact details saved", Toast.LENGTH_SHORT).show();
            Intent intent = new  Intent(this,WorkContactList.class);
            startActivity(intent);
        }

    }
}