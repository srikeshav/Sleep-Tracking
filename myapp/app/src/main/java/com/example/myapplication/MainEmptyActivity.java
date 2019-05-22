package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent;
        if(SaveSharedPreferences.getLoggedStatus(getApplicationContext())){
           intent = new Intent(getApplicationContext(), MainActivity.class);

        }
        else {
            intent = new Intent(getApplicationContext(), UserActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
