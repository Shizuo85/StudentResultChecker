package com.example.mystudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ExamRegistration extends AppCompatActivity {

    EditText examReg_firstName, examReg_lastName, examReg_email, examReg_sub1, examReg_sub2, examReg_sub3, examReg_sub4, examReg_date;
    Button save_examReg;
    private ProgressBar loading1PB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_registration);
        Intent intent = getIntent();

        examReg_firstName = findViewById(R.id.examReg_firstname_edit_text);
        examReg_lastName = findViewById(R.id.examReg_lastname_edit_text);
        examReg_email = findViewById(R.id.examReg_enter_email_edit_text);
        examReg_sub1 = findViewById(R.id.examReg_subjectOne__edit_text);
        examReg_sub2 = findViewById(R.id.examReg_SubjectTwo_edit_text);
        examReg_sub3 = findViewById(R.id.examReg_SubjectThree_edit_text);
        examReg_sub4 = findViewById(R.id.examReg_SubjectFour_edit_text);
        examReg_date = findViewById(R.id.examReg_ExamDate_edit_text);
        save_examReg = findViewById(R.id.save_button);
        loading1PB = findViewById(R.id.idLoadingPB);

        save_examReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LOG_TAG", "Save Button Clicked");
                if (examReg_firstName.getText().toString().isEmpty() && examReg_lastName.getText().toString().isEmpty()) {
                    Toast.makeText(ExamRegistration.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }
                postDataUsingVolley(examReg_firstName.getText().toString(), examReg_lastName.getText().toString(), examReg_email.getText().toString(), examReg_sub1.getText().toString(), examReg_sub2.getText().toString(), examReg_sub3.getText().toString(), examReg_sub4.getText().toString(), examReg_date.getText().toString());

            }
        });
    }

    private void postDataUsingVolley(String firstName, String lastName, String emailAddress, String subjectOne, String subjectTwo, String subjectThree, String subjectFour, String examDate) {
        String url = "http://fashfemi-001-site21.htempurl.com/api/Exam";

        RequestQueue queue = Volley.newRequestQueue(ExamRegistration.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                examReg_firstName.setText("");
                examReg_lastName.setText("");
                examReg_email.setText("");
                examReg_sub1.setText("");
                examReg_sub2.setText("");
                examReg_sub3.setText("");
                examReg_sub4.setText("");
                examReg_date.setText("");
                Toast.makeText(ExamRegistration.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);
                    String firstName = respObj.getString("firstName");
                    String lastName = respObj.getString("lastName");
                    String emailAddress = respObj.getString("emailAddress");
                    String subjectOne = respObj.getString("subjectOne");
                    String subjectTwo = respObj.getString("subjectTwo");
                    String subjectThree = respObj.getString("subjectThree");
                    String subjectFour = respObj.getString("subjectFour");
                    String examDate = respObj.getString("examDate");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamRegistration.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("emailAddress", emailAddress);
                params.put("subjectOne", subjectOne);
                params.put("subjectTwo", subjectTwo);
                params.put("subjectThree", subjectThree);
                params.put("subjectFour", subjectFour);
                params.put("examDate", examDate);

                // at last we are
                // returning our params.
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}