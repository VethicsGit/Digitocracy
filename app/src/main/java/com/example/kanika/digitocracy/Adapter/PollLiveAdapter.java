package com.example.kanika.digitocracy.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogList;
import com.example.kanika.digitocracy.APIResponse.PollDetails_response.PollDetail;
import com.example.kanika.digitocracy.APIResponse.PollList_Response.PollList;
import com.example.kanika.digitocracy.APIResponse.PollList_Response.PollListResponse;
import com.example.kanika.digitocracy.APIResponse.PollList_Response.PollVoteOption;
import com.example.kanika.digitocracy.APIResponse.PollVoteResponse.PollVoteResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollLiveAdapter extends RecyclerView.Adapter<PollLiveAdapter.ViewHolder> {

    List<PollList> blogLists;
    Context context;

    public PollLiveAdapter(List<PollList> blogLists, Context context) {
        this.blogLists = blogLists;
        this.context = context;
    }

    public void add(PollList blogList) {
        if (blogLists.isEmpty()) blogLists = new ArrayList<>();
        else blogLists.add(blogList);
        notifyDataSetChanged();
    }

    public void clear() {
        if (blogLists != null) blogLists = null;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frgamentelectricallive, null);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final PollList pollDetail = blogLists.get(position);

        holder.poll_live_title.setText(pollDetail.getPollTitle());
        holder.poll_live_id.setText(pollDetail.getPollId());
        holder.poll_live_cateid.setText(pollDetail.getPollCategoryId());
        holder.poll_live_total_vote.setText("Votes : " + pollDetail.getTotalPollVotes());

//        DateTimeUtils obj = new DateTimeUtils();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        try {
            Date date1 = simpleDateFormat.parse(pollDetail.getEndDateTime());
            Date date2 = simpleDateFormat.parse(pollDetail.getStartDateTime());

            holder.poll_live_days.setText(printDifference(date1, date2));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        ColorStateList colorStateList = new ColorStateList(new int[][]{

                new int[]{android.R.attr.state_enabled} //enabled
        }, new int[]{

                context.getResources().getColor(R.color.clr) //enabled
//                , Color.parseColor("00b9ee") //enabled

        });



        List<PollVoteOption> pollVoteOptionList = pollDetail.getPollVoteOptions();
        for (int i = 0; i < pollVoteOptionList.size(); i++) {
            final PollVoteOption pollVoteOption = pollVoteOptionList.get(i);
            RadioButton rb = new RadioButton(context);
            rb.setText(pollVoteOption.getOption());
            rb.setTag(pollVoteOption.getPollVoteOptionId());
            if (pollVoteOption.getPollVoteOptionId().equals(pollDetail.getMyPollVoteOptionId())) {
                rb.setChecked(true);
                rb.setClickable(false);
            }

            rb.setButtonTintList(colorStateList);
            Log.e( "already_voted ",pollDetail.getAlreadyVoted());
            if (Integer.parseInt(pollDetail.getAlreadyVoted()) == 1) {
                holder.poll_live_group.setClickable(false);
                holder.poll_live_group.setEnabled(false);
            }else {
                rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (Integer.parseInt(pollDetail.getAlreadyVoted()) == 1) {
                            compoundButton.setClickable(false);
                            Toast.makeText(context, "You have already voted.", Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferences LoginPref = context.getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
                            API apiService = APIS.getRetrofit().create(API.class);
                            Call<PollVoteResponse> call1 = apiService.send_vote_on_poll(LoginPref.getString("user_id", ""), pollDetail.getPollId(), pollVoteOption.getPollVoteOptionId(), LoginPref.getString("token", ""));

                            call1.enqueue(new Callback<PollVoteResponse>() {
                                @Override
                                public void onResponse(Call<PollVoteResponse> call, Response<PollVoteResponse> response) {
                                    PollVoteResponse pollVoteResponse = response.body();
                                    Gson gson = new GsonBuilder().create();
                                    JsonObject myCustomArray = gson.toJsonTree(pollVoteResponse).getAsJsonObject();
                                    JsonArray ary = myCustomArray.getAsJsonArray("response");
                                    JsonObject obj = (JsonObject) ary.get(0);
                                    if (obj.get("status").equals("true")) {
                                        Toast.makeText(context, "Voted sucessfully", Toast.LENGTH_SHORT).show();
                                    } else Toast.makeText(context, "Voted Unsucessful", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<PollVoteResponse> call, Throwable t) {

                                }
                            });
                        }
                    }
                });

            }
            holder.poll_live_group.addView(rb);
        }

    }

    @Override
    public int getItemCount() {

        return blogLists.size();
    }

    public String printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf("%d days, %d hours, %d minutes, %d seconds%n", elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);

        if (elapsedDays < 7)

            return "Closed in a week";
        else return "Closed in " + elapsedSeconds + " days";
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView poll_live_title, poll_live_total_vote, poll_live_days, poll_live_id, poll_live_cateid;
        RadioGroup poll_live_group;

        public ViewHolder(View itemView) {
            super(itemView);
            poll_live_title = itemView.findViewById(R.id.poll_live_title);
            poll_live_total_vote = itemView.findViewById(R.id.poll_live_total_vote);
            poll_live_days = itemView.findViewById(R.id.poll_live_days);
            poll_live_id = itemView.findViewById(R.id.poll_live_id);
            poll_live_cateid = itemView.findViewById(R.id.poll_live_cateid);
            poll_live_group = itemView.findViewById(R.id.poll_live_group);

        }
    }

}
