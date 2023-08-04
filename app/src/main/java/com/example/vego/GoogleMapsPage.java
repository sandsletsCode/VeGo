package com.example.vego;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class GoogleMapsPage extends AppCompatActivity {
    private static final String TAG = "GoogleMapsPage";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps_page);

        if (isServicesOK()){
            init();
        }
    }
    private  void init(){
        Button btnMap=(Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoogleMapsPage.this,MapActivity.class);
                startActivity(intent);
            }
        });

    }
    public  boolean isServicesOK(){
        Log.d(TAG,"isServicesOK: checking google services version");
        int available =GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(GoogleMapsPage.this);

        if (available == ConnectionResult.SUCCESS){
            //all is fine
            Log.d(TAG,"isServicesOK: checking google play are working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //error occurred but we can resolve it
            Log.d(TAG,"isServiceOK: an error occurred but we can fix it");
            Dialog dialog= GoogleApiAvailability.getInstance().getErrorDialog(GoogleMapsPage.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();

        }else{
            Toast.makeText(this, "we cant make map request", Toast.LENGTH_SHORT).show();
        }
        return false;

    }

}