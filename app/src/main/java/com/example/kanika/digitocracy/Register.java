package com.example.kanika.digitocracy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.signup.Response_;
import com.example.kanika.digitocracy.APIResponse.signup.Responsesignup;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//    RadioGroup group;
    String gender;


    String[] countryNames={"Gender","Male","Female"};
//    int flags[] = {R.drawable.gender, R.drawable.male, R.drawable.female};
Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);


        openHelper = new DatabaseHelper(this);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        alredylogin = findViewById(R.id.alredylogin);
//        group = findViewById(R.id.group);
        register = findViewById(R.id.register);
        spin = findViewById(R.id.register_gen_spin);

        alredylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });



        ArrayAdapter   your_adpter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, countryNames){

            @Override
            public View getDropDownView(int position, View convertView,ViewGroup parent) {
                // TODO Auto-generated method stub

                View view = super.getView(position, convertView, parent);

                TextView text = (TextView)view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub

                View view = super.getView(position, convertView, parent);

                TextView text = (TextView)view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);

                return view;

            }
        };


//        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),countryNames);
        spin.setAdapter(your_adpter);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                ApiCallRegister();
                db = openHelper.getWritableDatabase();
                String nm = name.getText().toString();
                String em = email.getText().toString();
                String pw = password.getText().toString();


              /*  int selectedId = group.getCheckedRadioButtonId();

                if (selectedId == R.id.female)
                    gender = "Female";
                else
                    gender = "Male";*/




gender=spin.getSelectedItem().toString();
                if (!isValidPassword(pw)) {
                    password.setError("Password must be at least 6 characters");
                }

                else if (!isValidEmail(em)) {
                    email.setError("invalid Email");
                }else if (nm.equals("")) {
                    name.setError("Enter name");
                }
                else if (gender.equalsIgnoreCase("gender"))
                {
                    Toast.makeText(Register.this, "Please select gender first.", Toast.LENGTH_SHORT).show();
                }
                else {
                    ApiCallRegister();
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
        mProgressDialog.setCancelable(false);
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


class CustomAdapter extends BaseAdapter {
    Context context;
//    int flags[];
    String[] countryNames;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, /*int[] flags,*/ String[] countryNames) {
        this.context = applicationContext;
//        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.gender_spinner_layout, null);
        ImageView icon = (ImageView) view.findViewById(R.id.gender_spn_imageView);
        TextView names = (TextView) view.findViewById(R.id.gender_spn_textView);
//        icon.setImageResource(flags[i]);
        names.setText(countryNames[i]);
        return view;
    }
}