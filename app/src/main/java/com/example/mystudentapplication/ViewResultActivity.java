package com.example.mystudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ViewResultActivity extends AppCompatActivity {

    EditText email_result, password_result;
    Button check_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);


        email_result = findViewById(R.id.checkResult_email);
        password_result = findViewById(R.id.checkResult_password);
        check_result = findViewById(R.id.checkResult_button);
    }
}