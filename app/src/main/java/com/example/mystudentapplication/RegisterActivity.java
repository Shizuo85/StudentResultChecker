package com.example.mystudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button new_registration_btn;
    EditText fName, lName, email, pword, dept;
    private ProgressBar loadingPB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        new_registration_btn = findViewById(R.id.new_registration_button);
        fName = findViewById(R.id.firstname_edit_text);
        lName = findViewById(R.id.lastname_edit_text);
        email = findViewById(R.id.enter_email_edit_text);
        pword = findViewById(R.id.new_password__edit_text);
        dept = findViewById(R.id.department_edit_text);
        loadingPB = findViewById(R.id.idLoadingPB);


        new_registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fName.getText().toString().isEmpty() && lName.getText().toString().isEmpty())  {
                    Toast.makeText(RegisterActivity.this, "Please enter both  values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postDataUsingVolley(fName.getText().toString(), lName.getText().toString(), email.getText().toString(), pword.getText().toString(), dept.getText().toString());
            }
        });
    }
    private void postDataUsingVolley(String firstName, String lastName, String emailAddress, String password, String department){
                String url = "http://fashfemi-001-site21.htempurl.com/api/SignUp";
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadingPB.setVisibility(View.GONE);
                        fName.setText("");
                        lName.setText("");
                        email.setText("");
                        pword.setText("");
                        dept.setText("");
                        Toast.makeText(RegisterActivity.this,"Registration Successful! ",Toast.LENGTH_SHORT).show();
                        try{
                            JSONObject respObj = new JSONObject(response);
                            String firstName = respObj.getString("firstName");
                            String lastName = respObj.getString("lastName");
                            String emailAddress = respObj.getString("emailAddress");
                            String password = respObj.getString("password");
                            String department = respObj.getString("department");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();

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
                        params.put("password", password);
                        params.put("department", department);

                        // at last we are
                        // returning our params.
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);

                    }
            }



