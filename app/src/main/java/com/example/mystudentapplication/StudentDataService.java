package com.example.mystudentapplication;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDataService {

    public static final String QUERY_FOR_EXAM_SCHEDULE_BY_EMAIL ="http://fashfemi-001-site21.htempurl.com/api/Exam/byEmail?email=";
    Context context;

    public StudentDataService(Context context) {
        this.context = context;
    }
    public interface ScheduleByEmailResponse{
        void onError(String message);

        void onResponse(List<StudentReportModel> studentReportModel);
    }

    public void getStudentScheduleByEmail(String email, ScheduleByEmailResponse scheduleByEmailResponse) {
        List<StudentReportModel> report = new ArrayList<>();

        String url = QUERY_FOR_EXAM_SCHEDULE_BY_EMAIL + email;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject exam = response.getJSONObject("byEmail");
                    StudentReportModel examSchedule = new StudentReportModel();



                    JSONObject exam_Schedule = (JSONObject) exam.get("byEmail");
                    examSchedule.setFirstName(exam_Schedule.getString("firstName"));
                    examSchedule.setLastName(exam_Schedule.getString("lastName"));
                    examSchedule.setEmailAddress(exam_Schedule.getString("emailAddress"));
                    examSchedule.setDateCreated(exam_Schedule.getString("dateCreated"));
                    examSchedule.setExamDate(exam_Schedule.getString("subjects"));
                    examSchedule.setExamVenue(exam_Schedule.getString("examVenue"));
                    examSchedule.setSubjects(exam_Schedule.getString("subjects"));
                    report.add(examSchedule);

                    scheduleByEmailResponse.onResponse(report);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show();

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }
}
