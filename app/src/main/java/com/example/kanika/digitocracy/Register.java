package com.example.kanika.digitocracy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {


    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button register ;
    EditText name,email,password;
    CheckBox terms;
    TextView alredylogin;
    RadioGroup group;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        openHelper = new DatabaseHelper(this);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        alredylogin=findViewById(R.id.alredylogin);
        group=findViewById(R.id.group);
        register=findViewById(R.id.register);

        alredylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ApiCallRegister();
                db=openHelper.getWritableDatabase();
                String nm=name.getText().toString();
                String em=email.getText().toString();
                String pw=password.getText().toString();



                int selectedId = group.getCheckedRadioButtonId();
                String gender;
                if(selectedId == R.id.female)
                    gender = "Female";
                else
                    gender = "Male";


                if (!isValidPassword(pw)) {
                    password.setError("Password must be at least 6 characters");
                }

                if (!isValidEmail(em)) {
                    email.setError("invalid Email");
                }

              /*  boolean  insertedata = insertedata(nm,em,pw,gender);
                if (insertedata = true) {
                    Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);

                }else if (insertedata=false){
                    Toast.makeText(getApplicationContext(), "Please Input Data !", Toast.LENGTH_SHORT).show();
                }*/

            }
        });


    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }



    private void ApiCallRegister(){

        API apiService = APIS.getRetrofit().create(API.class);
        Call<Response_> call1 = apiService.Signup(name.getText().toString().trim(),
                email.getText().toString().trim(),
                password.getText().toString().trim(),gender);

       call1.enqueue(new Callback<Response_>() {
           @Override
           public void onResponse(Call<Response_> call, Response<Response_> response) {

               if (response.isSuccessful()) {
                Response_ response_= response.body();

                   if (response_ != null && response_.getStatus().equals("true"))
                       Log.e("Register", "register" + response.body().toString());
                   Intent intent = new Intent(getApplicationContext(), Login.class);
                       startActivity(intent);


               }
           }

                         @Override
                         public void onFailure(Call<Response_> call, Throwable t) {

                         }

                         });


    }

  /*  private boolean insertedata(String nm,String em,String pw,String gender){
        ContentValues contentValues= new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,(nm));
        contentValues.put(DatabaseHelper.COL_3,(em));
        contentValues.put(DatabaseHelper.COL_4,(pw));
        contentValues.put(DatabaseHelper.COL_5,(gender));
        long id =db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

        return false;
    }*/
}
