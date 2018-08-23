package com.example.kanika.digitocracy.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kanika.digitocracy.APIResponse.DebatesList.DebateList;
import com.example.kanika.digitocracy.APIResponse.DebatesList.DebateParticipant;
import com.example.kanika.digitocracy.Public;
import com.example.kanika.digitocracy.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DebatesAdapter extends RecyclerView.Adapter<DebatesAdapter.ViewHolder> {

    List<DebateList> debateList;
    Context context;

    String day[] = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public DebatesAdapter(List<DebateList> debateList, Context context) {
        this.debateList = debateList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.debates_layout, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DebateList debate = debateList.get(position);
        holder.debates_title.setText(debate.getTopicTitle());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date sourceDate = null;

        Date date = null;
        try {
            date = dateFormat.parse(debate.getScheduleDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date); // yourdate is an object of type Date
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        Log.e("day_of_week", String.valueOf(dayOfWeek));
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        holder.debates_date_time.setText(day[dayOfWeek - 1] + ", " + format.format(date));
        List<DebateParticipant> debateParticipants = debate.getDebateParticipants();
        for (int i = 0; i < debateParticipants.size(); i++) {
            DebateParticipant debat = debateParticipants.get(i);

            LinearLayout layout=new LinearLayout(context);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
            params.setMargins(5,5,5,5);
            params.gravity=Gravity.CENTER;
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);

            CircleImageView circleImageView=new CircleImageView(context);
            LinearLayout.LayoutParams paramsImg=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
//            paramsImg.setMargins(3,3,3,3);
            paramsImg.gravity=Gravity.CENTER;
            circleImageView.setLayoutParams(paramsImg);
            Glide.with(context).load(debat.getProfilePicThumb()).into(circleImageView);



            TextView tQty=new TextView(context);
            LinearLayout.LayoutParams paramstqty=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 40,1);
            paramstqty.setMargins(20,0,20,10);
            paramstqty.gravity= Gravity.CENTER;

            tQty.setLayoutParams(paramstqty);
            tQty.setTextSize(15.0f);
            tQty.setTextColor(Color.BLACK);
            tQty.setSingleLine(true);
            tQty.setText(debat.getName());

            layout.addView(circleImageView);
            layout.addView(tQty);
            holder.debates_layout_img.addView(layout);

           /* holder.debates_profilename1.setText(debat.getName());

            Glide.with(context).load(debat.getProfilePic()).into(holder.debates_profile1);*/
        }

    }

    @Override
    public int getItemCount() {
        return debateList.size();
    }







    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView debates_title,debates_date_time,debates_profilename1,debates_profilename2,debates_profilename3;
        Button debates_join;
        ImageView debates_profile1, debates_profile2, debates_profile3;
        LinearLayout debates_layout_img;

        public ViewHolder(View itemView) {
            super(itemView);

            debates_title = itemView.findViewById(R.id.debates_title);
            debates_date_time = itemView.findViewById(R.id.debates_date_time);
            debates_profilename1 = itemView.findViewById(R.id.debates_profilename1);
            debates_profilename2 = itemView.findViewById(R.id.debates_profilename2);
            debates_profilename3 = itemView.findViewById(R.id.debates_profilename3);
//            debates_profile1 = itemView.findViewById(R.id.debates_profile1);
//            debates_profile2 = itemView.findViewById(R.id.debates_profile2);
//            debates_profile3 = itemView.findViewById(R.id.debates_profile3);
            debates_join = itemView.findViewById(R.id.debates_join);
            debates_layout_img = itemView.findViewById(R.id.debates_layout_img);

        }


    }
}
                            