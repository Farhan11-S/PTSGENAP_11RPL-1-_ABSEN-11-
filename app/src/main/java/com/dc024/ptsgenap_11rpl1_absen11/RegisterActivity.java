package com.dc024.ptsgenap_11rpl1_absen11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.HashMap;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText u_username, u_password, u_fullname;
    private boolean isFormFilled = false;
    private CircularProgressButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        u_password = findViewById(R.id.etPassword);
        u_username = findViewById(R.id.etUsername);
        u_fullname = findViewById(R.id.etFullName);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {
            }

            @Override
            public void onClick(View v) {
                isFormFilled = true;
                final String hp = u_username.getText().toString();
                final String password = u_password.getText().toString();
                final String fullname = u_fullname.getText().toString();

                if (hp.isEmpty() || password.isEmpty() || fullname.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "MAKE SURE FORM IS FILLED", Toast.LENGTH_SHORT).show();
                    isFormFilled = false;
                }
                if (isFormFilled) {
                    btnRegister.startAnimation();
                    HashMap<String, String> body = new HashMap<>();
                    body.put("username", hp);
                    body.put("password", password);
                    body.put("fullname", fullname);
                    AndroidNetworking.post("http://192.168.43.183/ptsapi/register.php")
                            .addBodyParameter(body)
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("GZS", "respon : " + response);

                                    String status = response.optString("STATUS");
                                    String message = response.optString("MESSAGE");
                                    if (status.equalsIgnoreCase("SUCCESS")) {
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                        finish();
                                        finishAffinity();
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }

                                    btnRegister.revertAnimation();
                                }

                                @Override
                                public void onError(ANError anError) {
                                    btnRegister.revertAnimation();
                                    Toast.makeText(RegisterActivity.this, "ERROR LUR", Toast.LENGTH_SHORT).show();
                                    Log.d("GZS", "onError: " + anError.getErrorBody(    ));
                                    Log.d("GZS", "onError: " + anError.getLocalizedMessage());
                                    Log.d("GZS", "onError: " + anError.getErrorDetail());
                                    Log.d("GZS", "onError: " + anError.getResponse());
                                    Log.d("GZS  ", "onError: " + anError.getErrorCode());
                                }
                            });
                }
            }
        });
    }
}
