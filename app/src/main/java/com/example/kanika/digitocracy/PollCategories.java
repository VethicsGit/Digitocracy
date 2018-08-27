package com.example.kanika.digitocracy;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.kanika.digitocracy.APIResponse.BlogCateDetail.BlogCateDetail;
import com.example.kanika.digitocracy.APIResponse.PollDetails_response.PollDetailsResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Fragments.FragmentBlog;
import com.example.kanika.digitocracy.Fragments.FragmentDebates;
import com.example.kanika.digitocracy.Fragments.Fragment_PollCategories;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.eazegraph.lib.models.BarModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollCategories extends AppCompatActivity {


    RelativeLayout settings, home, blogs, debates;
    LinearLayout viewpager;
    public TextView Title;

    ImageView setting_img,debets_img,blogs_img,home_img,blog_filter;
    TextView setting_txt,debets_txt,blogs_txt,home_txt;
    RadioButton marketing,business,professional;
    RadioGroup blog_filter_radio_group;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_categorie);

        Title = findViewById(R.id.Title);
        settings = findViewById(R.id.setting);
        home = findViewById(R.id.home);
        blogs = findViewById(R.id.blogs);
        debates = findViewById(R.id.debates);
        viewpager = findViewById(R.id.viewpager);


        home_img= findViewById(R.id.home_img);
        setting_img= findViewById(R.id.setting_img);
        debets_img= findViewById(R.id.debets_img);
        blogs_img= findViewById(R.id.blogs_img);

        home_txt= findViewById(R.id.home_txt);
        setting_txt= findViewById(R.id.setting_txt);
        debets_txt= findViewById(R.id.debets_txt);
        blogs_txt= findViewById(R.id.blogs_txt);
        blog_filter=findViewById(R.id.blog_filter);


        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new Fragment_PollCategories()).commit();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                Title.setText("Settings");
                ft.addToBackStack("Dashboard");
                ft.replace(R.id.viewpager,new Setting()).commit();

                home_img.setImageDrawable(getResources().getDrawable(R.drawable.home));
                blogs_img.setImageDrawable(getResources().getDrawable(R.drawable.blogs));
                debets_img.setImageDrawable(getResources().getDrawable(R.drawable.debates));
                setting_img.setImageDrawable(getResources().getDrawable(R.drawable.setting_selected));


                home_txt.setTextColor(Color.parseColor("#666666"));
                blogs_txt.setTextColor(Color.parseColor("#666666"));
                debets_txt.setTextColor(Color.parseColor("#666666"));
                setting_txt.setTextColor(getResources().getColor(R.color.clr));
                blog_filter.setVisibility(View.GONE);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                Title.setText("Poll Categories");
                ft.addToBackStack("Dashboard");
                ft.replace(R.id.viewpager,new Fragment_PollCategories()).commit();

                home_img.setImageDrawable(getResources().getDrawable(R.drawable.home_selected));
                blogs_img.setImageDrawable(getResources().getDrawable(R.drawable.blogs));
                debets_img.setImageDrawable(getResources().getDrawable(R.drawable.debates));
                setting_img.setImageDrawable(getResources().getDrawable(R.drawable.setting));

                home_txt.setTextColor(getResources().getColor(R.color.clr));
                blogs_txt.setTextColor(Color.parseColor("#666666"));
                debets_txt.setTextColor(Color.parseColor("#666666"));
                setting_txt.setTextColor(Color.parseColor("#666666"));

                blog_filter.setVisibility(View.GONE);
            }
        });
        blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                Title.setText("Blogs");
                ft.addToBackStack("Dashboard");
                ft.replace(R.id.viewpager,new FragmentBlog()).commit();

                home_img.setImageDrawable(getResources().getDrawable(R.drawable.home));
                blogs_img.setImageDrawable(getResources().getDrawable(R.drawable.blogs_selected));
                debets_img.setImageDrawable(getResources().getDrawable(R.drawable.debates));
                setting_img.setImageDrawable(getResources().getDrawable(R.drawable.setting));

                home_txt.setTextColor(Color.parseColor("#666666"));
                blogs_txt.setTextColor(getResources().getColor(R.color.clr));
                debets_txt.setTextColor(Color.parseColor("#666666"));
                setting_txt.setTextColor(Color.parseColor("#666666"));
                blog_filter.setVisibility(View.VISIBLE);

            }
        });
        debates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                Title.setText("Debates");
                ft.addToBackStack("Dashboard");
                ft.replace(R.id.viewpager,new FragmentDebates()).commit();

                home_img.setImageDrawable(getResources().getDrawable(R.drawable.home));
                blogs_img.setImageDrawable(getResources().getDrawable(R.drawable.blogs));
                debets_img.setImageDrawable(getResources().getDrawable(R.drawable.debates_selected));
                setting_img.setImageDrawable(getResources().getDrawable(R.drawable.setting));

                home_txt.setTextColor(Color.parseColor("#666666"));
                blogs_txt.setTextColor(Color.parseColor("#666666"));
                debets_txt.setTextColor(getResources().getColor(R.color.clr));
                setting_txt.setTextColor(Color.parseColor("#666666"));
                blog_filter.setVisibility(View.GONE);


            }
        });


        blog_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                blog_filter.setVisibility(View.GONE);

                final Dialog dailog=new Dialog(PollCategories.this,android.R.style.Theme_Light_NoTitleBar);
                dailog.setContentView(R.layout.filter_detail);
                marketing=dailog.findViewById(R.id.marketing);
                business=dailog.findViewById(R.id.business);
                professional=dailog.findViewById(R.id.professional);
               final TextView marketing_tv=view.findViewById(R.id.marketing_tv);
                final TextView business_tv=view.findViewById(R.id.business_tv);
                final TextView professional_tv=view.findViewById(R.id.professional_tv);
                final Button done=dailog.findViewById(R.id.done);
                blog_filter_radio_group=dailog.findViewById(R.id.blog_filter_radio_group);

                SharedPreferences LoginPref = getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
                final ProgressDialog mProgressDialog = new ProgressDialog(PollCategories.this);
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();
                API apiService = APIS.getRetrofit().create(API.class);
                Call<BlogCateDetail> call1 = apiService.bolg_category_list(LoginPref.getString("user_id", ""), LoginPref.getString("token", ""));

                call1.enqueue(new Callback<BlogCateDetail>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(@NonNull Call<BlogCateDetail> call, @NonNull Response<BlogCateDetail> response) {
                        Gson gson = new GsonBuilder().create();
                        JsonObject myCustomArray = gson.toJsonTree(response.body()).getAsJsonObject();
                        Log.e("BlogCateResponse",myCustomArray.toString());
                        JsonArray resopnseArray=myCustomArray.getAsJsonArray("response");
                        if (resopnseArray.get(0).getAsJsonObject().get("status").getAsString().equals("true")) {
                            JsonArray pollArray = resopnseArray.get(0).getAsJsonObject().getAsJsonArray("blog_category_list");


                            Log.e("response","response"+pollArray);
                         /*   JsonObject pollObj= pollArray.get(0).getAsJsonObject();*/
                            try {
                                ColorStateList colorStateList = new ColorStateList(new int[][]{

                                        new int[]{android.R.attr.state_enabled} //enabled
                                }, new int[]{

                                        getResources().getColor(R.color.clr) //enabled
//                , Color.parseColor("00b9ee") //enabled

                                });
                                mProgressDialog.dismiss();
                               /* JSONObject obj=new JSONObject(pollObj.toString());*/
                               if (pollArray.getAsJsonObject().get("status").getAsString().equals("true")){



                                   final JSONObject jsonObject=new JSONObject(pollArray.toString());
                                   dailog.show();
                                   done.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           int selectedId=blog_filter_radio_group.getCheckedRadioButtonId();

                                           if (selectedId==1){

                                               try {
                                                   marketing_tv.setText(jsonObject.getString("blog_category_id"));

                                               } catch (JSONException e) {
                                                   e.printStackTrace();
                                               }


                                           }
                                           if (selectedId==2){

                                               try {
                                                   business_tv.setText(jsonObject.getString("blog_category_id"));
                                               } catch (JSONException e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                           if (selectedId==3){

                                               try {
                                                   professional_tv.setText(jsonObject.getString("blog_category_id"));
                                               } catch (JSONException e) {
                                                   e.printStackTrace();
                                               }

                                           }
                                       }
                                   });

                               }
                              /*  poll_details_title.setText(obj.getString("poll_title"));
                                poll_details_totalvotes.setText("Total votes : "+obj.getString("total_poll_votes"));*/
/*
                                    JSONArray pollVoteOptArray=obj.getJSONArray("poll_vote_options");
*/
                              /*  for (int x=0;x<pollArray.size();x++){
                                    LinearLayout layout=new LinearLayout(getApplicationContext());
                                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                                    params.setMargins(5,5,5,5);
                                    layout.setOrientation(LinearLayout.VERTICAL);
                                    layout.setLayoutParams(params);*/

/*
                                    RadioButton tv=new RadioButton(getApplicationContext());
                                    tv.setTextSize(20.0f);
                                    tv.setTag(pollArray.getAsJsonObject(x).getString("blog_category_id"));
                                    tv.setTextColor(Color.BLACK);
//                                tv.setEnabled(false);
                                    tv.setClickable(false);
                                    tv.setButtonTintList(colorStateList);
                                    if (obj.getInt("my_poll_vote_option_id")==pollVoteOptArray.getJSONObject(x).getInt("poll_vote_option_id")) tv.setChecked(true);
                                    tv.setText(pollVoteOptArray.getJSONObject(x).getString("option"));
                                    layout.addView(tv);*/

/*

                                    RoundCornerProgressBar progressBar=new RoundCornerProgressBar(PollCategories.this, null, android.R.attr.progressBarStyleHorizontal);
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
*/



                               /* }*/
                             /*   mBarChart.startAnimation();*/
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else {mProgressDialog.dismiss();
                            Toast.makeText(PollCategories.this, "Something went wrong", Toast.LENGTH_SHORT).show();}

                    }

                    @Override
                    public void onFailure(Call<BlogCateDetail> call, Throwable t) {

                    }
                });






             /*   blog_filter.setVisibility(view.VISIBLE);
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentBlog fragmentBlog=new FragmentBlog();
                fragmentTransaction.replace(R.id.done,fragmentBlog);
                fragmentTransaction.commit();
*/


            }
        });




       /* electricity=findViewById(R.id.electricity);
        employement=findViewById(R.id.employement);
        transport=findViewById(R.id.transport);
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PollCategories.this,Electrical.class);
                startActivity(i);
            }
        });
        employement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PollCategories.this,Public.class);
                startActivity(i);
            }
        });*/
    }
  /*  public void setTitle(String title){
//        ((TextView)findViewById(R.id.Title)).setText(title);
        getSupportActionBar().setTitle(title);
    }*/
}

