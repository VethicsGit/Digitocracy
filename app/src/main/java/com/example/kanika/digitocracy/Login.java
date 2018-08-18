package com.example.kanika.digitocracy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.login.LoginResponse;
import com.example.kanika.digitocracy.APIResponse.login.Responsee;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    EditText email, password;
    Button login;
    TextView forogot, signup;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        forogot = findViewById(R.id.forgot);
        signup = findViewById(R.id.signup);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        /*Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/brandonmed.otf");
        login.setTypeface(custom_font);*/

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        forogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /* String em=email.getText().toString();
             String pw=password.getText().toString();*/
                if (password.getText().toString().equals("")) {
                    password.setError("Please fill something");
                } else if (email.getText().toString().equals("")) {
                    email.setError("Please fill something");
                } else if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    final ProgressDialog mProgressDialog = new ProgressDialog(Login.this);
                    mProgressDialog.setIndeterminate(false);
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.setMessage("Please wait...");
                    mProgressDialog.show();

                    API apiService = APIS.getRetrofit().create(API.class);
                    Call<LoginResponse> call1 = apiService.Sigin(email.getText().toString(), password.getText().toString());

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
                                    SharedPreferences preferences=getSharedPreferences("LoginStatus",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=preferences.edit();
                                    editor.putString("token",responsee.getToken());
                                    editor.putString("screen_code",responsee.getScreenCode());
                                    editor.putString("user_id",responsee.getUserId());
                                    editor.putString("name",responsee.getName());
                                    editor.putString("gender",responsee.getGender());
                                    editor.putString("pass",password.getText().toString());
                                    editor.putString("email",responsee.getEmail()).apply();



                                    Intent intent = new Intent(getApplicationContext(), Location.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
                                }
                                mProgressDialog.dismiss();

                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(Login.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                        }
                    });
                }

            /* cursor=db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_3 + " =? AND " + DatabaseHelper.COL_4 + " =? ",new String[]{em,pw});
             if (cursor !=null){
                 Log.e("message","login");
                 if (cursor.getCount() > 0){

                     Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                     Intent intent = new Intent(Login.this,Location.class);
                     startActivity(intent);
                 }else {

                     Intent intent = new Intent(Login.this,Location.class);
                     startActivity(intent);
                     Toast.makeText(getApplicationContext(),"Login Error",Toast.LENGTH_LONG).show();
                 }
             }*/
            }
        });

    }

}
