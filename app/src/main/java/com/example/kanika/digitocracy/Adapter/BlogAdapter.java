package com.example.kanika.digitocracy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.BlogDetail;
import com.example.kanika.digitocracy.R;

import java.util.ArrayList;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

     List<BlogList> blogLists;
     Context context;

    public BlogAdapter(List<BlogList> blogLists, Context context) {
        this.blogLists = blogLists;
        this.context = context;
    }

    public void add(BlogList blogList) {
        if (blogLists.isEmpty())
            blogLists=new ArrayList<>();
        else
        blogLists.add(blogList);
        notifyDataSetChanged();
    }
    public void clear() {
        if (blogLists!=null)
        blogLists=null;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView blog_createdat,blog_cateid,blog_id,blog_title,bloglist_blogcatetitle;
        ImageView blog_coverimg;
        List<BlogList> blogLists;
        BlogList list;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.blogLists = blogLists;
            blog_createdat=itemView.findViewById(R.id.blog_createdat);
            blog_cateid=itemView.findViewById(R.id.blog_cateid);
            blog_id=itemView.findViewById(R.id.blog_id);
            blog_title=itemView.findViewById(R.id.blog_title);
            bloglist_blogcatetitle=itemView.findViewById(R.id.bloglist_blogcatetitle);
            blog_coverimg=itemView.findViewById(R.id.blog_coverimg);
        }

        @Override
        public void onClick(View view) {

            int postion = getAdapterPosition();
            BlogList blogList=this.blogLists.get(postion);
            Intent intent = new Intent (view.getContext(), BlogDetail.class);
            view.getContext().startActivity(intent);

        }
    }

}
