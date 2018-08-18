package com.example.kanika.digitocracy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kanika.digitocracy.Fragments.FragmentEmpclose;
import com.example.kanika.digitocracy.Fragments.FragmentEmplive;

public class Employment extends AppCompatActivity {
    TextView live_emp,close_emp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment);

        live_emp=findViewById(R.id.live_emp);
        close_emp=findViewById(R.id.close_emp);

        live_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentEmplive fl= new FragmentEmplive();
                fragmentTransaction.replace(R.id.fragment_container_emp,fl);
                fragmentTransaction.commit();


            }
        });
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        FragmentEmplive fl= new FragmentEmplive();
        fragmentTransaction.replace(R.id.fragment_container_emp,fl);
        fragmentTransaction.commit();


        close_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentEmpclose fc= new FragmentEmpclose();
                fragmentTransaction.replace(R.id.fragment_container_emp,fc);
                fragmentTransaction.commit();

            }
        });
    }
}



