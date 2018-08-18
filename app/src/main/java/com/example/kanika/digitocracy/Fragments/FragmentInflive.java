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

public class FragmentInflive extends Fragment {


    RadioGroup inf_group;
    TextView inf_days1,inf_vots1,inf_days2,inf_vots2;
    Button inf_more1,inf_more2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentinflive,container,false);

        inf_group = (RadioGroup)view.findViewById(R.id.inf_group);
        inf_days1=(TextView)view.findViewById(R.id.inf_days1);
        inf_days2=(TextView)view.findViewById(R.id.inf_days2);
        inf_vots1=(TextView)view.findViewById(R.id.inf_vote1);
        inf_vots2=(TextView)view.findViewById(R.id.inf_vote2);
        inf_more1=(Button) view.findViewById(R.id.inf_more1);
        inf_more2=(Button) view.findViewById(R.id.inf_more2);

        return view;
    }
}
