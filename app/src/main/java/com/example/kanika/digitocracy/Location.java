package com.example.kanika.digitocracy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kanika.digitocracy.location.LocationResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Location extends AppCompatActivity {


    private FusedLocationProviderClient client;

    TextView tv1, tv2;
    Button location;
    String lat,lng;
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

                    return;
                }
                client.getLastLocation().addOnSuccessListener(Location.this, new OnSuccessListener<android.location.Location>() {
                    @Override
                     public void onSuccess(final android.location.Location location) {


                        Intent i = new Intent(Location.this,PollCategories.class);
                        startActivity(i);

                        if (location !=null ){
                            final TextView textView = findViewById(R.id.loc);




                            API apiService = APIS.getRetrofit().create(API.class);
                            Call<LocationResponse> call = apiService.Upadate_location()):

                            call.enqueue(new Callback<LocationResponse>() {
                                @Override
                                public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                                    location.getLatitude();
                                    location.getLongitude();
                                    textView.setText(location.toString():

                                }

                                @Override
                                public void onFailure(Call<LocationResponse> call, Throwable t) {

                                }
                            });





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
