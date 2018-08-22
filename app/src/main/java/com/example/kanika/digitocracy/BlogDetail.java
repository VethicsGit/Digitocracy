package com.example.kanika.digitocracy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kanika.digitocracy.APIResponse.BlogDetail.BlogDetailResponse;
import com.example.kanika.digitocracy.APIResponse.BlogDetail.BlogDetail_;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogDetail extends Fragment {

    SharedPreferences LoginPref;

    ImageView blog_detail_image;
    TextView blog_detail_title,blog_detail_categori,blog_detail_date,blog_detailp_desc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_blog_detail,container,false);

        blog_detail_title=view.findViewById(R.id.blog_detail_title);
        blog_detail_image=view.findViewById(R.id.blog_detail_image);
        blog_detail_categori=view.findViewById(R.id.blog_detail_categori);
        blog_detail_date=view.findViewById(R.id.blog_detail_date);
        blog_detailp_desc=view.findViewById(R.id.blog_detailp_desc);

        LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        API apiService = APIS.getRetrofit().create(API.class);
        Call<BlogDetailResponse> call = apiService.blog_details(LoginPref.getString("user_id",""),LoginPref.getString("blog_id",""));

        call.enqueue(new Callback<BlogDetailResponse>() {
            @Override
            public void onResponse(Call<BlogDetailResponse> call, Response<BlogDetailResponse> response) {
                BlogDetailResponse blogListResponse =response.body();
                List<com.example.kanika.digitocracy.APIResponse.BlogDetail.Response>responseList=blogListResponse.getResponse();
                for (int i = 0; i < responseList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.BlogDetail.Response response1=responseList.get(i);
                    List<BlogDetail_>blogDetails=response1.getBlogDetails();

                    if (response1.getStatus().equals("true")){
                        for (int x=0;x<blogDetails.size();x++){
                            BlogDetail_ blogDetail_=response1.getBlogDetails();


                        }


                    }


                }

            }

            @Override
            public void onFailure(Call<BlogDetailResponse> call, Throwable t) {

            }
        });
        return view;
    }
}
