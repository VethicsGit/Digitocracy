package com.example.kanika.digitocracy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context)
    {
        this.context=context;

    }

    public int[] slide_images={

            R.drawable.intro_screens,
            R.drawable.intro_screens,
            R.drawable.intro_screens
    };

    public String[] slide_title={
            "Raise your Voice",
            "Raise your Voice",
            "Raise your Voice"
    };
    public String[] slide_desc={
            "     Join the live polls and\n" +
                    "give your valuable feedback",
            "     Join the live polls and\n" +
                    "give your valuable feedback",
            "     Join the live polls and\n" +
                    "give your valuable feedback"
    };

    public int getCount() {
        return slide_desc.length;
    }


    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout)object;
    }

    @NonNull

    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);


        ImageView slideImageView= (ImageView) view.findViewById(R.id.images);
        TextView slidHeading =(TextView)view.findViewById(R.id.title);
        TextView slidDescription=(TextView) view.findViewById(R.id.desc);

//        slideImageView.setImageResource(slide_images[position]);
        slidHeading.setText(slide_title[position]);
        slidDescription.setText(slide_desc[position]);

        container.addView(view);

        return view;

    }


    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);



    }
}
