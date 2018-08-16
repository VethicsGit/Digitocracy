package com.example.kanika.digitocracy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FragmentGovlive extends Fragment {

    RadioGroup gov_group;
    TextView gov_days1,gov_vots1,gov_days2,gov_vots2;
    Button gov_more1,gov_more2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentgovlive,container,false);



        gov_group = (RadioGroup)view.findViewById(R.id.gov_group);
        gov_days1=(TextView)view.findViewById(R.id.gov_days1);
        gov_days2=(TextView)view.findViewById(R.id.gov_days2);
        gov_vots1=(TextView)view.findViewById(R.id.gov_vote1);
        gov_vots2=(TextView)view.findViewById(R.id.gov_vote2);
        gov_more1=(Button) view.findViewById(R.id.gov_more1);
        gov_more2=(Button) view.findViewById(R.id.gov_more2);


        return view;
    }
}
