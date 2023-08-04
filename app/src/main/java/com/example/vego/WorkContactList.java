package com.example.vego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vego.Adapter.workContactAdapter;

import java.util.ArrayList;

public class WorkContactList extends AppCompatActivity {

    DatabaseHelperForContacts dbHelperForContacts;
    ArrayList<Contact> contactArrayList = new ArrayList<>();
    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_contact_list);
        dbHelperForContacts = new DatabaseHelperForContacts(this);
        //loading the list
        contactArrayList = dbHelperForContacts.getAllContacts();
        Log.i("TAG", "contacts : " + contactArrayList.toString());
        textView = findViewById(R.id.tvMessage);
        listView = findViewById(R.id.listView);
        if(contactArrayList.size()>0){
            textView.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }
        workContactAdapter adapter = new workContactAdapter(this,contactArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = (Contact) listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),EditContact.class);
                intent.putExtra("id",contact.getId());
                startActivity(intent);

            }
        });

    }

    public void goToEnterNewContact(View view) {
        Intent intent = new Intent(this, EnterNewContact.class);
        startActivity(intent);
    }
}
