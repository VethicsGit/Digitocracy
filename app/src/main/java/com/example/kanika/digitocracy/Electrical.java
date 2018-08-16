package com.example.kanika.digitocracy;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Electrical extends AppCompatActivity {

  TextView live_elc,close_elc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_electrical);
        super.onCreate(savedInstanceState);
        live_elc=findViewById(R.id.live_elc);
        close_elc=findViewById(R.id.close_elc);

        live_elc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                Fragmentelectricallive fl= new Fragmentelectricallive();
                fragmentTransaction.replace(R.id.fragment_container_elc,fl);
                fragmentTransaction.commit();


            }
        });
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        Fragmentelectricallive fl= new Fragmentelectricallive();
        fragmentTransaction.replace(R.id.fragment_container_elc,fl);
        fragmentTransaction.commit();


        close_elc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                Fragmentelectricalclose fc= new Fragmentelectricalclose();
                fragmentTransaction.replace(R.id.fragment_container_elc,fc);
                fragmentTransaction.commit();

            }
        });
    }
}
