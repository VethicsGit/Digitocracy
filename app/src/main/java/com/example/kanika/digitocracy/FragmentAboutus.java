package com.example.kanika.digitocracy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentAboutus extends Fragment {


        TextView about_tv1,about_description;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_aboutus,container,false);

        about_tv1=view.findViewById(R.id.about_tv1);
        about_description=view.findViewById(R.id.about_description);

        return view;
    }
}
