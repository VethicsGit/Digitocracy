package com.example.kanika.digitocracy.extra;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogListResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Adapter.BlogAdapter;
import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Paginator {
    PullToLoadView pullToLoadView;
    RecyclerView rv;
    BlogAdapter adapter;
    int page1 = 0;
    SharedPreferences LoginPref;
    private Context c;
    private boolean isLoading = false;
    private boolean hasLoadAll = false;
    private int nextPage;

    public Paginator(Context c, PullToLoadView pullToLoadView) {
        this.c = c;
        this.pullToLoadView = pullToLoadView;
        rv = pullToLoadView.getRecyclerView();
        rv.setLayoutManager(new LinearLayoutManager(c));
        Toast.makeText(c, "uplon adapter", Toast.LENGTH_SHORT).show();
        adapter = new BlogAdapter(new ArrayList<BlogList>() ,c);
        rv.setAdapter(adapter);

        initializePagination();
    }

    public void initializePagination() {

        pullToLoadView.isLoadMoreEnabled(true);
        pullToLoadView.setPullCallback(new PullCallback() {
            @Override
            public void onLoadMore() {
                LoadData();
            }

            @Override
            public void onRefresh() {
                adapter.clear();
                hasLoadAll = false;
                LoadData();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return hasLoadAll;
            }
        });

        pullToLoadView.initLoad();
    }

    private void LoadData() {

        isLoading = true;
        LoginPref = c.getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        final ProgressDialog mProgressDialog = new ProgressDialog(c);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();
        API apiService = APIS.getRetrofit().create(API.class);
        Call<BlogListResponse> call1 = apiService.blog_list(LoginPref.getString("user_id", ""), page1, "", LoginPref.getString("token", ""));

        call1.enqueue(new Callback<BlogListResponse>() {
            @Override
            public void onResponse(@NonNull Call<BlogListResponse> call, @NonNull Response<BlogListResponse> response) {
                mProgressDialog.dismiss();
                BlogListResponse blogListResponse = response.body();
                List<com.example.kanika.digitocracy.APIResponse.BlogList.Response> resList = blogListResponse.getResponse();
                for (int i = 0; i < resList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.BlogList.Response re = resList.get(i);
                    if (re.getStatus().equals("true")) {

                        List<BlogList> blogLists = re.getBlogList();
                        for (int x = 0; x < blogLists.size(); x++)
                            adapter.add(blogLists.get(x));


                    pullToLoadView.setComplete();
                    isLoading = false;

                        page1 = re.getOffset();
                    } else {
                        mProgressDialog.dismiss();
                        pullToLoadView.setComplete();
                        isLoading = false;
                    }

                }
            }

            @Override
            public void onFailure(Call<BlogListResponse> call, Throwable t) {
                Toast.makeText(c, "Something went wrong", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
                pullToLoadView.setComplete();
                isLoading = false;
            }
        });
    }
}
