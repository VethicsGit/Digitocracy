package com.example.kanika.digitocracy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager view_pager;
    private LinearLayout layoutDots;

    private TextView[] mDots;
    private Button skip;
    private SlideAdapter slideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_pager = findViewById(R.id.view_pager);
        layoutDots = findViewById(R.id.layoutDots);

        slideAdapter = new SlideAdapter(this);
        view_pager.setAdapter(slideAdapter);

       skip = findViewById(R.id.btnskip);

        addDotsIndicator(0);

        view_pager.addOnPageChangeListener(viewlistner);

       skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent i =new Intent(MainActivity.this,Login.class);
             startActivity(i);
            }
        });



    }



    public void addDotsIndicator(int position) {
        mDots = new TextView[3];

        layoutDots.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colortrnasparentwhite));

            layoutDots.addView(mDots[i]);

        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.black));
        }

    }

    ViewPager.OnPageChangeListener viewlistner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}