package com.example.mystudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckExamDetails extends AppCompatActivity {

    EditText email_examDetails;
    Button check_examDetails;
    ListView listV;
    TextView examVenue_tv, dateCreated_tv, examDate_tv, subjects_tv;
    private ProgressBar proBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_exam_details);
        final StudentDataService studentDataService = new StudentDataService(CheckExamDetails.this);

        email_examDetails = findViewById(R.id.email_view_schedule);
        check_examDetails = findViewById(R.id.view_exam_schedule);
        examVenue_tv = findViewById(R.id.textView_examVenue);
        dateCreated_tv = findViewById(R.id.textView_dateCreated);
        examDate_tv = findViewById(R.id.textView_examDate);
        subjects_tv = findViewById(R.id.textView_subjects);
        proBar = findViewById(R.id.pBar_checkSchedule);

        check_examDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email_examDetails.getText().toString().isEmpty()){
                    Toast.makeText(CheckExamDetails.this, "field can't be empty", Toast.LENGTH_SHORT);
                    return;
                }

                RequestQueue queue = Volley.newRequestQueue(CheckExamDetails.this);
                String url = "http://fashfemi-001-site21.htempurl.com/api/Exam/byEmail?email=" + email_examDetails.getText().toString();
                proBar.setVisibility(View.VISIBLE);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        proBar.setVisibility(View.GONE);
                        String examVenue ="";
                        String examDate = "";
                        String subjects = "";
                        String dateCreated = "";

                        try {
                             examVenue = response.getString("examVenue");
                             examDate = response.getString("examDate");
                             subjects = response.getString("subjects");
                             dateCreated = response.getString("dateCreated");
                                                         examVenue_tv.setText("Exam Venue : " + examVenue);
                            examDate_tv.setText("Exam Date : " + examDate);
                            subjects_tv.setText("Subjects are: " + subjects);
                            dateCreated_tv.setText("Date Created is: " + dateCreated);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        proBar.setVisibility(View.GONE);
                        Toast.makeText(CheckExamDetails.this, "Error occurred!", Toast.LENGTH_SHORT).show();

                    }
                });
                queue.add(jsonObjectRequest);

            }
        });

    }
}
