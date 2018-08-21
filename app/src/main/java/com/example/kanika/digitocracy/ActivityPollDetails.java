package com.example.kanika.digitocracy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import com.example.kanika.digitocracy.APIResponse.PollDetails_response.PollDetailsResponse;
import com.example.kanika.digitocracy.APIResponse.PollList_Response.PollListResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.achartengine.GraphicalView;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityPollDetails extends AppCompatActivity {

    TextView poll_details_title,poll_details_totalvotes;
    LinearLayout poll_details_progressbar_layout;

    final int c[]=new int[]{
            0xFF123456,
            0xFF343456,
            0xFF563456,
            0xFF873F56,
            0xFF56B7F1,
            0xFF343456,
            0xFF1FF4AC,
            0xFF1BA4E6,};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_details);

        poll_details_title=findViewById(R.id.poll_details_title);
        poll_details_totalvotes=findViewById(R.id.poll_details_totalvotes);
        poll_details_progressbar_layout=findViewById(R.id.poll_details_progressbar_layout);
        final BarChart mBarChart = (BarChart) findViewById(R.id.barchart);



       SharedPreferences LoginPref = getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        final ProgressDialog mProgressDialog = new ProgressDialog(ActivityPollDetails.this);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        API apiService = APIS.getRetrofit().create(API.class);
        Call<PollDetailsResponse> call1 = apiService.poll_details(LoginPref.getString("user_id", ""),Integer.parseInt(getIntent().getStringExtra("poll_id")), LoginPref.getString("token", ""));

        call1.enqueue(new Callback<PollDetailsResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call<PollDetailsResponse> call, @NonNull Response<PollDetailsResponse> response) {
                Gson gson = new GsonBuilder().create();
                JsonObject myCustomArray = gson.toJsonTree(response.body()).getAsJsonObject();
Log.e("PollDetailsResponse",myCustomArray.toString());
                JsonArray resopnseArray=myCustomArray.getAsJsonArray("response");
                    if (resopnseArray.get(0).getAsJsonObject().get("status").getAsString().equals("true")) {
                        JsonArray pollArray = resopnseArray.get(0).getAsJsonObject().getAsJsonArray("poll_details");
                        JsonObject pollObj= pollArray.get(0).getAsJsonObject();
                        try {
                            ColorStateList colorStateList = new ColorStateList(new int[][]{

                                    new int[]{android.R.attr.state_enabled} //enabled
                            }, new int[]{

                                    getResources().getColor(R.color.clr) //enabled
//                , Color.parseColor("00b9ee") //enabled

                            });
                            mProgressDialog.dismiss();
                            JSONObject obj=new JSONObject(pollObj.toString());
                            poll_details_title.setText(obj.getString("poll_title"));
                            poll_details_totalvotes.setText("Total votes : "+obj.getString("total_poll_votes"));
                            JSONArray pollVoteOptArray=obj.getJSONArray("poll_vote_options");
                            for (int x=0;x<pollVoteOptArray.length();x++){
                                LinearLayout layout=new LinearLayout(getApplicationContext());
                                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                                params.setMargins(5,5,5,5);
                               layout.setOrientation(LinearLayout.VERTICAL);
                                layout.setLayoutParams(params);


                                RadioButton tv=new RadioButton(getApplicationContext());
                                tv.setTextSize(20.0f);
                                tv.setTag(pollVoteOptArray.getJSONObject(x).getString("poll_vote_option_id"));
                                tv.setTextColor(Color.BLACK);
//                                tv.setEnabled(false);
                                tv.setClickable(false);
                                tv.setButtonTintList(colorStateList);
                                if (obj.getInt("my_poll_vote_option_id")==pollVoteOptArray.getJSONObject(x).getInt("poll_vote_option_id")) tv.setChecked(true);
                                tv.setText(pollVoteOptArray.getJSONObject(x).getString("option"));
                                layout.addView(tv);


                                RoundCornerProgressBar progressBar=new RoundCornerProgressBar(ActivityPollDetails.this, null, android.R.attr.progressBarStyleHorizontal);
                                LinearLayout.LayoutParams paramsprogress=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
                                paramsprogress.setMargins(5,5,5,5);

                                // get progress bar bounds.
//                                Rect bounds = progressBar.getProgressDrawable().getBounds();

//                                progressBar.setProgressDrawable(getResources().getDrawable(R.color.clr));

//                                progressBar.getProgressDrawable().setBounds(bounds);


//                                progressBar.setSecondaryProgressColor(R.color.clr);
                                progressBar.setLayoutParams(paramsprogress);
                                progressBar.setProgress(pollVoteOptArray.getJSONObject(x).getInt("vote_count")/obj.getInt("total_poll_votes")*100);
                                progressBar.setSecondaryProgress(100);
                                progressBar.setRadius(20);
                                progressBar.setProgressColor(Color.parseColor("#00b9ee"));
                                progressBar.setSecondaryProgressColor(Color.parseColor("#E0E0E0"));
                                progressBar.setMax(100);
                                progressBar.setPadding(5,5,5,5);
//                                progressBar.setProgre().setColorFilter(getResources().getColor(R.color.clr), PorterDuff.Mode.SRC_IN);

                                LinearLayout lyn=new LinearLayout(getApplicationContext());
                                lyn.setOrientation(LinearLayout.HORIZONTAL);
                                LinearLayout.LayoutParams paramstlyn=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                                lyn.setLayoutParams(paramstlyn);
                                TextView tQty=new TextView(getApplicationContext());
                                LinearLayout.LayoutParams paramstqty=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                                paramstqty.setMargins(20,0,20,10);
                                paramstqty.gravity= Gravity.START;

                                tQty.setLayoutParams(paramstqty);
                                tQty.setTextSize(15.0f);
                                tQty.setTextColor(Color.BLACK);
                                tQty.setText(pollVoteOptArray.getJSONObject(x).getInt("vote_count")+"/"+obj.getInt("total_poll_votes"));

                                TextView tper=new TextView(getApplicationContext());

                                paramstqty.gravity= Gravity.RIGHT;
tper.setGravity(Gravity.RIGHT);
                                tper.setLayoutParams(paramstqty);
                                tper.setTextSize(15.0f);
                                tper.setTextColor(Color.BLACK);
                                tper.setText((pollVoteOptArray.getJSONObject(x).getInt("vote_count")/obj.getInt("total_poll_votes")*100)+"%");
                                lyn.addView(tQty);
                                lyn.addView(tper);



                                layout.addView(progressBar);
                                layout.addView(lyn);
                                poll_details_progressbar_layout.addView(layout);



                                mBarChart.addBar(new BarModel(pollVoteOptArray.getJSONObject(x).getString("option")+" "+pollVoteOptArray.getJSONObject(x).getInt("vote_count")/obj.getInt("total_poll_votes")*100+"%",pollVoteOptArray.getJSONObject(x).getInt("vote_count")/obj.getInt("total_poll_votes")*100, c[x]));



                            }
                            mBarChart.startAnimation();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }else {mProgressDialog.dismiss();
                        Toast.makeText(ActivityPollDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();}

            }

            @Override
            public void onFailure(Call<PollDetailsResponse> call, Throwable t) {

            }
        });


    }
}
