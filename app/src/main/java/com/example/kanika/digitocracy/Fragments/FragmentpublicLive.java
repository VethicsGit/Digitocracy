package com.example.kanika.digitocracy.Fragments;

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

import com.example.kanika.digitocracy.R;

public class FragmentpublicLive extends Fragment{
    RadioGroup group;
    TextView days1,vots1,days2,vots2;
    Button more1,more2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentpubliclive,container,false);

        group = (RadioGroup)view.findViewById(R.id.group);
        days1=(TextView)view.findViewById(R.id.days1);
        days2=(TextView)view.findViewById(R.id.days2);
        vots1=(TextView)view.findViewById(R.id.vote1);
        vots2=(TextView)view.findViewById(R.id.vote2);
        more1=(Button) view.findViewById(R.id.more1);
        more2=(Button) view.findViewById(R.id.more2);

        return view;
    }
}
