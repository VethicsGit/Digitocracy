package com.example.kanika.digitocracy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kanika.digitocracy.Fragments.FragmentGovclose;
import com.example.kanika.digitocracy.Fragments.FragmentGovlive;

public class Goverment extends AppCompatActivity {
    TextView live_gov,close_gov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goverment);


        live_gov=findViewById(R.id.live_gov);
        close_gov=findViewById(R.id.close_gov);



        live_gov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentGovlive fl= new FragmentGovlive();
                fragmentTransaction.replace(R.id.fragment_container_gov,fl);
                fragmentTransaction.commit();


            }
        });

        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        FragmentGovlive fl= new FragmentGovlive();
        fragmentTransaction.replace(R.id.fragment_container_gov,fl);
        fragmentTransaction.commit();

        close_gov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentGovclose fc= new FragmentGovclose();
                fragmentTransaction.replace(R.id.fragment_container_gov,fc);
                fragmentTransaction.commit();

            }
        });
    }
}
