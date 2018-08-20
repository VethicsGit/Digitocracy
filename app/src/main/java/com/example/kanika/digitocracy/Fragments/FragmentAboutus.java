package com.example.kanika.digitocracy.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kanika.digitocracy.APIResponse.Aboutus.Aboutus;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAboutus extends Fragment {


        TextView about_tv1,about_description;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_aboutus,container,false);

        about_tv1=view.findViewById(R.id.about_tv1);
        about_description=view.findViewById(R.id.about_description);


        API apiService = APIS.getRetrofit().create(API.class);
        Call<Aboutus> call=apiService.About_Us(about_description.getText().toString());

        call.enqueue(new Callback<Aboutus>() {
            @Override
            public void onResponse(Call<Aboutus> call, Response<Aboutus> response) {
                Aboutus aboutus=response.body();
                List<com.example.kanika.digitocracy.APIResponse.Aboutus.Response> list_response=aboutus.getResponse();


                for (int i = 0; i < list_response.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.Aboutus.Response response1 = list_response.get(i);
                    if (response1.getStatus().equals("true")){


                        about_description.setText(Html.fromHtml(response1.getAboutUs()));

                    }


                    about_description.setText(response1.toString());


                }


            }

            @Override
            public void onFailure(Call<Aboutus> call, Throwable t) {

            }
        });





        return view;
    }
}
