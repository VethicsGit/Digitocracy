package com.example.kanika.digitocracy.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogListResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Adapter.BlogAdapter;
import com.example.kanika.digitocracy.PollCategories;
import com.example.kanika.digitocracy.R;
import com.example.kanika.digitocracy.extra.PaginationScrollListener;
import com.example.kanika.digitocracy.extra.RecyclerViewMargin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBlog extends Fragment {

    RecyclerView blog_recyclar_view;
    SharedPreferences LoginPref;




    int offset = 0;
    BlogAdapter blogAdapter;
    private boolean isLoading = false;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ImageView blog_filter;
    String cat_id;
    RadioGroup categori;
    RadioButton marketing,business,professional;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        blog_recyclar_view = view.findViewById(R.id.blog_recyclar_view);



        blog_filter=view.findViewById(R.id.blog_filter);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        blog_recyclar_view.setLayoutManager(manager);

        if (offset==0) {
           LoadFirstItem();
        }

       mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset=0;
                LoadFirstItem();
            }
        });

        blog_recyclar_view.addOnScrollListener(new PaginationScrollListener(manager,getContext()) {
            @Override
            protected void loadMoreItems() {
                ServiceCall();
                isLoading=true;
            }

            @Override
            public int getTotalPageCount() {
                return 0;
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        return view;
    }

    public void LoadFirstItem(){
        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 2);
        blog_recyclar_view.addItemDecoration(decoration);

        LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        API apiService = APIS.getRetrofit().create(API.class);
        Call<BlogListResponse> call1 = apiService.blog_list(LoginPref.getString("user_id", ""), offset, "", LoginPref.getString("token", ""));

        call1.enqueue(new Callback<BlogListResponse>() {
            @Override
            public void onResponse(@NonNull Call<BlogListResponse> call, @NonNull Response<BlogListResponse> response) {
                mProgressDialog.dismiss();
                BlogListResponse blogListResponse = response.body();
                List<com.example.kanika.digitocracy.APIResponse.BlogList.Response> resList = blogListResponse.getResponse();
                for (int i = 0; i < resList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.BlogList.Response re = resList.get(i);
                    if (re.getStatus().equals("true")) {
                        offset = re.getOffset();

                        List<BlogList> blogLists = re.getBlogList();
                        blogAdapter = new BlogAdapter(blogLists, getContext());
                        blog_recyclar_view.setAdapter(blogAdapter);
                        Log.e("_Offset",String.valueOf(offset));
                        mSwipeRefreshLayout.setRefreshing(false);
                    } else {
                        mProgressDialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<BlogListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ServiceCall() {

        LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        API apiService = APIS.getRetrofit().create(API.class);
        Call<BlogListResponse> call1 = apiService.blog_list(LoginPref.getString("user_id", ""), offset, "", LoginPref.getString("token", ""));

        call1.enqueue(new Callback<BlogListResponse>() {
            @Override
            public void onResponse(Call<BlogListResponse> call, Response<BlogListResponse> response) {
                BlogListResponse blogListResponse = response.body();
                List<com.example.kanika.digitocracy.APIResponse.BlogList.Response> resList = blogListResponse.getResponse();
                for (int i = 0; i < resList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.BlogList.Response re = resList.get(i);
                    if (re.getStatus().equals("true")) {
                        offset = re.getOffset();
                        List<BlogList> blogLists = re.getBlogList();
                        for (int x = 0; x < blogLists.size(); x++)
                            blogAdapter.add(blogLists.get(x));
                        blogAdapter.notifyDataSetChanged();
                        mProgressDialog.dismiss();
                        Log.e("_Offset",String.valueOf(offset));
                        isLoading=false;
                    }else {
                        mProgressDialog.dismiss();
                        Toast.makeText(getContext(), "This is the end", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<BlogListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something broken in the way.", Toast.LENGTH_SHORT).show();
mProgressDialog.dismiss();
            }
        });

        blog_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dailog=new Dialog(getContext());
                dailog.setContentView(R.layout.filter_detail);

               marketing=(RadioButton)view.findViewById(R.id.marketing);
               business=(RadioButton)view.findViewById(R.id.business);
               professional=(RadioButton)view.findViewById(R.id.professional);
                final TextView marketni_tv=(TextView)view.findViewById(R.id.marketing_tv);
                TextView business_tv=(TextView)view.findViewById(R.id.business_tv);
                TextView professional_tv=(TextView)view.findViewById(R.id.professional_tv);
                Button done=view.findViewById(R.id.done);
//                categori=view.findViewById(R.id.categori);

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int selectedId=categori.getCheckedRadioButtonId();

                                if (selectedId==1){


                                /*    API apiService = APIS.getRetrofit().create(API.class);
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
*/
                    }
                                if (selectedId==2) {
                                  /*  API apiService = APIS.getRetrofit().create(API.class);
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
*/

                        }

                                if (selectedId==3) {
/*

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
*/

                                }
                        }



                });

            }
        });
    }
}

