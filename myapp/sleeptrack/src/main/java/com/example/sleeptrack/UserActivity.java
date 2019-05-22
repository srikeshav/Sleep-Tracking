package com.example.sleeptrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {
    Button submitBtn;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        username = findViewById(R.id.username);
        submitBtn = findViewById(R.id.login);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Make form visible

                SaveSharedPreferences.setLoggedIn(getApplicationContext(),true,username.getText().toString());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
