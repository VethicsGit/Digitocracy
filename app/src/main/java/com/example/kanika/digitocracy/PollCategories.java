package com.example.kanika.digitocracy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.kanika.digitocracy.Fragments.Fragment_PollCategories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollCategories extends AppCompatActivity {


    RelativeLayout settings, home, blogs, debates;
    LinearLayout viewpager;
    TextView Title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_categorie);

        Title = findViewById(R.id.Title);
        settings = findViewById(R.id.setting);
        home = findViewById(R.id.home);
        blogs = findViewById(R.id.blogs);
        debates = findViewById(R.id.debates);
        viewpager = findViewById(R.id.viewpager);

        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new Fragment_PollCategories()).commit();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title.setText("Settings");
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title.setText("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new Fragment_PollCategories()).commit();
            }
        });
        blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title.setText("Blogs");
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new FragmentBlog()).commit();
            }
        });
        debates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title.setText("Debates");

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
}

