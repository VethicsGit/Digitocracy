package com.example.kanika.digitocracy.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogListResponse;
import com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Login;
import com.example.kanika.digitocracy.R;
import com.example.kanika.digitocracy.extra.RecyclerViewMargin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBlog extends Fragment {

    RecyclerView blog_recyclar_view;
    SharedPreferences LoginPref;

    int offset = 0;
    int blog_category_id = 0;
    boolean isLoading = false;

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
        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 2);
        blog_recyclar_view.addItemDecoration(decoration);

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
                Toast.makeText(getContext(), "in response", Toast.LENGTH_SHORT).show();
                BlogListResponse blogListResponse = response.body();
                List<com.example.kanika.digitocracy.APIResponse.BlogList.Response> resList = blogListResponse.getResponse();
                for (int i = 0; i < resList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.BlogList.Response re = resList.get(i);
                    if (re.getStatus().equals("true")) {
                        offset = re.getOffset();
                       
                        List<BlogList> blogLists = re.getBlogList();
                        final BlogAdapter blogAdapter = new BlogAdapter(blogLists, getContext());
                        blog_recyclar_view.setAdapter(blogAdapter);
                    }
                    else {
                        Toast.makeText(getContext(), "else part", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<BlogListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

//        ServiceCall();

        blog_recyclar_view.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                int pastVisibleItems = manager.findFirstVisibleItemPosition();

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    ServiceCall();
                }
            }
        });
        return view;
    }

    public void ServiceCall() {
        final BlogAdapter blogAdapter = new BlogAdapter(new ArrayList<BlogList>(), getContext());

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
                        for (int x = 0; x < blogLists.size(); x++)
                            blogAdapter.add(blogLists.get(x));
                    }

                }
            }

            @Override
            public void onFailure(Call<BlogListResponse> call, Throwable t) {

            }
        });
    }
}

class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    List<BlogList> blogLists;
    Context context;

    public BlogAdapter(List<BlogList> blogLists, Context context) {
        this.blogLists = blogLists;
        this.context = context;
    }

    public void add(BlogList blogList) {
        blogLists.add(blogList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blog_list_item_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BlogList bolg=blogLists.get(position);
        holder.blog_createdat.setText(bolg.getCreatedAt());
        holder.blog_cateid.setText(bolg.getBlogCategoryId());
        holder.blog_id.setText(bolg.getBlogId());
        holder.blog_title.setText(bolg.getBlogTitle());
        holder.bloglist_blogcatetitle.setText(bolg.getBlogCategoryTitle());

        Glide.with(context).load(bolg.getCoverImage()).into(holder.blog_coverimg);

    }

    @Override
    public int getItemCount() {
        return blogLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView blog_createdat,blog_cateid,blog_id,blog_title,bloglist_blogcatetitle;
        ImageView blog_coverimg;

        public ViewHolder(View itemView) {
            super(itemView);

            blog_createdat=itemView.findViewById(R.id.blog_createdat);
            blog_cateid=itemView.findViewById(R.id.blog_cateid);
            blog_id=itemView.findViewById(R.id.blog_id);
            blog_title=itemView.findViewById(R.id.blog_title);
            bloglist_blogcatetitle=itemView.findViewById(R.id.bloglist_blogcatetitle);
            blog_coverimg=itemView.findViewById(R.id.blog_coverimg);
        }
    }

}
