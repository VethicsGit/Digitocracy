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

import com.example.kanika.digitocracy.APIResponse.Termsofuse.Termsofuse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTermsofuse extends Fragment {

    TextView terms_description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_termsofuse,container,false);


        terms_description=view.findViewById(R.id.terns_dsecription);


        API apiService = APIS.getRetrofit().create(API.class);
        Call<Termsofuse> call=apiService.Terms_and_condition();

        call.enqueue(new Callback<Termsofuse>() {
            @Override
            public void onResponse(Call<Termsofuse> call, Response<Termsofuse> response) {
                Termsofuse termsofuse=response.body();
                List<com.example.kanika.digitocracy.APIResponse.Termsofuse.Response>list=termsofuse.getResponse();

                for (int i = 0; i < list.size(); i++) {

                    com.example.kanika.digitocracy.APIResponse.Termsofuse.Response response1=list.get(i);
                    if (response1.getStatus().equals("true")){

                        terms_description.setText(Html.fromHtml(response1.getTermsAndCondition()));
                    }

                }

            }

            @Override
            public void onFailure(Call<Termsofuse> call, Throwable t) {

            }
        });

        return view;
    }
}
