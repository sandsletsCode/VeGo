package com.example.vego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        TextView btn1=(TextView) findViewById(R.id.NavigateBtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,GoogleMapsPage.class));
            }
        });
        TextView btn2=(TextView) findViewById(R.id.ToDoListBtn);
        btn2.setOnClickListener(v -> startActivity(new Intent(HomePage.this,ToDoList.class)));

        TextView btn3=(TextView) findViewById(R.id.contactsBtn);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,ContactsPage.class));
            }
        });

        TextView btn4=(TextView) findViewById(R.id.backF_homeBtn);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,MainActivity.class));
            }
        });
    }
}