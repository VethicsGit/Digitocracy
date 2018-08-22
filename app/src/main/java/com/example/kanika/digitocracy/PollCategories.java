package com.example.kanika.digitocracy;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Fragments.FragmentBlog;
import com.example.kanika.digitocracy.Fragments.FragmentDebates;
import com.example.kanika.digitocracy.Fragments.Fragment_PollCategories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollCategories extends AppCompatActivity {


    RelativeLayout settings, home, blogs, debates;
    LinearLayout viewpager;
    public TextView Title;

    ImageView setting_img,debets_img,blogs_img,home_img;
    TextView setting_txt,debets_txt,blogs_txt,home_txt;

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

