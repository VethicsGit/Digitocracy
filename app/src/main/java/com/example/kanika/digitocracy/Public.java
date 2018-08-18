package com.example.kanika.digitocracy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kanika.digitocracy.Fragments.FragmentpublicLive;
import com.example.kanika.digitocracy.Fragments.Fragmentpublicclose;

public class Public extends AppCompatActivity {

        Button live,close;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        live=findViewById(R.id.live);
        close=findViewById(R.id.close);

        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentpublicLive fl=new FragmentpublicLive();
                fragmentTransaction.add(R.id.fragment_container,fl);
                fragmentTransaction.commit();


            }
        });
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        FragmentpublicLive fl=new FragmentpublicLive();
        fragmentTransaction.add(R.id.fragment_container,fl);
        fragmentTransaction.commit();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                  android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                 Fragmentpublicclose fc=new Fragmentpublicclose();
                 fragmentTransaction.add(R.id.fragment_container,fc);
                 fragmentTransaction.commit();

            }
        });
    }
}
