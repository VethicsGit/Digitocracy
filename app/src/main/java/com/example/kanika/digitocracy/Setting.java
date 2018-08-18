package com.example.kanika.digitocracy;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.kanika.digitocracy.Fragments.FragmentAboutus;
import com.example.kanika.digitocracy.Fragments.FragmentEditprofile;

public class Setting extends AppCompatActivity {


    LinearLayout setting_editprofile,setting_aboutus,setting_feedback,setting_privacypolice,setting_termsofuse,setting_rateapp,setting_logout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);



        setting_editprofile=findViewById(R.id.setting_editprofile);
        setting_aboutus=findViewById(R.id.setting_aboutus);
        setting_feedback=findViewById(R.id.setting_feedback);
        setting_privacypolice=findViewById(R.id.setting_privacypolice);
        setting_termsofuse=findViewById(R.id.setting_termsofuse);
        setting_rateapp=findViewById(R.id.setting_rateapp);
        setting_logout=findViewById(R.id.setting_logout);


        setting_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Fragment fragment=new FragmentEditprofile();
android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            }
        });

        setting_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment=new  FragmentAboutus();

            }
        });
        setting_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setting_privacypolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setting_termsofuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setting_rateapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
