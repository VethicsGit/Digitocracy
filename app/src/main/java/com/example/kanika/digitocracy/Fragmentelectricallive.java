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

public class Fragmentelectricallive extends Fragment {

    RadioGroup elc_group;
    TextView elc_days,elc_vote;
    TextView elc_more;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frgamentelectricallive,container,false);


        elc_group=(RadioGroup)view.findViewById(R.id.elc_group);
        elc_days=(TextView)view.findViewById(R.id.elc_days);
        elc_vote=(TextView)view.findViewById(R.id.elc_vote);
        elc_more=(TextView)view.findViewById(R.id.elc_more);


        return view;
    }
}
