package com.example.prediccionmedicaj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class LoginMenu extends AppCompatActivity {

    private EditText editText_userMail, editText_userPass;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);
        Objects.requireNonNull(getSupportActionBar()).hide();
        editText_userMail  = findViewById(R.id.editText_userMail);
        editText_userPass = findViewById(R.id.editText_userPass);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(viewButton->{
            Intent myIntent = new Intent(getBaseContext(), MainMenuActivity.class);
            startActivity(myIntent);
        });
    }
}