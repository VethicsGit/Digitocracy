package com.example.kanika.digitocracy.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogListResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Adapter.BlogAdapter;
import com.example.kanika.digitocracy.R;
import com.example.kanika.digitocracy.extra.PaginationScrollListener;
import com.example.kanika.digitocracy.extra.Paginator;
import com.example.kanika.digitocracy.extra.RecyclerViewMargin;
import com.srx.widget.PullToLoadView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBlog extends Fragment {

    RecyclerView blog_recyclar_view;
    SharedPreferences LoginPref;

    int offset = 0;
    int blog_category_id = 0;
    int pos= 0;
    boolean isLoading = false;
    BlogAdapter blogAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        blog_recyclar_view = view.findViewById(R.id.blog_recyclar_view);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        blog_recyclar_view.setLayoutManager(manager);
        /*final BlogAdapter blogAdapter = new BlogAdapter(new ArrayList<BlogList>(), getContext());
        blog_recyclar_view.setAdapter(blogAdapter);
*/

        /*
        if (offset==0) {
            RecyclerViewMargin decoration = new RecyclerViewMargin(10, 2);
            blog_recyclar_view.getRecyclerView().addItemDecoration(decoration);

            LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
            final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Please wait...");
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
                            blog_recyclar_view.getRecyclerView().setAdapter(blogAdapter);
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
        }else*/


//        new Paginator(getContext(),blog_recyclar_view);


//        ServiceCall();

       /* blog_recyclar_view.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                int pastVisibleItems = manager.findFirstVisibleItemPosition();

                pos=totalItemCount;
                if (visibleItemCount + pastVisibleItems >= totalItemCount && pastVisibleItems >= 0) {
                    ServiceCall();
                }
            }
        });*/

        /*blog_recyclar_view.addOnScrollListener(new PaginationScrollListener(manager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1; //Increment page index to load the next one
                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();*/
        return view;
    }

 /*   private void loadFirstPage() {
        // fetching dummy data
        List<Movie> movies = Movie.createMovies(adapter.getItemCount());
        progressBar.setVisibility(View.GONE);
        adapter.addAll(movies);

        if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
        else isLastPage = true;
    }
*/
    public void ServiceCall() {
//        final BlogAdapter blogAdapter = new BlogAdapter();

        LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);

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
//                        int pos=blogAdapter.getItemCount();
                        for (int x = 0; x < blogLists.size(); x++)
                            blogAdapter.add(blogLists.get(x));
                        blogAdapter.notifyDataSetChanged();
//                        blog_recyclar_view.scrollToPosition(pos);
//                        blog_recyclar_view.setAdapter(blogAdapter);
                    }else {
                        Toast.makeText(getContext(), "This is the end", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<BlogListResponse> call, Throwable t) {

            }
        });
    }
}

