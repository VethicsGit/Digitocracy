package com.example.kanika.digitocracy.extra;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogListResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Adapter.BlogAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager layoutManager;
    Context context;
    BlogAdapter blogAdapter;

    public PaginationScrollListener(LinearLayoutManager layoutManager,Context context) {
        this.layoutManager = layoutManager;
        this.context=context;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();

//                blogAdapter = new BlogAdapter(new ArrayList<BlogList>(), context);

//                ServiceCall();
            }
        }
    }

    protected abstract void loadMoreItems(

    );

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

    /*BlogAdapter blogAdapter;
    public void ServiceCall() {
//        final BlogAdapter blogAdapter = new BlogAdapter();

        LoginPref = context.getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);

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
                        Toast.makeText(context, "This is the end", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<BlogListResponse> call, Throwable t) {

            }
        });
    }*/
}