package com.example.kanika.digitocracy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.ForgotPass.ForgotpassResponse;
import com.example.kanika.digitocracy.APIResponse.login.LoginResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    EditText forgot_pass_email;
    Button forgotpass_reset_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgot_pass_email = findViewById(R.id.forgot_pass_email);
        forgotpass_reset_pass = findViewById(R.id.forgotpass_reset_pass);

        forgotpass_reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(forgot_pass_email.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(forgot_pass_email.getText().toString()).matches()) {
                    final ProgressDialog mProgressDialog = new ProgressDialog(ForgotPassword.this);
                    mProgressDialog.setIndeterminate(false);
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.setMessage("Please wait...");
                    mProgressDialog.show();

                    API apiService = APIS.getRetrofit().create(API.class);

                    Call<ForgotpassResponse> call1 = apiService.Forgot_Password(forgot_pass_email.getText().toString());
                    call1.enqueue(new Callback<ForgotpassResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<ForgotpassResponse> call, Response<ForgotpassResponse> response) {
                            ForgotpassResponse response1 = response.body();
                          List<com.example.kanika.digitocracy.APIResponse.ForgotPass.Response> resList= response1.getResponse();
                            for (int i=0;i<resList.size();i++){
                                com.example.kanika.digitocracy.APIResponse.ForgotPass.Response resForgot=resList.get(i);

                                if (resForgot.getStatus().equals("true")){
                                    Toast.makeText(ForgotPassword.this, resForgot.getResponseMsg(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else Toast.makeText(ForgotPassword.this, resForgot.getResponseMsg(), Toast.LENGTH_SHORT).show();

                                mProgressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ForgotpassResponse> call, Throwable t) {
                            mProgressDialog.dismiss();
                            Toast.makeText(ForgotPassword.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    forgot_pass_email.setError("Please enter valid email address.");
                }
            }
        });

    }
}
