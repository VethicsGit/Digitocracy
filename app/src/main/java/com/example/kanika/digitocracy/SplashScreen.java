package com.example.kanika.digitocracy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList;
import com.example.kanika.digitocracy.APIResponse.login.LoginResponse;
import com.example.kanika.digitocracy.APIResponse.login.Responsee;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    ImageView image;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        image=findViewById(R.id.image);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final ProgressDialog mProgressDialog = new ProgressDialog(SplashScreen.this);
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage("Please wait...");
                mProgressDialog.show();
                final SharedPreferences preferences=getSharedPreferences("LoginStatus",MODE_PRIVATE);
                API apiService = APIS.getRetrofit().create(API.class);
                Call<LoginResponse> call1 = apiService.Sigin(preferences.getString("email",""),preferences.getString("pass",""));

                call1.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                        Log.e("_response_login", "login" + response.body().toString());

                        LoginResponse res = response.body();
                        List<Responsee> list_response = res.getResponse();

                        for (int i = 0; i < list_response.size(); i++) {

                            Responsee responsee = list_response.get(i);
                            Log.e("resnse", responsee.getResponseMsg());

                            if (responsee.getStatus().equals("true")) {

                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("token",responsee.getToken());
                                editor.putString("screen_code",responsee.getScreenCode());
                                editor.putString("user_id",responsee.getUserId());
                                editor.putString("name",responsee.getName());
                                editor.putString("gender",responsee.getGender());
                                editor.putString("email",responsee.getEmail()).apply();



                                Intent intent = new Intent(getApplicationContext(), PollCategories.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            mProgressDialog.dismiss();

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(SplashScreen.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                    }
                });
              /*  Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);*/


                finish();

            }
        },SPLASH_TIME_OUT);

    }
}
