package com.example.kanika.digitocracy;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.kanika.digitocracy.Fragments.FragmentAboutus;
import com.example.kanika.digitocracy.Fragments.FragmentEditprofile;
import com.example.kanika.digitocracy.Fragments.FragmentFeedback;
import com.example.kanika.digitocracy.Fragments.FragmentPrivacyPolice;
import com.example.kanika.digitocracy.Fragments.FragmentTermsofuse;

public class Setting extends Fragment {


    LinearLayout setting_editprofile,setting_aboutus,setting_feedback,setting_privacypolice,setting_termsofuse,setting_invitefriends,setting_rateapp,setting_logout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);


        setting_editprofile = (LinearLayout) view.findViewById(R.id.setting_editprofile);
        setting_aboutus = (LinearLayout) view.findViewById(R.id.setting_aboutus);
        setting_feedback = (LinearLayout) view.findViewById(R.id.setting_feedback);
        setting_privacypolice = (LinearLayout) view.findViewById(R.id.setting_privacypolice);
        setting_termsofuse = (LinearLayout) view.findViewById(R.id.setting_termsofuse);
        setting_invitefriends=(LinearLayout)view.findViewById(R.id.setting_invitefriends);
        setting_rateapp = (LinearLayout) view.findViewById(R.id.setting_rateapp);

        setting_logout = (LinearLayout) view.findViewById(R.id.setting_logout);


        setting_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new FragmentEditprofile();
android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        setting_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new FragmentAboutus();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        setting_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentFeedback();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        setting_privacypolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentPrivacyPolice();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        setting_termsofuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentTermsofuse();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        setting_invitefriends.setOnClickListener(new View.OnClickListener() {
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

        return view;
    }
}
