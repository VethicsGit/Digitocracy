package com.example.kanika.digitocracy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.kanika.digitocracy.APIResponse.BlogDetail.BlogDetailResponse;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogListResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Adapter.BlogAdapter;
import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterDetail extends AppCompatActivity {

    CheckBox marketing, business, professional;
    Button done;
    SharedPreferences LoginPref;

    int offset = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_detail);
        marketing = findViewById(R.id.marketing);
        business = findViewById(R.id.business);
        professional = findViewById(R.id.professional);

        done=findViewById(R.id.done);



    }

    public void onCheckboxClicked(View view) {



        if(marketing.isChecked()){

            String checked=marketing.getText().toString();

            API apiService = APIS.getRetrofit().create(API.class);
            Call<BlogListResponse> call = apiService.blog_list(LoginPref.getString("user_id", ""), offset, "", LoginPref.getString("token", ""));

         call.enqueue(new Callback<BlogListResponse>() {
             @Override
             public void onResponse(Call<BlogListResponse> call, Response<BlogListResponse> response) {
                 BlogListResponse blogListResponse = response.body();
                 List<com.example.kanika.digitocracy.APIResponse.BlogList.Response> resList = blogListResponse.getResponse();
                 for (int i = 0; i < resList.size(); i++) {
                     com.example.kanika.digitocracy.APIResponse.BlogList.Response re = resList.get(i);
                     if (re.getStatus().equals("true")) {
                         offset = re.getOffset();

                         List<BlogList> blogLists = re.getBlogList();
                         blogAdapter = new BlogAdapter(blogLists, getContext());
                         blog_recyclar_view.setAdapter(blogAdapter);
                         Log.e("_Offset", String.valueOf(offset));
                         mSwipeRefreshLayout.setRefreshing(false);
                     } else {
                         mProgressDialog.dismiss();
                     }
                 }
             }

             @Override
             public void onFailure(Call<BlogListResponse> call, Throwable t) {

             }
         });
        }
        if(business.isChecked()){

        }
        if(professional.isChecked()){

        }

        }
}