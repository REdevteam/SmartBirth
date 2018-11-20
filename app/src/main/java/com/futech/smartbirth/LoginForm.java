package com.futech.smartbirth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void Register(View view) {
        Intent intent = new Intent(LoginForm.this, RegisterForm.class);
        startActivity(intent);
    }
}
