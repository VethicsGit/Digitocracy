package com.example.kanika.digitocracy.Polling;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kanika.digitocracy.R;
import com.example.kanika.digitocracy.Setting;

public class ActivityPollingMain extends AppCompatActivity {

    TextView pollingmain_livepolls, pollingmain_closepolls,polling_Title;
LinearLayout viewpager_polling;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_poll_main);
        pollingmain_livepolls = findViewById(R.id.pollingmain_livepolls);
        pollingmain_closepolls = findViewById(R.id.pollingmain_closepolls);
        polling_Title = findViewById(R.id.polling_Title);

        polling_Title.setText(getIntent().getStringExtra("name"));
        pollingmain_livepolls.performClick();

        /**/
        pollingmain_closepolls.setBackgroundColor(getResources().getColor(android.R.color.white));
        pollingmain_closepolls.setTextColor(Color.parseColor("#00b9ee"));
        pollingmain_livepolls.setBackgroundColor(Color.parseColor("#00b9ee"));
        pollingmain_livepolls.setTextColor(getResources().getColor(android.R.color.white));


        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        Polling_live_list_fragment polling_live_list_fragment=new Polling_live_list_fragment(getIntent().getIntExtra("id",0));
        ft.replace(R.id.viewpager_polling,polling_live_list_fragment).commit();
        /**/

        pollingmain_livepolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pollingmain_closepolls.setBackgroundColor(getResources().getColor(android.R.color.white));
                pollingmain_closepolls.setTextColor(Color.parseColor("#00b9ee"));
                pollingmain_livepolls.setBackgroundColor(Color.parseColor("#00b9ee"));
                pollingmain_livepolls.setTextColor(getResources().getColor(android.R.color.white));


                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
Polling_live_list_fragment polling_live_list_fragment=new Polling_live_list_fragment(getIntent().getIntExtra("id",0));
                ft.replace(R.id.viewpager_polling,polling_live_list_fragment).commit();

            }
        });

        pollingmain_closepolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pollingmain_livepolls.setBackgroundColor(getResources().getColor(android.R.color.white));
                pollingmain_livepolls.setTextColor(Color.parseColor("#00b9ee"));
                pollingmain_closepolls.setBackgroundColor(Color.parseColor("#00b9ee"));
                pollingmain_closepolls.setTextColor(getResources().getColor(android.R.color.white));

                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                Polling_closed_list_fragment polling_closed_list_fragment=new Polling_closed_list_fragment(getIntent().getIntExtra("id",0));
                ft.replace(R.id.viewpager_polling,polling_closed_list_fragment).commit();
            }
        });

        viewpager_polling=findViewById(R.id.viewpager_polling);
    }
}
