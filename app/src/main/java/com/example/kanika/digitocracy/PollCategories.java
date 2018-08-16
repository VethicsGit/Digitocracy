package com.example.kanika.digitocracy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class PollCategories extends AppCompatActivity {

        RelativeLayout electricity,employement,transport;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_categorie);

        electricity=findViewById(R.id.electricity);
        employement=findViewById(R.id.employement);
        transport=findViewById(R.id.transport);
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PollCategories.this,Electrical.class);
                startActivity(i);
            }
        });
        employement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PollCategories.this,Public.class);
                startActivity(i);
            }
        });
    }
}
