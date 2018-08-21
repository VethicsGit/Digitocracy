package com.example.kanika.digitocracy.Fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kanika.digitocracy.R;

public class FragmentEditprofile extends AppCompatActivity {

    ImageView img;
    Spinner editprofile_gender;
    EditText editprofile_name;
    Button editprofile_chang_pw,editprofile_update;
    TextView notification,editprofile_email;
    String[] countryNames={"Gender","Male","Female"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenteditprofile);
        img=findViewById(R.id.profile_image);
        editprofile_name=findViewById(R.id.editprofile_name);
        editprofile_gender=findViewById(R.id.editprofile_gender);
        editprofile_email=findViewById(R.id.editprofile_email);
        notification=findViewById(R.id.editprofile_notification);
        editprofile_chang_pw=findViewById(R.id.editprofile_change_pw);
        editprofile_update=findViewById(R.id.editprofile_upadte);


        SharedPreferences preferences=getSharedPreferences("LoginStatus",MODE_PRIVATE);
        editprofile_name.setText(preferences.getString("name",""));
        editprofile_email.setText(preferences.getString("email",""));

        ArrayAdapter your_adpter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, countryNames){

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
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

        editprofile_gender.setAdapter(your_adpter);
    }

}