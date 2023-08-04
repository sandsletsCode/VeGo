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

public class registerPage extends AppCompatActivity {
    EditText username , password , Confirm_password;
    Button register ,signIn;
    DBHelperForRegistration DB;
//login is for the already have ana account button and register for register button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.password);
        Confirm_password = findViewById(R.id.confirmPassword);
        register = findViewById(R.id.registerBtn);
        signIn = findViewById(R.id.already_Have_anAcc);
        DB = new DBHelperForRegistration(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = Confirm_password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(registerPage.this, "All fields are required", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(registerPage.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomePage.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(registerPage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(registerPage.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(registerPage.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });




    }
}