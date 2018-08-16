package com.example.kanika.digitocracy;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kanika.digitocracy.login.LoginResponse;
import com.example.kanika.digitocracy.login.Responsee;
import com.example.kanika.digitocracy.signup.Response_;
import com.example.kanika.digitocracy.signup.Responsesignup;

import java.util.List;
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
    Button register;
    EditText name, email, password;
    CheckBox terms;
    TextView alredylogin;
    RadioGroup group;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);


        openHelper = new DatabaseHelper(this);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        alredylogin = findViewById(R.id.alredylogin);
        group = findViewById(R.id.group);
        register = findViewById(R.id.register);

        alredylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ApiCallRegister();
                db = openHelper.getWritableDatabase();
                String nm = name.getText().toString();
                String em = email.getText().toString();
                String pw = password.getText().toString();


                int selectedId = group.getCheckedRadioButtonId();

                if (selectedId == R.id.female)
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


    private void ApiCallRegister() {
        final ProgressDialog  mProgressDialog = new ProgressDialog(Register.this);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        mProgressDialog.setContentView(R.layout.progress_dialog_layout);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();

        API apiService = APIS.getRetrofit().create(API.class);
        Call<Responsesignup> call1 = apiService.Signup(name.getText().toString().trim(),
                email.getText().toString().trim(),
                password.getText().toString().trim(), gender);

        call1.enqueue(new Callback<Responsesignup>() {
            @Override
            public void onResponse(@NonNull Call<Responsesignup> call, @NonNull Response<Responsesignup> response) {
                Log.e("_response_signup","login"+response.body().toString());

                Responsesignup res = response.body();
                List<Response_> list_response = res.getResponse();


                for (int i= 0;i<list_response.size();i++) {

                    Response_ response_ = list_response.get(i);
                    Log.e("response status",response_.getStatus());

                    if (response_.getStatus().equals("true")) {
                        mProgressDialog.dismiss();
                        Log.e("Register", "register" + response.body().toString());
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);


                    }else {
mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"please check your email and verify you account first",Toast.LENGTH_LONG).show();
                    }


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

            @Override
            public void onFailure(@NonNull Call<Responsesignup> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(Register.this, "Failure", Toast.LENGTH_SHORT).show();
                Log.e("throwable",t.getMessage());
                call.request();

            }

        });
    }
}