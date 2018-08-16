package com.example.kanika.digitocracy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kanika.digitocracy.login.Responsee;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
        EditText email,password;
        Button login;
        TextView forogot,signup;
        Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        forogot=findViewById(R.id.forgot);
        signup=findViewById(R.id.signup);
        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /* String em=email.getText().toString();
             String pw=password.getText().toString();*/

                API apiService = APIS.getRetrofit().create(API.class);
                Call<Responsee> call1 = apiService.Sigin(email.getText().toString(),password.getText().toString());

                call1.enqueue(new Callback<Responsee>() {
                    @Override
                    public void onResponse(@NonNull Call<Responsee> call, @NonNull Response<Responsee> response) {
                        Log.e("_response_login","login"+response.body().toString());




                        if(response.isSuccessful()){
                            Responsee responsee=response.body();
                            Log.e("resnse",responsee.getStatus());

                            if (responsee.getStatus().equals("true")) {
                                Intent intent = new Intent(getApplicationContext(),Location.class);
                                startActivity(intent);
                            }/*else{
                                Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_LONG).show();
                            }*/

                            }

                    }


                    @Override
                    public void onFailure(Call<Responsee> call, Throwable t) {

                    }
                });


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
