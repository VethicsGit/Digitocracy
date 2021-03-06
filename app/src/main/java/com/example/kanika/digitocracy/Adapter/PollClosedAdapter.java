package com.example.kanika.digitocracy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.APIResponse.PollList_Response.PollList;
import com.example.kanika.digitocracy.ActivityPollDetails;
import com.example.kanika.digitocracy.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PollClosedAdapter extends RecyclerView.Adapter<PollClosedAdapter.ViewHolder> {

     List<PollList> blogLists;
     Context context;

    public PollClosedAdapter(List<PollList> blogLists, Context context) {
        this.blogLists = blogLists;
        this.context = context;
    }

    public void add(PollList blogList) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.frgamentelectricalclose,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PollClosedAdapter.ViewHolder holder, int position) {

        PollList pollDetail=blogLists.get(position);


        holder.poll_live_title.setText(pollDetail.getPollTitle());
        holder.poll_live_total_vote.setText("Votes : "+pollDetail.getTotalPollVotes());
        holder.poll_close_id.setText(pollDetail.getPollId());
        holder.poll_close_cateid.setText(pollDetail.getPollCategoryId());

        holder.poll_close_knoe_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,ActivityPollDetails.class);
                i.putExtra("poll_id",holder.poll_close_id.getText().toString());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {

        return blogLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView poll_live_title,poll_live_total_vote,poll_close_knoe_more,poll_close_id,poll_close_cateid;

        public ViewHolder(View itemView) {
            super(itemView);
            poll_live_title=itemView.findViewById(R.id.poll_close_title);
            poll_live_total_vote=itemView.findViewById(R.id.poll_close_vote);
            poll_close_knoe_more=itemView.findViewById(R.id.poll_close_knoe_more);
            poll_close_id=itemView.findViewById(R.id.poll_close_id);
            poll_close_cateid=itemView.findViewById(R.id.poll_close_cateid);

        }
    }



}
