package com.example.vego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    DBHelperForRegistration DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.enter_username);
        password = findViewById(R.id.enter_Password);
        login = findViewById(R.id.Login_button);
        DB = new DBHelperForRegistration(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass =DB.checkusernamepassword(user, pass);
                    if(checkuserpass){
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomePage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        TextView btn1 =(TextView) findViewById(R.id.textViewSignup);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,registerPage.class));
            }
        });

        TextView btn2 =(TextView) findViewById(R.id.LoginGoogle);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HomePage.class));
            }
        });



    }
}