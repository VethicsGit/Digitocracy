package com.example.kanika.digitocracy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
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
import com.example.kanika.digitocracy.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HEAD;

public class BlogDetail extends AppCompatActivity {

    SharedPreferences LoginPref;

    ImageView blog_detail_image;
    TextView blog_detail_title,blog_detail_categori,blog_detail_date,blog_detailp_desc,blog_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blog_detail);


         blog_detail_title=findViewById(R.id.blog_detail_title);
        blog_detail_image=findViewById(R.id.blog_detail_image);
        blog_detail_categori=findViewById(R.id.blog_detail_categori);
        blog_detail_date=findViewById(R.id.blog_detail_date);
        blog_detailp_desc=findViewById(R.id.blog_detailp_desc);
        blog_id=findViewById(R.id.blog_id);

        LoginPref = getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        API apiService = APIS.getRetrofit().create(API.class);
        Call<BlogDetailResponse> call = apiService.blog_details(LoginPref.getString("user_id",""),Integer.parseInt(getIntent().getStringExtra("blog_id")),LoginPref.getString("token",""));

        call.enqueue(new Callback<BlogDetailResponse>() {
            @Override
            public void onResponse(Call<BlogDetailResponse> call, Response<BlogDetailResponse> response) {

                Gson gson = new GsonBuilder().create();
                JsonObject myCustomArray = gson.toJsonTree(response.body()).getAsJsonObject();
                Log.e("recycle","msg"+myCustomArray);

                BlogDetailResponse blogListResponse =response.body();
                List<com.example.kanika.digitocracy.APIResponse.BlogDetail.Response>responseList=blogListResponse.getResponse();
                for (int i = 0; i < responseList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.BlogDetail.Response response1=responseList.get(i);
                    List<BlogDetail_>blogDetails=response1.getBlogDetails();

                    Log.e("Detail","Respo"+response1);
                    if (response1.getStatus().equals("true")){
                        Log.e("msg","Response"+response1);
                        for (int x=0;x<blogDetails.size();x++){

<<<<<<< HEAD
                            BlogDetail_ blogDetail_= blogDetails.get(x);
=======
                            BlogDetail_ blogDetail_=blogDetails.get(x);

>>>>>>> 66cfa1c11a21f16eb6cb8ace1916f51daee36cd7

                            Log.e("blog","res"+blogDetail_);
                            blog_detail_title.setText(blogDetail_.getBlogTitle());
                            blog_detail_categori.setText(blogDetail_.getBlogCategoryTitle());
                            blog_detailp_desc.setText(Html.fromHtml(blogDetail_.getShortDescription()));
                            blog_detail_date.setText(blogDetail_.getCreatedAt());


                            Glide.with(getApplicationContext()).load(blogDetail_.getCoverImage()).into(blog_detail_image);

<<<<<<< HEAD
=======

>>>>>>> 66cfa1c11a21f16eb6cb8ace1916f51daee36cd7



                        }


                    }


                }

            }

            @Override
            public void onFailure(Call<BlogDetailResponse> call, Throwable t) {

            }
        });

    }
}

