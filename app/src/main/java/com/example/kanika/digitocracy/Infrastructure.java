package com.example.kanika.digitocracy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kanika.digitocracy.Fragments.FragmentInfclose;
import com.example.kanika.digitocracy.Fragments.FragmentInflive;

public class    Infrastructure extends AppCompatActivity {
    TextView live_inf,close_inf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infrastructure);


        live_inf=findViewById(R.id.live_inf);
        close_inf=findViewById(R.id.close_inf);



        live_inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentInflive fl= new FragmentInflive();
                fragmentTransaction.replace(R.id.fragment_container_inf,fl);
                fragmentTransaction.commit();


            }
        });

        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        FragmentInflive fl= new FragmentInflive();
        fragmentTransaction.replace(R.id.fragment_container_inf,fl);
        fragmentTransaction.commit();

        close_inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentInfclose fc= new FragmentInfclose();
                fragmentTransaction.replace(R.id.fragment_container_inf,fc);
                fragmentTransaction.commit();

            }
        });
    }
}
