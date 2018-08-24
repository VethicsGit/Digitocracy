package com.example.kanika.digitocracy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kanika.digitocracy.Fragments.FragmentBlog;
import com.example.kanika.digitocracy.Fragments.FragmentDebates;
import com.example.kanika.digitocracy.Fragments.Fragment_PollCategories;

public class PollCategories extends AppCompatActivity {


    RelativeLayout settings, home, blogs, debates;
    LinearLayout viewpager;
    public TextView Title;

    ImageView setting_img,debets_img,blogs_img,home_img,blog_filter;
    TextView setting_txt,debets_txt,blogs_txt,home_txt;
    RadioButton marketing,business,professional;
    RadioGroup 

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

                blog_filter.setVisibility(view.INVISIBLE);
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
                blog_filter.setVisibility(view.INVISIBLE);

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
                blog_filter.setVisibility(view.INVISIBLE);


            }
        });


        blog_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dailog=new Dialog(getApplicationContext());
                dailog.setContentView(R.layout.filter_detail);
                marketing=(RadioButton)view.findViewById(R.id.marketing);
                business=(RadioButton)view.findViewById(R.id.business);
                professional=(RadioButton)view.findViewById(R.id.professional);
                final TextView marketni_tv=(TextView)view.findViewById(R.id.marketing_tv);
                TextView business_tv=(TextView)view.findViewById(R.id.business_tv);
                TextView professional_tv=(TextView)view.findViewById(R.id.professional_tv);
                Button done=view.findViewById(R.id.done);
                categori=view.findViewById(R.id.categori);



                blog_filter.setVisibility(view.VISIBLE);
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FragmentBlog fragmentBlog=new FragmentBlog();
                fragmentTransaction.replace(R.id.done,fragmentBlog);
                fragmentTransaction.commit();



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

