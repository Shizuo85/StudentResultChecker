package com.example.mystudentapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExamActivity extends AppCompatActivity {

    Button register_for_exam, check_exam_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        // calling the action bar
        register_for_exam = findViewById(R.id.register_for_exam);
        check_exam_details = findViewById(R.id.check_exam_details);
        register_for_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamActivity.this, ExamRegistration.class);
                startActivity(intent);

            }
        });

        check_exam_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamActivity.this, CheckExamDetails.class);
                startActivity(intent);
            }
        });

    }

}