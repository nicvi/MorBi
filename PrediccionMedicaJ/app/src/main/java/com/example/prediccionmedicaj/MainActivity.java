package com.example.prediccionmedicaj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button buttonSelectLogin, buttonSelectSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        buttonSelectLogin = (Button) findViewById(R.id.buttonSelectLogin);
        buttonSelectSignIn = (Button) findViewById(R.id.buttonSelectSignIn);
        buttonSelectLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), LoginMenu.class);
                startActivity(myIntent);
            }
        });
        buttonSelectSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // add functionality
            }
        });
    }

}