package com.example.kanika.digitocracy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kanika.digitocracy.APIResponse.DebatesList.DebateList;
import com.example.kanika.digitocracy.APIResponse.DebatesList.DebateParticipant;
import com.example.kanika.digitocracy.Public;
import com.example.kanika.digitocracy.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class
DebatesAdapter extends RecyclerView.Adapter<DebatesAdapter.ViewHolder> {

    List<DebateList>debateList;
    Context context;

    String day[]=new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};


    public DebatesAdapter(List<DebateList> debateList, Context context) {
        this.debateList = debateList;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.debates_layout,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DebateList debate=debateList.get(position);
        holder.debates_title.setText(debate.getTopicTitle());


        Date date = new Date(debate.getScheduleDateTime());

        android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", date);

        Calendar c = Calendar.getInstance();
        c.setTime(date); // yourdate is an object of type Date

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy kk:mm p");
        try {
            Date date1 = format.parse(debate.getScheduleDateTime());
            holder.debates_date_time.setText(day[dayOfWeek]+", "+date1.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<DebateParticipant>debateParticipants =debate.getDebateParticipants();
     for (int i=0;i<debateParticipants.size();i++){
         DebateParticipant debat=debateParticipants.get(position);
         holder.debates_profilename1.setText(debat.getName());

         Glide.with(context).load(debat.getProfilePic()).into(holder.debates_profile1);
     }



    }

    @Override
    public int getItemCount() {
        return debateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView debates_title,debates_date_time,debates_profilename1,debates_profilename2,debates_profilename3;
        Button debates_join;
        ImageView debates_profile1,debates_profile2,debates_profile3;
        public ViewHolder(View itemView) {
            super(itemView);


            debates_title=itemView.findViewById(R.id.debates_title);
            debates_date_time=itemView.findViewById(R.id.debates_date_time);
            debates_profilename1=itemView.findViewById(R.id.debates_profilename1);
            debates_profilename2=itemView.findViewById(R.id.debates_profilename2);
            debates_profilename3=itemView.findViewById(R.id.debates_profilename3);
            debates_profile1=itemView.findViewById(R.id.debates_profile1);
            debates_profile2=itemView.findViewById(R.id.debates_profile2);
            debates_profile3=itemView.findViewById(R.id.debates_profile3);
            debates_join=itemView.findViewById(R.id.debates_join);



        }
    }
}
                            