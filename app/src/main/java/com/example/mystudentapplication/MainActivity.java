package com.example.mystudentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button login_button;
    ImageButton fingerPrint_butn;
    TextView register_button;
    EditText EmailAdd, psWord;
    TextView navHeader_name, navHeader_email, forgot_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_fragment);
        fingerPrint_butn = (ImageButton) findViewById(R.id.fingerprint_btn);
        fingerPrint_butn.setImageResource(R.drawable.ic_biometric);
        EmailAdd = findViewById(R.id.email_edit_text);
        psWord = findViewById(R.id.password_edit_text);
        navHeader_email = findViewById(R.id.nav_header_email);
        navHeader_name = findViewById(R.id.nav_header_text);

        forgot_pwd = findViewById(R.id.frgt_password);
        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(i);
            }
        });


        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EmailAdd.getText().toString().isEmpty() && psWord.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both values", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(MainActivity.this, UserActivity.class);
                startActivity(i);
            }
        });
        register_button = findViewById(R.id.register_tv);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        BiometricPrompt prompt = new BiometricPrompt(this,
                ContextCompat.getMainExecutor(this),
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(MainActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);

                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
        BiometricPrompt.PromptInfo info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric")
                .setNegativeButtonText("Cancel")
                .build();

        fingerPrint_butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt.authenticate(info);
            }
        });
    }
}






