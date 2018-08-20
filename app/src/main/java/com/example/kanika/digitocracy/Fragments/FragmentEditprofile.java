package com.example.kanika.digitocracy.Fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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


        SharedPreferences sharedPreferences = getSharedPreferences("Mypref",MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editprofile_name.setText(sharedPreferences.getString("name",""));
        editprofile_email.setText(sharedPreferences.getString("email",""));
        editor.commit();


    }
/*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragmenteditprofile,container,false);

       


        return view;
    }*/
}