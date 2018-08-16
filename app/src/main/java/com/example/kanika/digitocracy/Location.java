package com.example.kanika.digitocracy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Location extends AppCompatActivity {


    private FusedLocationProviderClient client;

    TextView tv1, tv2;
    Button location;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        requestPermission();
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        client = LocationServices.getFusedLocationProviderClient(this);

        location = findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(Location.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                client.getLastLocation().addOnSuccessListener(Location.this, new OnSuccessListener<android.location.Location>() {
                    @Override
                     public void onSuccess(android.location.Location location) {


                        Intent i = new Intent(Location.this,PollCategories.class);
                        startActivity(i);

                        if (location !=null ){
                            TextView textView = findViewById(R.id.loc);
                            textView.setText(location.toString());

                        }


                    }
                });
            }
        });

    }
    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }

    }
