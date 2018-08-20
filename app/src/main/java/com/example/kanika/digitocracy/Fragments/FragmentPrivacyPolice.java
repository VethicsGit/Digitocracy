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

import com.example.kanika.digitocracy.APIResponse.PrivacyPolice.PrivacyPolicy;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPrivacyPolice extends Fragment {

    TextView privacypolice_tv,privacypolice_description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fregment_privacypolice,container,false);

        privacypolice_tv=view.findViewById(R.id.privacypolice_tv);
        privacypolice_description=view.findViewById(R.id.privacypolice_description);


        API apiService = APIS.getRetrofit().create(API.class);
        Call<PrivacyPolicy> call=apiService.Privacy_Policy(privacypolice_description.getText().toString());

        call.enqueue(new Callback<PrivacyPolicy>() {
            @Override
            public void onResponse(Call<PrivacyPolicy> call, Response<PrivacyPolicy> response) {
                PrivacyPolicy privacyPolicy=response.body();
                List<com.example.kanika.digitocracy.APIResponse.PrivacyPolice.Response>responseList=privacyPolicy.getResponse();

                for (int i = 0; i < responseList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.PrivacyPolice.Response response1=responseList.get(i);
                    if (response1.getStatus().equals("true")){
                        privacypolice_description.setText(Html.fromHtml(response1.getPrivacyPolicy()));
                    }

                }

            }

            @Override
            public void onFailure(Call<PrivacyPolicy> call, Throwable t) {

            }
        });

        return view;
    }
}
