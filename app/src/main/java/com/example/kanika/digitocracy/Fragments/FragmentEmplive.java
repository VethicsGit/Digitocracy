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

public class FragmentEmplive extends Fragment {

    RadioGroup emp_group;
    TextView emp_days1,emp_vots1,emp_days2,emp_vots2;
    Button emp_more1,emp_more2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentemplive,container,false);

        emp_group = (RadioGroup)view.findViewById(R.id.emp_group);
        emp_days1=(TextView)view.findViewById(R.id.emp_days1);
        emp_days2=(TextView)view.findViewById(R.id.emp_days2);
        emp_vots1=(TextView)view.findViewById(R.id.emp_vote1);
        emp_vots2=(TextView)view.findViewById(R.id.emp_vote2);
        emp_more1=(Button) view.findViewById(R.id.emp_more1);
        emp_more2=(Button) view.findViewById(R.id.emp_more2);


        return view;
    }
}
